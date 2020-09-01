package organisation;

public class Mission {

	private String Nature;
	private String Titre;
	private String DateDebut;
	private String DateFin;
	private String Itineraire;
	private String InformationComplementaire;
	private int NiveauDeGravite;
	private int NiveauUrgence;
	
	public String getNature() {
		return Nature;
	}
	public void setNature(String nature) {
		Nature = nature;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getDateDebut() {
		return DateDebut;
	}
	public void setDateDebut(String dateDebut) {
		DateDebut = dateDebut;
	}
	public String getDateFin() {
		return DateFin;
	}
	public void setDateFin(String dateFin) {
		DateFin = dateFin;
	}
	public String getItineraire() {
		return Itineraire;
	}
	public void setItineraire(String itineraire) {
		Itineraire = itineraire;
	}
	public String getInformationComplementaire() {
		return InformationComplementaire;
	}
	public void setInformationComplementaire(String informationComplementaire) {
		InformationComplementaire = informationComplementaire;
	}
	public int getNiveauDeGravite() {
		return NiveauDeGravite;
	}
	public void setNiveauDeGravite(int niveauDeGravite) {
		NiveauDeGravite = niveauDeGravite;
	}
	public int getNiveauUrgence() {
		return NiveauUrgence;
	}
	public void setNiveauUrgence(int niveauUrgence) {
		NiveauUrgence = niveauUrgence;
	}
}
