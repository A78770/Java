package service.dao;

import model.Person;

public class UserDao {

	public void updatePerson(Person person) {
		System.out.println("update BDD, etc... (" + person.getFirstName() + ")");
	}
	
	public void insertPerson(Person person) {
		System.out.println("insert BDD, etc... (" + person.getFirstName() + ")");
	}
}
