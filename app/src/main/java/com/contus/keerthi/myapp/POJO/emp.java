package com.contus.keerthi.myapp.POJO;

/**
 * Created by user on 13/2/17.
 */

public class emp {


    private String username;
    private String age;
    private String email;
    private String password;

    public emp(String username, String age, String email, String password) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
