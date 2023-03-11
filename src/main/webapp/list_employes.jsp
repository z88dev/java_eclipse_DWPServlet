<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>    
<%@page import="com.prog.model.Employe" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Employes</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
	<style>
       .mtop-20 { margin-top:20px; }
	</style>
</head>
<body>
<div class="table-responsive">
   <h2 class="card-header text-center bg-info text-light">List Employes</h2>
   <a href="EmpController?action=add" class="btn btn-primary m-2">Ajouter Employee</a>

  <table class="table table-hover">
     <thead>
       <tr>
         <th>ID</th><th>Nom</th><th>Dob</th><th>Genre</th><th>Ville</th><th></th><th></th>
         </tr>
     </thead>
     <tbody>
<%
List<Employe> employes = (ArrayList<Employe>)request.getAttribute("listEmployes"); 
  for(Employe employe : employes) {
%>  
   <tr>
     <% int employe_id=employe.getId(); %>   
     <td><%= employe_id %></td>  	  
     <td><%= employe.getNom() %></td>  
     <td><%= employe.getDobStr() %></td>       
     <td><%= employe.getGenre() %></td>  
     <td><%= employe.getVille() %></td>
     <td><a href="EmpController?action=update&id=<%= employe_id %>" class="btn btn-outline-success">Modifier</a></td>
	 <td><a href="EmpController?action=delete&id=<%= employe_id %>" class="btn btn-outline-danger">Supprimer</a></td>     
	</tr>	  	  
<% 	  
  }
%>     
     </tbody>
</div>
</body>
</html>