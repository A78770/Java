package model;
import java.time.Instant;

public class Civil {
	private String firstName;
	private String lastName;
	private String civility;
	private String contactInfo;
	private Instant birthday; 
	private String nationality;
	private Instant deathDate;
	private String commentaire;
	private Instant addDate;
	private Instant modifDate;
	private String login;
	private int id;
	
	
	
	public Civil(String firstName, String lastName, String civility, String contactInfo, Instant birthday,
			String nationality, Instant deathDate, String commentaire, Instant addDate, Instant modifDate, String login,
			int id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.civility = civility;
		this.contactInfo = contactInfo;
		this.birthday = birthday;
		this.nationality = nationality;
		this.deathDate = deathDate;
		this.commentaire = commentaire;
		this.addDate = addDate;
		this.modifDate = modifDate;
		this.login = login;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Instant getAddDate() {
		return addDate;
	}
	public void setAddDate(Instant addDate) {
		this.addDate = addDate;
	}
	public Instant getModifDate() {
		return modifDate;
	}
	public void setModifDate(Instant modifDate) {
		this.modifDate = modifDate;
	}
	public String getCivility() {
		return civility;
	}
	public Instant getBirthday() {
		return birthday;
	}
	public void setBirthday(Instant birthday) {
		this.birthday = birthday;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Instant getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Instant deathDate) {
		this.deathDate = deathDate;
	}
	public void setCivility(String civility) {
		this.civility = civility;
	}
/*public Civil(String firstName, String lastName, String civility, String contactInfo, String email, Instant birthday) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.civility = civility;
		this.contactInfo = contactInfo;
		this.email = email;
		this.birthday = birthday;
	}*/
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
