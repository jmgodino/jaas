<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html;charset=ISO-8859-15"
	errorPage="/error.jsp"%>

<%
	String contextName = request.getContextPath();
%>
<html>
<head>
<title>Login</title>
</head>

<body>
	<center>

		<form action="<%=contextName%>/j_security_check" method="post">
			<table>
				<tr>
					<td align="left"><b class="app">Usuario:</b></td>
					<td align="left"><input class="app" type="text"
						name="j_username" size="30" maxlength="30" /></td>
				</tr>
				<tr>
					<td align="left"><b class="app">Contrase&ntilde;a:</b></td>
					<td align="left"><input class="app" type="password"
						name="j_password" size="30" maxlength="30" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="logon" value="Enviar" /></td>
				</tr>
			</table>

		</form>

	</center>
</body>
</html>
