package com.contus.keerthi.myapp.Contract;

import android.provider.BaseColumns;

/**
 * Created by user on 10/2/17.
 */

public class Employee {

    public static final String DB_NAME="employee.db";
    public static final int DATABASE_VERSION = 1;

    public static class regEntry implements BaseColumns {
        public static final String TABLE_NAME = "employeeReg";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_password = "password";
    }
}
