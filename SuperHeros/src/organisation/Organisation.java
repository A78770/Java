package organisation;

public class Organisation {
	
	private String Nom;
	private String SiegeSocial;
	private String Dirigeant;
	private String Membres;
	private String Commentaire;
	private String DateAjout;
	
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
	public String getMembres() {
		return Membres;
	}
	public void setMembres(String membres) {
		Membres = membres;
	}
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	public String getDateAjout() {
		return DateAjout;
	}
	public void setDateAjout(String dateAjout) {
		DateAjout = dateAjout;
	}
	
}
