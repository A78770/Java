package model;

import java.time.Instant;

public class SuperVilain extends SuperHeros{
	
	private String Nom;
	private String Pouvoir;
	private String PointFaible;
	private String Commentaire2;
	private int idSV;
	private int DegresMalveillance;
	
	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPouvoir() {
		return Pouvoir;
	}

	public void setPouvoir(String pouvoir) {
		Pouvoir = pouvoir;
	}

	public String getPointFaible() {
		return PointFaible;
	}

	public void setPointFaible(String pointFaible) {
		PointFaible = pointFaible;
	}

	public String getCommentaire2() {
		return Commentaire2;
	}

	public void setCommentaire2(String commentaire2) {
		Commentaire2 = commentaire2;
	}

	public int getIdSV() {
		return idSV;
	}

	public void setIdSV(int idSV) {
		this.idSV = idSV;
	}

	public SuperVilain(String firstName, String lastName, String civility, String contactInfo, Instant birthday,
			String nationality, Instant deathDate, String commentaire, Instant addDate, Instant modifDate, String login,
			int id, String nom, String pouvoir, String pointFaible, String commentaire2, int degresMalv, int idSV) {
		super(firstName, lastName, civility, contactInfo, birthday, nationality, deathDate, commentaire, addDate, modifDate,
				login, id);
		// TODO Auto-generated constructor stub
		Nom = nom;
		Pouvoir = pouvoir;
		PointFaible = pointFaible;
		DegresMalveillance = degresMalv;
		Commentaire2 = commentaire2;
		this.idSV = idSV;
	}
	
	public int getDegresMalveillance() {
		return DegresMalveillance;
	}
	public void setDegresMalveillance(int degresMalveillance) {
		DegresMalveillance = degresMalveillance;
	}
}
