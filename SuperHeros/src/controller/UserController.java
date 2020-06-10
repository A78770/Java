package controller;

import model.Person;
import service.dao.UserDao;

public class UserController {

	private UserDao userDao = new UserDao();
	
	public void onInsert(Person person) {
		userDao.insertPerson(person);
	}
	
	public void onUpdate(Person person) {
		userDao.updatePerson(person);
	}
}
