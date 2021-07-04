package com.aisolutions.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.aisolutions.myapplication.Model.MainActivity;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "results.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_DETAIL = "Details";
    public static final String TABLE_SEMESTER_DETAIL = "Semester_Details";
    public static final String TABLE_YEAR_1_SEMESTER_1 = "Year1_Semester1";
    public static final String TABLE_YEAR_1_SEMESTER_2 = "Year1_Semester2";
    public static final String TABLE_YEAR_2_SEMESTER_1 = "Year2_Semester1";
    public static final String TABLE_YEAR_2_SEMESTER_2 = "Year2_Semester2";
    public static final String TABLE_YEAR_3_SEMESTER_1 = "Year3_Semester1";
    public static final String TABLE_YEAR_3_SEMESTER_2 = "Year3_Semester2";

    public static final String TABLE_PASS = "Pass_Subjects";
    public static final String TABLE_REPEAT = "Repeat_Subjects";
    public static final String TABLE_NOT_ATTEND = "Not_Attend_Subjects";

    public static final String TABLE_SEM_GPA = "Sem_Gpa";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // IndexNumber
        // Password
        // Name
        // Gpa
        // Rank
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DETAIL + " (IndexNumber TEXT, Password TEXT, Name TEXT,Gpa TEXT, Rank TEXT)");

        // ID
        // Name
        // Pass
        // Repeat
        // Not_attend
        // Less_attend
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SEMESTER_DETAIL + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Pass TEXT, Repeat TEXT, Not_attend TEXT,Less_attend TEXT)");

        // ID
        // Code
        // Name
        // Result
        // Status
        // GpaValue
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_1_SEMESTER_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_1_SEMESTER_2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_2_SEMESTER_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_2_SEMESTER_2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_3_SEMESTER_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_YEAR_3_SEMESTER_2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT, Status TEXT, GpaValue TEXT)");

        // ID
        // Code
        // Name
        // Result
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PASS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REPEAT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NOT_ATTEND + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT, Name TEXT, Result TEXT)");

        //sem1
        //sem2
        //sem3
        //sem4
        //sem5
        //sem6
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SEM_GPA + " (sem1 TEXT, sem2 TEXT, sem3 TEXT, sem4 TEXT, sem5 TEXT, sem6 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTER_DETAIL);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_1_SEMESTER_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_1_SEMESTER_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_2_SEMESTER_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_2_SEMESTER_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_3_SEMESTER_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_YEAR_3_SEMESTER_2);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PASS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REPEAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOT_ATTEND);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEM_GPA);
    }

    //---------------------------------LOGOUT---------------------------------------------------------------------------------------
    public void onLogout(MainActivity context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    //------------***************************************************************************--------------------------------
    //-----------Data Insert to DETAILS TABLE-------------------------------
    // IndexNumber
    // Password
    // Name
    // Gpa
    // Rank
    public boolean insertData_DETAILS(String index, String password, String name, String gpa, String rank) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IndexNumber", index);
        contentValues.put("Password", password);
        contentValues.put("Name", name);
        contentValues.put("Gpa", gpa);
        contentValues.put("Rank", rank);

        long enterResult = sqLiteDatabase.insert(TABLE_DETAIL, null, contentValues);
        if (enterResult == -1)
            return false;
        else
            return true;
    }

    //--------------------Data Insert to SEMESTER_DETAIL TABLE---------------------------------
    // Name
    // Pass
    // Repeat
    // Not_attend
    // Less_attend
    public boolean insertData_SEMESTER_DETAILS(String name, String pass, String repeat, String not_attend, String less_attend) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Pass", pass);
        contentValues.put("Repeat", repeat);
        contentValues.put("Not_attend", not_attend);
        contentValues.put("Less_attend", less_attend);

        long enterResult = sqLiteDatabase.insert(TABLE_SEMESTER_DETAIL, null, contentValues);
        if (enterResult == -1)
            return false;
        else
            return true;
    }

    //----------------------Data Insert to SEMESTER TABLES----------------------------------
    // Code
    // Name
    // Result
    // Status
    // GpaValue
    public boolean insertDataSemesters(String tableName, String code, String name, String result, String status, String gpa_value) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Code", code);
        contentValues.put("Name", name);
        contentValues.put("Result", result);
        contentValues.put("Status", status);
        contentValues.put("GpaValue", gpa_value);

        long enterResult = sqLiteDatabase.insert(tableName, null, contentValues);
        if (enterResult == -1)
            return false;
        else
            return true;
    }

    //-------------------------Data Insert to RESULTS TABLES--------------------------------------------
    // Code
    // Name
    // Status
    public boolean insertDataOthers(String tableName, String code, String name, String result) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Code", code);
        contentValues.put("Name", name);
        contentValues.put("Result", result);

        long enterResult = sqLiteDatabase.insert(tableName, null, contentValues);
        if (enterResult == -1)
            return false;
        else
            return true;
    }

    //-----------Data Insert to TABLE_SEM_GPA TABLE-------------------------------
    // IndexNumber
    // Password
    // Name
    // Gpa
    // Rank
    public boolean insertData_Sem_Gpa(String sem1, String sem2, String sem3, String sem4, String sem5, String sem6) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sem1", sem1);
        contentValues.put("sem2", sem2);
        contentValues.put("sem3", sem3);
        contentValues.put("sem4", sem4);
        contentValues.put("sem5", sem5);
        contentValues.put("sem6", sem6);

        long enterResult = sqLiteDatabase.insert(TABLE_SEM_GPA, null, contentValues);
        if (enterResult == -1)
            return false;
        else
            return true;
    }
//------------***************************************************************************--------------------------------

    //--------------------Data Get from TABLES-----------------------------------------------------------------
    // Here code for Get All Data from TABLE
    public Cursor getAllData(String tableName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);
        return cursor;
    }
}
