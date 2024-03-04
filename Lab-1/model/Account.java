package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private String account_number;
    private String username_of_account_holder;
    private String account_type; // "Standard" or "Savings"
    private Date account_opening_date;

    public Account(String account_number, String username_of_account_holder, String account_type, Date account_opening_date) {
        this.account_number = account_number;
        this.username_of_account_holder = username_of_account_holder;
        this.account_type = account_type;
        this.account_opening_date = account_opening_date;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getUsername_of_account_holder() {
        return username_of_account_holder;
    }

    public void setUsername_of_account_holder(String username_of_account_holder) {
        this.username_of_account_holder = username_of_account_holder;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Date getAccount_opening_date() {
        return account_opening_date;
    }

    public void setAccount_opening_date(Date account_opening_date) {
        this.account_opening_date = account_opening_date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return String.format("%-15s| %-25s| %-15s| %-20s",
                this.account_number,
                this.username_of_account_holder,
                this.account_type,
                sdf.format(this.account_opening_date));
    }
}
