@echo off
SET CATALINA_HOME=C:\apache-tomcat-7.0.67
javac -encoding utf8 -cp .\classes -d .\classes .\src\main\User.java
javac -encoding utf8 -cp .\classes -d .\classes .\src\main\Database.java
javac -encoding utf8 -cp .\classes -d .\classes .\src\main\UserManager.java
javac -encoding utf8 -cp .\classes;.\lib\bson-3.0.4.jar;.\lib\mongodb-driver-3.0.4.jar;.\lib\mongodb-driver-core-3.0.4.jar -d .\classes .\src\main\MongoConnectDatabase.java
javac -encoding utf8 -cp .\classes;.\lib\mysql-connector-java-5.1.38-bin.jar;%CATALINA_HOME%\lib\servlet-api.jar -d .\classes .\src\main\MySQLConnectDatabase.java
javac -encoding utf8 -cp .\classes -d .\classes .\src\main\Signinout.java
javac -encoding utf8 -cp .\classes;%CATALINA_HOME%\lib\servlet-api.jar -d .\classes .\src\main\SigninoutImpl.java
javac -encoding utf8 -cp .\classes;.\lib\bson-3.0.4.jar;.\lib\mongodb-driver-3.0.4.jar;.\lib\mongodb-driver-core-3.0.4.jar -d .\test .\src\test\Unit1.java
javac -encoding utf8 -cp .\classes;%CATALINA_HOME%\lib\servlet-api.jar -d .\classes .\src\mypkg\HelloServlet.java
javac -encoding utf8 -cp .\classes;%CATALINA_HOME%\lib\servlet-api.jar -d .\classes .\src\sso\SigninServlet.java
javac -encoding utf8 -cp .\classes;%CATALINA_HOME%\lib\servlet-api.jar -d .\classes .\src\sso\SignoutServlet.java

rem java -cp .\classes;.\lib\bson-3.0.4.jar;.\lib\mongodb-driver-3.0.4.jar;.\lib\mongodb-driver-core-3.0.4.jar test.Unit1
rem javac -encoding utf8 -cp .\classes;.\lib\bson-3.0.4.jar;.\lib\mongodb-driver-3.0.4.jar;.\lib\mongodb-driver-core-3.0.4.jar -s .\src\main -d .\classes .\test\Unit1.java
rem java -cp .\classes;.\lib\bson-3.0.4.jar;.\lib\mongodb-driver-3.0.4.jar;.\lib\mongodb-driver-core-3.0.4.jar;.\classes Unit1