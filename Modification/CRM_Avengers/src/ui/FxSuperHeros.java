package ui;

import javafx.beans.property.SimpleStringProperty;
import model.SuperHeros;

public class FxSuperHeros {
	private SimpleStringProperty Nom;
	private SimpleStringProperty Pouvoir;
	private SimpleStringProperty PointFaible;
	private int Score;
	private SimpleStringProperty Commentaire2;
	private SimpleStringProperty NomPreCiv;
	
	public String getNomPreCiv() {
		return NomPreCiv.get();
	}

	public void setNomPreCiv(String nomPreCiv) {
		NomPreCiv.set(nomPreCiv);
	}
	private SuperHeros SH = new SuperHeros(null,null,null,null,null,null,null,null,null,null,null,0,null,null,null,0,null,0);
	
	public FxSuperHeros(SuperHeros SH) {
		this(SH.getNom(), SH.getPouvoir(), SH.getPointFaible(), SH.getScore(), SH.getCommentaire2(),null);
		this.SH = SH;
	}
	
	public FxSuperHeros(String Nom, String Pouvoir, String PointFaible, Integer Score, String Commentaire2,String nomPreCiv) {
		this.Nom= new SimpleStringProperty(Nom);
		this.Pouvoir= new SimpleStringProperty(Pouvoir);
		this.PointFaible=new SimpleStringProperty(PointFaible);
		this.Score=Score;
		this.Commentaire2=new SimpleStringProperty(Commentaire2);
		this.NomPreCiv= new SimpleStringProperty(nomPreCiv);
		SH.setPouvoir(Pouvoir);
		SH.setNom(Nom);
		SH.setPointFaible(PointFaible);
		SH.setScore(Score);
		SH.setCommentaire2(Commentaire2);
	}
	public String getNom() {
		return Nom.get();
	}
	public void setNom(String nom) {
		Nom.set(nom);
		SH.setNom(nom);
	}
	public String getPouvoir() {
		return Pouvoir.get();
	}
	public void setPouvoir(String pouvoir) {
		Pouvoir.set(pouvoir);
		SH.setPouvoir(pouvoir);
	}
	public String getPointFaible() {
		return PointFaible.get();
	}
	public void setPointFaible(String pointFaible) {
		PointFaible.set(pointFaible);
		SH.setPointFaible(pointFaible);
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score=score;
		SH.setScore(score);
	}
	public String getCommentaire2() {
		return Commentaire2.get();
	}
	public void setCommentaire2(String commentaire) {
		Commentaire2.set(commentaire);
		SH.setCommentaire2(commentaire);
	}
	public SuperHeros getSuperHeros() {
		return SH;
	}
}
