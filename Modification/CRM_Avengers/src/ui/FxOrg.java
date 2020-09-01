package ui;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import organisation.Organisation;

public class FxOrg {

	private SimpleStringProperty Nom;
	private SimpleStringProperty SiegeSocial;
	private SimpleStringProperty Dirigeant;
	private int Membres;
	private SimpleStringProperty Commentaire;
	private SimpleStringProperty DateAjout;
	
	private Organisation org = new Organisation(null,null,null,0,null,null,0);
	
	public FxOrg(Organisation org) {
		this(org.getNom(),org.getSiegeSocial(),org.getDirigeant(),org.getMembres(),org.getCommentaire(),org.getDateAjout());
		this.org=org;
	}
	
	public FxOrg(String Nom, String SiegeSoc, String Drgt, int Mbrs, String Comm, Instant DatAjt) {
		this.Nom= new SimpleStringProperty(Nom);
		this.SiegeSocial= new SimpleStringProperty(SiegeSoc);
		this.Dirigeant=new SimpleStringProperty(Drgt);
		this.Membres=Mbrs;
		this.Commentaire=new SimpleStringProperty(Comm);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		if (DatAjt!=null) {
			String dtajt = formatter.format(DatAjt);
			this.DateAjout=new SimpleStringProperty(dtajt);
			org.setDateAjout(DatAjt);
			org.setCommentaire(Comm);
			org.setDirigeant(Drgt);
			org.setSiegeSocial(SiegeSoc);
			org.setNom(Nom);
			org.setMembres(Mbrs);
		}
	}

	public String getNom() {
		return Nom.get();
	}

	public void setNom(String nom) {
		Nom.set(nom);
		org.setNom(nom);
	}

	public String getSiegeSocial() {
		return SiegeSocial.get();
	}

	public void setSiegeSocial(String siegeSocial) {
		SiegeSocial.set(siegeSocial);
		org.setSiegeSocial(siegeSocial);
	}

	public String getDirigeant() {
		return Dirigeant.get();
	}

	public void setDirigeant(String dirigeant) {
		Dirigeant.set(dirigeant);
		org.setDirigeant(dirigeant);
	}

	public int getMembres() {
		return Membres;
	}

	public void setMembres(int membres) {
		Membres=membres;
		org.setMembres(membres);
	}

	public String getCommentaire() {
		return Commentaire.get();
	}

	public void setCommentaire(String commentaire) {
		Commentaire.set(commentaire);
		org.setCommentaire(commentaire);
	}

	public String getDateAjout() {
		return DateAjout.get();
	}

	public void setDateAjout(String dateAjout) {
		this.DateAjout.set(dateAjout);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		org.setDateAjout(Instant.from(formatter.parse(dateAjout)));
	}

	public Organisation getOrg() {
		return org;
	}

}
