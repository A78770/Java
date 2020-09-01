package organisation;

public class Incident {
	
	private String Type;
	private String PersonneDeclarant;
	private String Adresse;
	private String ComplementAdresse;
	private String SuperVilain;
	private String InformationSupplementaire;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getPersonneDeclarant() {
		return PersonneDeclarant;
	}
	public void setPersonneDeclarant(String personneDeclarant) {
		PersonneDeclarant = personneDeclarant;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getComplementAdresse() {
		return ComplementAdresse;
	}
	public void setComplementAdresse(String complementAdresse) {
		ComplementAdresse = complementAdresse;
	}
	public String getSuperVilain() {
		return SuperVilain;
	}
	public void setSuperVilain(String superVilain) {
		SuperVilain = superVilain;
	}
	public String getInformationSupplementaire() {
		return InformationSupplementaire;
	}
	public void setInformationSupplementaire(String informationSupplementaire) {
		InformationSupplementaire = informationSupplementaire;
	}
}
