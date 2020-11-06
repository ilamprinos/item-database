<%@ page import="java.util.*" %>
<html>
<body>
<h1 align="center">New Product Added</h1>
<p>
<%
	List details = (List)request.getAttribute("details");
	out.print("<br>Barcode: "+ details.get(0) );
	out.print("<br>Name: "+ details.get(1) );
	out.print("<br>Color: " +details.get(2) );
	out.print("<br>Description: " +details.get(3) );
 
%>
</body>
</html>
