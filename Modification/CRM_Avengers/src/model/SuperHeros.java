package model;

import java.time.Instant;

public class SuperHeros extends Civil{
	
	private String Nom;
	private String Pouvoir;
	private String PointFaible;
	private int Score;
	private String Commentaire2;
	private int idSH;
	
	public SuperHeros(String firstName, String lastName, String civility, String contactInfo, Instant birthday,
			String nationality, Instant deathDate, String commentaire, Instant addDate, Instant modifDate, String login,
			int id, String nom, String pouvoir, String pointFaible, int score, String commentaire2, int idSH) {
		super(firstName, lastName, civility, contactInfo, birthday, nationality, deathDate, commentaire, addDate,
				modifDate, login, id);
		Nom = nom;
		Pouvoir = pouvoir;
		PointFaible = pointFaible;
		Score = score;
		Commentaire2 = commentaire2;
		this.idSH = idSH;
	}

	public int getIdSH() {
		return idSH;
	}

	public void setIdSH(int idSH) {
		this.idSH = idSH;
	}

	public SuperHeros(String firstName, String lastName, String civility, String contactInfo, Instant birthday, String nationality, Instant deathDate, String commentaire, Instant addDate, Instant modifDate, String login, int id ) {
		super(firstName, lastName, civility, contactInfo, birthday, nationality, deathDate, commentaire, addDate, modifDate, login, id);
	}
	
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
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getCommentaire2() {
		return Commentaire2;
	}
	public void setCommentaire2(String commentaire) {
		Commentaire2 = commentaire;
	}
}
