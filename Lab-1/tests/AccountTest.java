package tests;

import model.Account;
import utils.TestUtils;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTest {
    private Account account;
    private final String initialAccountNumber = "12345678";
    private final String initialUsername = "user123";
    private final String initialAccountType = "Checking";
    private final Date initialOpeningDate = new Date();


    // Manual testing
    public static void main(String[] args) {
        testAccountConstructor();
    }

    public static void testAccountConstructor() {
        String accountNumber = "12345678";
        String username = "user123";
        String accountType = "Checking";
        Date openingDate = new Date();

        Account account = new Account(accountNumber, username, accountType, openingDate);

        // Verify
        System.out.println("Starting manual assertions for testAccountConstructor");

        // Verifying account number
        if (account.getAccount_number().equals(accountNumber)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccountNumber-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccountNumber-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying username
        if (account.getUsername_of_account_holder().equals(username)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getUsername-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying account type
        if (account.getAccount_type().equals(accountType)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getAccountType-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getAccountType-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying opening date
        if (account.getAccount_opening_date().equals(openingDate)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getAccountOpeningDate-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getAccountOpeningDate-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying the setAccount_number method
        account.setAccount_number("87654321");
        if (account.getAccount_number().equals("87654321")) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-setAccountNumber-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-setAccountNumber-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying the setUsername_of_account_holder method
        account.setUsername_of_account_holder("newUser456");
        if (account.getUsername_of_account_holder().equals("newUser456")) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC6-setUsernameOfAccountHolder-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC6-setUsernameOfAccountHolder-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying the setAccount_type method
        account.setAccount_type("Savings");
        if (account.getAccount_type().equals("Savings")) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC7-setAccountType-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC7-setAccountType-Failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Verifying the setAccount_opening_date method
        Date newDate = new Date(); // Use a specific date as per your requirement
        account.setAccount_opening_date(newDate);
        if (account.getAccount_opening_date().equals(newDate)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC8-setAccountOpeningDate-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC8-setAccountOpeningDate-Failed" + TestUtils.TEXT_COLOR_RESET);
        }
    }

    // Automated testing
    @BeforeEach
    public void setUp() {
        // Initialise the Account object before each test
        account = new Account(initialAccountNumber, initialUsername, initialAccountType, initialOpeningDate);
    }

    @Test
    public void testConstructor() {
        try {
            assertNotNull(account, "Account is null.");
            assertEquals(initialAccountNumber, account.getAccount_number(), "Account number does not match.");
            assertEquals(initialUsername, account.getUsername_of_account_holder(), "Username does not match.");
            assertEquals(initialAccountType, account.getAccount_type(), "Account type does not match.");
            assertEquals(initialOpeningDate, account.getAccount_opening_date(), "Opening date does not match.");
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-testConstructor-Passed" + TestUtils.TEXT_COLOR_RESET);
        } catch (AssertionError e) {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-testConstructor-Failed - " + TestUtils.TEXT_COLOR_RESET + e.getMessage());
            throw e; // Rethrow the assertion error to let the framework handle it
        }
    }

    @Test
    public void testSetAndGetAccountNumber() {
        // Test setting and getting the account number
        String newAccountNumber = "87654321";
        account.setAccount_number(newAccountNumber);
        try {
            assertEquals(newAccountNumber, account.getAccount_number(), "Account number does not match after set.");
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-testSetAndGetAccountNumber-Passed" + TestUtils.TEXT_COLOR_RESET);
        } catch (AssertionError e) {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-testSetAndGetAccountNumber-Failed - " + TestUtils.TEXT_COLOR_RESET + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSetAndGetUsernameOfAccountHolder() {
        // Test setting and getting the username of account holder
        String newUsername = "user456";
        account.setUsername_of_account_holder(newUsername);
        try {
            assertEquals(newUsername, account.getUsername_of_account_holder(), "Username does not match after set.");
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-testSetAndGetUsernameOfAccountHolder-Passed" + TestUtils.TEXT_COLOR_RESET);
        } catch (AssertionError e) {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-testSetAndGetUsernameOfAccountHolder-Failed - " + TestUtils.TEXT_COLOR_RESET + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSetAndGetAccountType() {
        // Test setting and getting the account type
        String newAccountType = "Savings";
        account.setAccount_type(newAccountType);
        try {
            assertEquals(newAccountType, account.getAccount_type(), "Account type does not match after set.");
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-testSetAndGetAccountType-Passed" + TestUtils.TEXT_COLOR_RESET);
        } catch (AssertionError e) {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-testSetAndGetAccountType-Failed - " + TestUtils.TEXT_COLOR_RESET + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSetAndGetOpeningDate() {
        // Test setting and getting the account opening date
        Date newOpeningDate = new Date();
        account.setAccount_opening_date(newOpeningDate);
        try {
            assertEquals(newOpeningDate, account.getAccount_opening_date(), "Opening date does not match after set.");
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-testSetAndGetOpeningDate-Passed" + TestUtils.TEXT_COLOR_RESET );
        } catch (AssertionError e) {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-testSetAndGetOpeningDate-Failed - " + TestUtils.TEXT_COLOR_RESET  + e.getMessage());
            throw e;
        }
    }
}
