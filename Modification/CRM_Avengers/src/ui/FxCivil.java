package ui;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import model.Civil;


public class FxCivil {
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty login;
	private final SimpleStringProperty civilite;
	private final SimpleStringProperty coordonnees;
	private final SimpleStringProperty nationalite;
	private SimpleStringProperty dateNaissance;
	private SimpleStringProperty dateDeces;
	private SimpleStringProperty commentaire;
	private SimpleStringProperty dateAdd;
	private SimpleStringProperty dateMod;
	private SimpleStringProperty org;
	
	

	private Civil civil = new Civil(null, null, null, null, null, null, null, null, null, null, null, 0);
	
	public FxCivil(Civil civil) {
		this(civil.getFirstName(),civil.getLastName(),civil.getLogin(), civil.getCivility(), civil.getContactInfo(),civil.getNationality(),civil.getBirthday(),civil.getDeathDate(),civil.getCommentaire(),civil.getAddDate(),civil.getModifDate(),null);
		this.civil = civil;
	}
	
	public FxCivil(String fName, String lName, String lgin, String cvlt, String crdn, String ntlt, Instant brthd, Instant dth, String cmmt, Instant addate, Instant moddate,String org) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.login = new SimpleStringProperty(lgin);
        this.civilite= new SimpleStringProperty(cvlt);
        this.coordonnees=new SimpleStringProperty(crdn);
        this.nationalite=new SimpleStringProperty(ntlt);
        this.org=new SimpleStringProperty(org);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
        String birthStr = formatter.format(brthd);
        this.dateNaissance=new SimpleStringProperty(birthStr);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
        String dthStr="";
        if (dth != null) {
        	dthStr = form.format(dth);
        }
        this.dateDeces=new SimpleStringProperty(dthStr);
        this.commentaire=new SimpleStringProperty(cmmt);
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
        String addstr = forma.format(addate);
        this.dateAdd=new SimpleStringProperty(addstr);
        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
        String modstr = "";
        if (moddate!=null) {
        	modstr=formatt.format(moddate);
        }
        this.dateMod=new SimpleStringProperty(modstr);
        civil.setFirstName(fName);
        civil.setLastName(lName);
        civil.setLogin(lgin);
        civil.setCivility(cvlt);
        civil.setContactInfo(crdn);
        civil.setNationality(ntlt);
        civil.setBirthday(brthd);
        civil.setDeathDate(dth);
        civil.setCommentaire(cmmt);
        civil.setAddDate(addate);
        civil.setModifDate(moddate);
	}
	
	public String getOrg() {
		return org.get();
	}

	public void setOrg(String org) {
		this.org.set(org);
	}

	public String getDateMod() {
		return dateMod.get();
	}
	
	public void setDateMod(String modifdate) {
		this.dateMod.set(modifdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		civil.setModifDate(Instant.from(formatter.parse(modifdate)));
	}
	
	public String getDateAjt() {
		return dateAdd.get();
	}

	public void setDateAjt(String dateaj) {
		this.dateAdd.set(dateaj);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		civil.setAddDate(Instant.from(formatter.parse(dateaj)));
	}
	public String getCommentaire() {
		return commentaire.get();
	}

	public void setCommentaire(String cmmt) {
		commentaire.set(cmmt);
		civil.setCommentaire(cmmt);
	}

	public String getDateDeces() {
		return dateDeces.get();
	}

	public void setDateDeces(String dateDeces) {
		this.dateDeces.set(dateDeces);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		civil.setDeathDate(Instant.from(formatter.parse(dateDeces)));
	}
	
	public String getDateNaissance() {
		return dateNaissance.get();
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance.set(dateNaissance);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
		civil.setBirthday(Instant.from(formatter.parse(dateNaissance)));
	}

	public void setNationalite(String ntl) {
		nationalite.set(ntl);
		civil.setNationality(ntl);
	}

	public String getNationalite() {
		return nationalite.get();
	}
	
	public void setCoordonnees(String cdn) {
		coordonnees.set(cdn);
		civil.setContactInfo(cdn);
	}
	
	
	public String getCoordonnees() {
		return coordonnees.get();
	}

	public String getCivilite() {
		return civilite.get();
	}
	
	public void setCivilite(String cvl) {
		civilite.set(cvl);
		civil.setCivility(cvl);
	}
	public String getLogin() {
		return login.get();
	}
	
	public void setLogin(String lgin) {
		login.set(lgin);
		civil.setFirstName(lgin);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String fName) {
		firstName.set(fName);
		civil.setFirstName(fName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String fName) {
		lastName.set(fName);
		civil.setLastName(fName);
	}

	public Civil getCivil() {
		return civil;
	}
}
