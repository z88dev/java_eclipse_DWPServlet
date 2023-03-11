package com.prog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.prog.model.Employe;

public class EmployeDao {
	 Connection con = null;

	 public List<Employe> getAllEmployes() {	
		 String sql = "SELECT * FROM employe";	
		 List<Employe> employes = new ArrayList<>();

		   try {
			  con = DBUtil.getConnection();
			  PreparedStatement ps = con.prepareStatement(sql);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
			     Employe employe = new Employe();
			     employe.setId(rs.getInt("id"));
			     employe.setNom(rs.getString("nom"));
			     employe.setDob(rs.getDate("dob"));
			     employe.setGenre(rs.getString("genre"));
			     employe.setVille(rs.getString("ville"));

			     employes.add(employe);			     
			   }  // end while
			     rs.close();
			     DBUtil.disconnect();
			  } catch (SQLException e) {
			    e.printStackTrace();
			}	  // end try
		   
		return employes;	   
	}   // end getAllClients 	
	 
	 public Employe getEmployeById(int id) {	
		 String sql = "SELECT * FROM employe WHERE id=?";	
		 Employe emp = new Employe();
		 		   
		   try {
			  con = DBUtil.getConnection();
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setInt(1, id);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
			     emp.setId(rs.getInt("id"));
			     emp.setNom(rs.getString("nom"));
			     emp.setDob(rs.getDate("dob"));
			     emp.setGenre(rs.getString("genre"));
			     emp.setVille(rs.getString("ville"));			     
			   }  // end if
			   rs.close();
			   DBUtil.disconnect();
			} catch (SQLException e) {
			    e.printStackTrace();
			}	  // end try
			   
		return emp;	   
	}   // end getEmployeById 	 
	 
	public int addEmploye(Employe employe) throws SQLException {
			String sql = "INSERT INTO employe (nom,dob,genre,ville) VALUES (?,?,?,?)";
			int rowInserted=0;
			
			try {
				  con = DBUtil.getConnection();
				  PreparedStatement ps = con.prepareStatement(sql);
				  ps.setString(1, employe.getNom());
				  ps.setDate(2, employe.getDob());				  
				  ps.setString(3, employe.getGenre());
				  ps.setString(4, employe.getVille());				  
				  rowInserted = ps.executeUpdate();	// return # row inserted
				  ps.close();
				  DBUtil.disconnect();
			}  catch (SQLException e) {
			    e.printStackTrace();
			}	  // end try
			
		return rowInserted;
	}  // end addEmploye	
	
	public int updateEmploye(Employe employe) throws SQLException {
		String sql = "UPDATE employe SET nom=?,dob=?,genre=?,ville=? WHERE id=?";
		int rowUpdated=0;
		
		try {
			  con = DBUtil.getConnection();
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setString(1, employe.getNom());
			  ps.setDate(2, employe.getDob());				  
			  ps.setString(3, employe.getGenre());
			  ps.setString(4, employe.getVille());		
			  ps.setInt(5, employe.getId());	
			  rowUpdated = ps.executeUpdate();	// return # row updated
			  ps.close();
			  DBUtil.disconnect();
		}  catch (SQLException e) {
		    e.printStackTrace();
		}	  // end try
		
		return rowUpdated;
}  // end updateEmploye	
	
	public int deleteEmploye(int id) throws SQLException {
		String sql = "DELETE FROM employe WHERE id=?";
		int rowDeleted=0;
		
		try {
			  con = DBUtil.getConnection();
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setInt(1, id);
			  rowDeleted = ps.executeUpdate();	// return # row delete
			  ps.close();
			  DBUtil.disconnect();
		}  catch (SQLException e) {
		    e.printStackTrace();
		}	  // end try
		
		return rowDeleted;
	}  // end deleteEmploye	
}  // end EmployeDao
