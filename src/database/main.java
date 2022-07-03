package database;

import chatapp.User;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		User a = new User();
		a = dao.login("tuan", "123");

	}

}
