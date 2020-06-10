package ui;

import javafx.beans.property.SimpleStringProperty;
import model.Person;

public class FxPerson {

	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty email;

	private Person person = new Person();
	
	public FxPerson(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            person.setFirstName(fName);
            person.setLastName(lName);
            person.setEmail(email);
        }

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String fName) {
		firstName.set(fName);
		person.setFirstName(fName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String fName) {
		lastName.set(fName);
		person.setLastName(fName);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String fName) {
		email.set(fName);
		person.setEmail(fName);
	}
	
	public Person getPerson() {
		return person;
	}
}