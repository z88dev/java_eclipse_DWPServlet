package com.prog.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Employe {
	 private int id;   
	 private String nom;   
	 private Date dob;
	 private String genre;
	 private String ville;	 
	 
	public Employe() { 
		this.id=0;
		this.nom="";
		this.dob=null;
		this.genre="";
		this.ville="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDob() {
		return dob;
	}
	
	public String getDobStr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dobStr = simpleDateFormat.format(dob);  // 2022-10-18
		return dobStr;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}	 	 
}
