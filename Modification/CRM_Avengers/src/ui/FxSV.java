package ui;

import javafx.beans.property.SimpleStringProperty;
import model.SuperVilain;

public class FxSV {
	private SimpleStringProperty Nom;
	private SimpleStringProperty Pouvoir;
	private SimpleStringProperty PointFaible;
	private int DegresMalveillance;
	private SimpleStringProperty Commentaire2;
	private SimpleStringProperty NomPreCiv;
	
	public String getNomPreCiv() {
		return NomPreCiv.get();
	}

	public void setNomPreCiv(String nomPreCiv) {
		NomPreCiv.set(nomPreCiv);
	}

	private SuperVilain SV = new SuperVilain(null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0);

	public FxSV(SuperVilain SV) {
		this(SV.getNom(), SV.getPouvoir(),SV.getPointFaible(), SV.getDegresMalveillance(), SV.getCommentaire2(),null);
		this.SV=SV;
	}
	
	public FxSV(String Nom, String Pouvoir, String PointFaible, Integer DegresMalveillance, String Commentaire2,String nomPreCiv) {
		this.Nom= new SimpleStringProperty(Nom);
		this.Pouvoir= new SimpleStringProperty(Pouvoir);
		this.PointFaible=new SimpleStringProperty(PointFaible);
		this.DegresMalveillance=DegresMalveillance;
		this.Commentaire2=new SimpleStringProperty(Commentaire2);
		this.NomPreCiv= new SimpleStringProperty(nomPreCiv);
		SV.setPouvoir(Pouvoir);
		SV.setNom(Nom);
		SV.setPointFaible(PointFaible);
		SV.setDegresMalveillance(DegresMalveillance);
		SV.setCommentaire2(Commentaire2);
	}
	
	public String getNom() {
		return Nom.get();
	}
	public void setNom(String nom) {
		Nom.set(nom);
		SV.setNom(nom);
	}
	public String getPouvoir() {
		return Pouvoir.get();
	}
	public void setPouvoir(String pouvoir) {
		Pouvoir.set(pouvoir);
		SV.setPouvoir(pouvoir);
	}
	public String getPointFaible() {
		return PointFaible.get();
	}
	public void setPointFaible(String pointFaible) {
		PointFaible.set(pointFaible);
		SV.setPointFaible(pointFaible);
	}
	
	public String getCommentaire2() {
		return Commentaire2.get();
	}
	public void setCommentaire2(String commentaire) {
		Commentaire2.set(commentaire);
		SV.setCommentaire2(commentaire);
	}
	public SuperVilain getSuperVilain() {
		return SV;
	}
	
	public int getDegresMalveillance() {
		return DegresMalveillance;
	}
	
	public void setDegresMalveillace(int degresMalveillance) {
		DegresMalveillance=degresMalveillance;
		SV.setDegresMalveillance(degresMalveillance);
	}
}
