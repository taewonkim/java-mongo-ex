package sso;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import main.*;

public class SignedServlet extends HttpServlet {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Database db;
		try {
			db = new MySQLConnectDatabase(this.dbConnectUrl, this.dbUsername, this.dbPassword);
			db.connect();
			UserManager userManager = new UserManager(db);
			Signinout signinout = new SigninoutImpl(userManager, session);
			if( signinout.signed() ) {
				out.print("OK!");
			} else {
				out.print("NO!");
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