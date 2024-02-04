package tests;

import model.User;
import utils.TestUtils;

public class UserTest {

	public static void main(String[] args) {
		/* Manual Testing
		User testUser = new User("jeff", "passWord", "Jeff", "Jimbob", "0773494369");
		
		System.out.println(testUser);
		*/
		testUserConstructor();
		
	}
	
	public static void testUserConstructor() {
		// Automated testing
		// Setup
				
		String username = "jeff";
		String password = "passWord";
		String firstName = "Jeff";
		String lastName = "Jimbob";
		String mobile = "0773494369";
		
		// Constructor
				
		User testUser = new User(username, password, firstName, lastName, mobile);
				
		// Verify
				
		System.out.println("Starting assertions of testUserConstructor");
				
		if (testUser.getUsername() == username) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed"+ TestUtils.TEXT_COLOR_RESET);
		} else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-Failed"+ TestUtils.TEXT_COLOR_RESET);
		}
				
		if (testUser.getPassword() == password) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed"+ TestUtils.TEXT_COLOR_RESET);
		} else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-Failed"+ TestUtils.TEXT_COLOR_RESET);
		}
				
		if (testUser.getFirst_name() == firstName) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getFirst_name-Passed"+ TestUtils.TEXT_COLOR_RESET);
		} else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getFirst_name-Failed"+ TestUtils.TEXT_COLOR_RESET);
		}
				
		if (testUser.getLast_name() == lastName) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getLast_name-Passed"+ TestUtils.TEXT_COLOR_RESET);
		} else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getLast_name-Failed"+ TestUtils.TEXT_COLOR_RESET);
		}
				
		if (testUser.getMobile_number() == mobile) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-getMobile_number-Passed"+ TestUtils.TEXT_COLOR_RESET);
		} else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-getMobile_number-Failed"+ TestUtils.TEXT_COLOR_RESET);
		}
	}

}
