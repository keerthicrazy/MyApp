package com.contus.keerthi.myapp.dbEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.contus.keerthi.myapp.Contract.Employee;
import com.contus.keerthi.myapp.POJO.emp;

import java.util.ArrayList;
import java.util.List;

import static com.contus.keerthi.myapp.R.string.username;

/**
 * Created by user on 10/2/17.
 */

public class dbHelper extends SQLiteOpenHelper {


    public dbHelper(Context context, String db_name,  int db_version) {
        super(context, db_name, null, db_version);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Employee.regEntry.TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tblname=Employee.regEntry.TABLE_NAME;

        String username= Employee.regEntry.COLUMN_NAME_USERNAME;
        String age=Employee.regEntry.COLUMN_NAME_AGE;
        String email=Employee.regEntry.COLUMN_NAME_EMAIL;
        String password=Employee.regEntry.COLUMN_NAME_password;

        String employee_details_table="CREATE TABLE " + tblname + "("
                +username+" TEXT,"+age+" INTEGER,"+email+" TEXT,"
                +password+" TEXT "+ ")";

        db.execSQL(employee_details_table);
    }

    public Boolean addEmployee(emp employee)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Employee.regEntry.COLUMN_NAME_USERNAME,employee.getUsername());
        values.put(Employee.regEntry.COLUMN_NAME_AGE,employee.getAge());
        values.put(Employee.regEntry.COLUMN_NAME_EMAIL,employee.getEmail());
        values.put(Employee.regEntry.COLUMN_NAME_password,employee.getPassword());
        long n=db.insert(Employee.regEntry.TABLE_NAME,null,values);
        db.close();
        return n>0;
    }

    public String login(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(Employee.regEntry.TABLE_NAME,new String[]
                {Employee.regEntry.COLUMN_NAME_USERNAME},Employee.regEntry.COLUMN_NAME_EMAIL + "=? " +
                " AND " +Employee.regEntry.COLUMN_NAME_password + "=?",new String[]{String.valueOf(email),String.valueOf(password)},null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor.getString(0);
        }else{
            return "login_failed";
        }
    }

    public List<emp> getAllEmployee(){

        List<emp> employeeList= new ArrayList<emp>();
        String selectQuery = "SELECT * FROM "+Employee.regEntry.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                emp emp=new emp(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                employeeList.add(emp);
            }while (cursor.moveToNext());
        }
        return employeeList;
    }

}
