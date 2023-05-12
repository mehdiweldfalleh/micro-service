package com.esprit.microservice.EntrepriseService.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

	@Entity
	public class Entreprise implements Serializable {
		private static final long serialVersionUID = 6711457437559348053L;
		@Id
		@GeneratedValue
		private int id;
		private String nom;
	
		private String email;
		
		public Entreprise() {
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
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}

		public Entreprise(int id, String nom, String email) {
			this.id = id;
			this.nom = nom;
			this.email = email;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Entreprise(String nom) {
			this.nom = nom;
		}
	
	}

