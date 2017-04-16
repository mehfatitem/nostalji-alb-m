package com.mehfatitem.imageUploadList.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{5,30}$";

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,30})";

    public boolean validUserName(String userName) {
        this.pattern = Pattern.compile(USERNAME_PATTERN);
        this.matcher = this.pattern.matcher(userName);
        return this.matcher.matches();
    }

    public boolean validPassword(String password) {
        this.pattern = Pattern.compile(PASSWORD_PATTERN);
        this.matcher = this.pattern.matcher(password);
        return this.matcher.matches();
    }

    public boolean validEmail(String email) {
        this.pattern = Pattern.compile(EMAIL_REGEX);
        this.matcher = this.pattern.matcher(email);
        System.out.println(email + "validation --->" + this.matcher.matches());
        return this.matcher.matches();
    }

    public boolean validIsExist(String inputVal) {
        boolean returnResult = true;
        int length = inputVal.trim().length();
        if (length == 0) {
            returnResult = false;
        } else if (length > 0) {
            returnResult = true;
        }
        return returnResult;
    }
}
