package com.contus.keerthi.myapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 3/2/17.
 */

public class Validation {

    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidName(String name){

        return true;
    }



    public boolean isValidPassword(String pass) {

        if (pass != null && pass.length() > 7 && pass.length()<=32) {

            return true;
        }
        return false;
    }
}
