package com.mms.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordUtil {

    private final static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public static boolean compare(String encryptedPwd, String plainPwd) {
        return passwordEncryptor.checkPassword(plainPwd, encryptedPwd);
    }

    public static String encrypt(String password) {
        return passwordEncryptor.encryptPassword(password);
    }

}
