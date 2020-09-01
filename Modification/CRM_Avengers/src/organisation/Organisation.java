package organisation;

import java.time.Instant;

public class Organisation {
	
	private String Nom;
	private String SiegeSocial;
	private String Dirigeant;
	private int Membres;
	private String Commentaire;
	private Instant DateAjout;
	
	
	public Organisation(String nom, String siegeSocial, String dirigeant, int membres, String commentaire,
			Instant dateAjout, int id) {
		super();
		Nom = nom;
		SiegeSocial = siegeSocial;
		Dirigeant = dirigeant;
		Membres = membres;
		Commentaire = commentaire;
		DateAjout = dateAjout;
		this.id = id;
	}
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getSiegeSocial() {
		return SiegeSocial;
	}
	public void setSiegeSocial(String siegeSocial) {
		SiegeSocial = siegeSocial;
	}
	public String getDirigeant() {
		return Dirigeant;
	}
	public void setDirigeant(String dirigeant) {
		Dirigeant = dirigeant;
	}
	public int getMembres() {
		return Membres;
	}
	public void setMembres(int membres) {
		Membres = membres;
	}
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	public Instant getDateAjout() {
		return DateAjout;
	}
	public void setDateAjout(Instant dateAjout) {
		this.DateAjout = dateAjout;
	}
}
