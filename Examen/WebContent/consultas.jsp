<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="es.aulamentor.Prestamo" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biblioteca</title>
</head>
<body>
<%ArrayList<Prestamo> prestamos = (ArrayList)request.getAttribute("prestamos"); %>
	<h2>RESULTADOS</h2>
	<br>
	<p>Nº de consultas: <%=request.getAttribute("contador") %></p>
	<br>
	<%if(prestamos==null || prestamos.size()==0){%>
		<p>No se han encontrado préstamos.</p>
		<br>
		<a href="/Examen/index.html">Realizar una nueva consulta</a>
		
	<% } else{ %>
		
		<h3>LIBRO: <%=prestamos.get(0).getTitulo()+" de "+prestamos.get(0).getAutor()+"("+prestamos.get(0).getPaginas()+"pag)" %></h3>
		<br>
		
		<table>
		<%for(int i=0;i<prestamos.size();i++){ %>
			<tr>
				<td>- Fecha: <%=prestamos.get(i).getFecha() %> Socio: <%=prestamos.get(i).getSocio() %>  </td>
			</tr>
		<%}%>
		</table>
		<br>
		<a href="/Examen/index.html">Realizar una nueva consulta</a>
	<% }
	%>
</body>
</html>