package com.prog.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import com.prog.model.Employe;
import com.prog.dao.EmployeDao;
/**
 * Servlet implementation class EmpController
 */
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION="action";
	private static final String ACTION_LIST="list";  
	private static final String ACTION_ADD="add";  
	private static final String ACTION_DELETE="delete"; 	
	private static final String ACTION_UPDATE="update";	
	private static final String FORM_ACTION_ADD="ajouter";	
	private static final String FORM_ACTION_UPDATE="modifier";	
	private static final String ID="id";
	private static final String NOM="nom";
	private static final String DOB="dob";
	private static final String GENRE="genre";
	private static final String VILLE="ville";
	private static String LIST_EMPLOYES="list_employes.jsp";
	private static String EMPLOYEE_FORM="employee_form.jsp";
	String forward=ACTION_LIST;
	String action="";
	private List<Employe> employes;
	private EmployeDao employeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		action = req.getParameter(ACTION);
		
		if (action.equalsIgnoreCase(ACTION_ADD)) {
			req.setAttribute(ACTION, ACTION_ADD);
		    forward=EMPLOYEE_FORM;
		} else if (action.equalsIgnoreCase(ACTION_UPDATE)) {
			req.setAttribute(ACTION, ACTION_UPDATE);
        	int empId = Integer.parseInt(req.getParameter(ID));
    		employeDao = new EmployeDao();
        	Employe emp = employeDao.getEmployeById( empId );
			req.setAttribute("employeRec", emp);			
		    forward=EMPLOYEE_FORM;
		    
		} else if (action.equalsIgnoreCase(ACTION_DELETE)) {				
		//   req.setAttribute("action", ACTION_DELETE);
		   deleteEmp(req, res);	
	    } else {  
		   // Default action is List employees
           listEmp(req, res);
		}
				
		RequestDispatcher rs = req.getRequestDispatcher(forward);
		rs.forward(req, res);
	}  // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// <input type="submit" value="ajouter" name="add
        if (req.getParameter(ACTION_ADD) != null) {
        	action=ACTION_ADD;	  
        	addUpdateEmp(req, res);
        } else if (req.getParameter(ACTION_UPDATE) != null) {
        	action=ACTION_UPDATE;	 
        	addUpdateEmp(req, res);
        } else {
        	listEmp(req, res);
        }
	}   // end doPost
	
	private void listEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		employeDao = new EmployeDao();
		employes = employeDao.getAllEmployes();
		req.setAttribute("listEmployes", employes);
		forward=LIST_EMPLOYES;		
		RequestDispatcher rs = req.getRequestDispatcher(forward);
		rs.forward(req, res);
	}   // end listEmp
		
	private void addUpdateEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		employeDao = new EmployeDao();
		Employe emp = new Employe();
				
		emp.setNom(req.getParameter(NOM));
		// Convert date input from string to date format
		String dobStr=req.getParameter(DOB);
		DateFormat formatter;
		formatter=new SimpleDateFormat("yyyy-MM-dd");				
		Date dob=Date.valueOf(dobStr);
		emp.setDob(dob);
		
		// Get gender select
		emp.setGenre(req.getParameter(GENRE));
		// Get ville select
		emp.setVille(req.getParameter(VILLE));
		// Get favorite sports checkbox
		Boolean hockey=false;
		Boolean ski=false;
		Boolean soccer=false;
		
		if (req.getParameterValues("sport") != null) {  // 
			String[] sportValues =  req.getParameterValues("sport");
			for (String sport : sportValues) {
			   if (sport.equalsIgnoreCase("hockey")) {
				   hockey=true;
			   }			   
			   if (sport.equalsIgnoreCase("ski")) {
				   ski=true;
			   }
			   if (sport.equalsIgnoreCase("soccer")) {
				   soccer=true;
			   }
			}
		}	
		
		if (action.equalsIgnoreCase(ACTION_UPDATE)) {	
		  // Update record
			try {
				emp.setId(Integer.parseInt(req.getParameter(ID)));  // get ID to update this record
				int row_update=employeDao.updateEmploye(emp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else {
	      // Add record	
			try {
				int row_add=employeDao.addEmploye(emp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	
	    }
		// Go back to List Employees menu
        listEmp(req, res);
	}   // addEmp
	
	private void deleteEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		employeDao = new EmployeDao();
		int employeId = Integer.parseInt(req.getParameter(ID));		
		try {
			employeDao.deleteEmploye( employeId );
			listEmp(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   // end deleteEmp	
			
}  // end EmpController

