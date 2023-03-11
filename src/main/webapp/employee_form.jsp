<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>    
<%@page import="com.prog.model.Employe" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employe Form</title>
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
<%
String submit_name;
   String action=(String)request.getAttribute("action");
   Employe emp = new Employe();
   if (request.getAttribute("employeRec") != null) {
  	 emp = (Employe)request.getAttribute("employeRec"); 
   }
   submit_name=action.equalsIgnoreCase("update") ? "Modifier" : "Ajouter";
%>
   <h2 class="card-header text-center bg-info text-light"><%= submit_name %> Employe</h2>
   <form action="EmpController" method="post">
      <table class="table table hover table-striped">
		<tr>
			<td>Id</td>
			<td><input type="hidden" type="text" name="id" value="<%= emp.getId() %>">
              <% if (emp.getId() > 0) { 
    	            out.print(emp.getId());
    	      } %>			
			</td>
		</tr>        
		<tr>
			<td>Nom</td>
			<td><input type="text" name="nom" value="<% if (!emp.getNom().isEmpty()) { 
    	       out.print(emp.getNom());
    	   } %>">
		   </td>
		</tr>  
		<tr>
			<td>Date de naissance</td>
			<td><input type="date" name="dob" value="<% if (emp.getDob() != null) { 
    	       out.print(emp.getDobStr());
    	   } %>">
			</td>
		</tr>	
		<tr>
	   	   <td>Genre</td>
		   <td><input type="radio" name="genre" value="homme" <% if (emp.getGenre().equalsIgnoreCase("homme")) { out.print(" checked"); } %>  
		       >Homme
		   	   <input type="radio" name="genre" value="femme" <% if (emp.getGenre().equalsIgnoreCase("femme")) { out.print(" checked"); } %>  
		   	   >Femme 
		   	   <input type="radio" name="genre" value="autre" <% if (emp.getGenre().equalsIgnoreCase("autre")) { out.print(" checked"); } %>  
		   	   >Autre
		   </td>
		</tr>
		<tr>
		  <td>Ville</td>
		  <td><select name="ville">
				<option value="Québec" <% if (emp.getVille().equalsIgnoreCase("Québec")) { out.print(" selected"); } %>  
				>Québec</option>
				<option value="Montréal" <% if (emp.getVille().equalsIgnoreCase("Montréal")) { out.print(" selected"); } %>
				>Montréal</option>
				<option value="Rimouski" <% if (emp.getVille().equalsIgnoreCase("Rimouski")) { out.print(" selected"); } %>
				>Rimouski</option>
				<option value="Gatineau" <% if (emp.getVille().equalsIgnoreCase("Gatineau")) { out.print(" selected"); } %>
				>Gatineau</option> 
				<option value="Ottawa" <% if (emp.getVille().equalsIgnoreCase("Ottawa")) { out.print(" selected"); } %>
				>Ottawa</option>
		  	</select>
	  	 </td>
		</tr>	
		<tr>
		  <td>Sports favoris</td>
		  <td><input type="checkbox" name="sport" value="hockey" />&nbsp;Hockey&nbsp;&nbsp;
		  <input type="checkbox" name="sport" value="ski" />&nbsp;Ski&nbsp;&nbsp;
		  <input type="checkbox" name="sport" value="soccer" />&nbsp;Soccer</td>
		</tr>
		<tr>
			<td><input type="submit" value="cancel" name="list" class="btn btn-outline-secondary"></td>
			<td><input type="reset" value="effacer" name="reset" class="btn btn-outline-danger">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="<%= submit_name %>" name="<%= action %>" class="btn btn-outline-success"></td>
		 </tr>
      </table>
   </form>
</div>   
</body>
</html>