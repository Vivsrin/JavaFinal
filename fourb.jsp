<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!String name,password; %>
<%
name=request.getParameter("name");
password=request.getParameter("password");
out.println(name+" "+password);
if(name.equals("qwerty") && password.equals("1234"))
{
	session =request.getSession(true);
	if(session.isNew())
	{
		session.setAttribute("username",name);
		out.println("welcome to my website "+name);
	}
	
	else
	{
		out.println("welcome back to my website "+name);
	}
	}
else
{
	out.println("invalid username and password");
	
}
%>

</body>
</html>
