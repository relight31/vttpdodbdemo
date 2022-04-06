package vttp.dodbdemo.model;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Friend {
    private String email;
    private String name;
    private String phone;
    private String status;
    private Date dob;
    private String passphrase;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public static Friend createWithoutPassphrase(SqlRowSet result) {
        Friend friend = new Friend();
        friend.setEmail(result.getString("email"));
        friend.setName(result.getString("name"));
        friend.setPhone(result.getString("phone"));
        friend.setDob(result.getDate("dob"));
        friend.setStatus(result.getString("status"));
        return friend;
    }
}
