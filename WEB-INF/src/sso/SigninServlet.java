package sso;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import main.*;

public class SigninServlet extends HttpServlet {
	private String dbConnectUrl = "";
	private String dbUsername = "";
	private String dbPassword = "";

	@Override
	public void init(ServletConfig config) 
	throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		this.dbConnectUrl = context.getInitParameter("dbConnectUrl");
		this.dbUsername = context.getInitParameter("dbUsername");
		this.dbPassword = context.getInitParameter("dbPassword");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Database db = null;
		try {
			String session_user_id = "";
			synchronized(session) {
				session_user_id = (String) session.getAttribute("user_id");
			}
			if(session_user_id != null) {
				response.sendRedirect(request.getContextPath());
			} else {
				db = new MySQLConnectDatabase(this.dbConnectUrl, this.dbUsername, this.dbPassword);
				db.connect();
				UserManager userManager = new UserManager(db);
				Signinout signinout = new SigninoutImpl(userManager, session);

				String user_id = request.getParameter("user_id");
				String user_password = request.getParameter("user_password");
				if(user_id == null || user_id.equals(""))
					throw new IOException("Empty! User ID Field! (" + user_id + ")");
				if(user_password == null || user_password.equals(""))
					throw new IOException("Empty! User Password Field! (" + user_password + ")");
				user_password = db.password(user_password);
				if(signinout.signin(user_id, user_password)) {
					session.setAttribute("user_id", user_id);
					session.setAttribute("user_signed", System.currentTimeMillis());
					response.sendRedirect(request.getContextPath());
				} else {
					response.sendRedirect(request.getContextPath());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(db != null) 
				db.release();
			out.close();
		}
	}
}