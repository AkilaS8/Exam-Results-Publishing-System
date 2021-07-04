package com.aisolutions.myapplication.Model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aisolutions.myapplication.Connection.Config;
import com.aisolutions.myapplication.Database.DatabaseHelper;
import com.aisolutions.myapplication.Details.DataSetConfig;
import com.aisolutions.myapplication.Details.LoadingDialog;
import com.aisolutions.myapplication.Details.SharedPrefs;
import com.aisolutions.myapplication.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edit_text_username, edit_text_password;
    Button btn_login;
    DatabaseHelper databaseHelper;
    RequestQueue myQueue;
    public String index, password, name, semester1, semester2, semester3, semester4, semester5, semester6, sem_gpa;
    public Double gpa;
    public int rank;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_text_username = findViewById(R.id.login_username_txt);
        edit_text_password = findViewById(R.id.login_password_txt);
        btn_login = findViewById(R.id.login_LoginButton_btn);

        databaseHelper = new DatabaseHelper(this);
        myQueue = Volley.newRequestQueue(this);

        if (isInternetOn()) {
            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
            Snackbar snack = Snackbar.make(findViewById(R.id.login_activity), "NO INTERNET", Snackbar.LENGTH_LONG).setAction("SETTINGS", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
            });
            snack.show();
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edit_text_username.getText().toString().equals("")) {
                    if (!edit_text_password.getText().toString().equals("")) {
                        getJSONData();
                    } else {
                        Toast.makeText(LoginActivity.this, "Enter your password", Toast.LENGTH_LONG).show();
                        edit_text_password.requestFocus();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter your index number", Toast.LENGTH_LONG).show();
                    edit_text_username.requestFocus();
                }
            }
        });
    }

    private void getJSONData() {
        loadingDialog = new LoadingDialog(LoginActivity.this);
        loadingDialog.startLoading();
        String url = Config.KEY_DATA_URL + edit_text_username.getText().toString().trim();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_JSON_ARRAY);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject myDataSet = jsonArray.getJSONObject(i);

                        //----------------DATA Fetch---------------------------------
                        index = myDataSet.getString(Config.KEY_INDEX);
                        password = myDataSet.getString(Config.KEY_PASSWORD);
                        name = myDataSet.getString(Config.KEY_NAME);
                        semester1 = myDataSet.getString(Config.KEY_SEMESTER_1);
                        semester2 = myDataSet.getString(Config.KEY_SEMESTER_2);
                        semester3 = myDataSet.getString(Config.KEY_SEMESTER_3);
                        semester4 = myDataSet.getString(Config.KEY_SEMESTER_4);
                        semester5 = myDataSet.getString(Config.KEY_SEMESTER_5);
                        semester6 = myDataSet.getString(Config.KEY_SEMESTER_6);
                        sem_gpa = myDataSet.getString(Config.KEY_SEM_GPA);
                        gpa = myDataSet.getDouble(Config.KEY_GPA);
                        rank = myDataSet.getInt(Config.KEY_RANK);

                        String tempPassword = edit_text_password.getText().toString();

                        //---------------Login Verification----------------------------------
                        if (tempPassword.equalsIgnoreCase(password)) {

                            //------------Data insert to Array & TABLES-----------------------------------
                            intoArraysDataSemester1(semester1);
                            intoArraysDataSemester2(semester2);
                            intoArraysDataSemester3(semester3);
                            intoArraysDataSemester4(semester4);
                            intoArraysDataSemester5(semester5);
                            intoArraysDataSemester6(semester6);
                            intoArraysDataSemGpa(sem_gpa);
                            //-------------Data insert to TABLE_DETAILS--------------------------
                            boolean Detail_Inserted = databaseHelper.insertData_DETAILS(index, password, name, String.valueOf(gpa), String.valueOf(rank));
                            if (Detail_Inserted = true) {
                                loadingDialog.dismissLoading();
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                //-------------------------Remember Me------------------------------
                                SharedPrefs.saveSharedSetting(LoginActivity.this, "MyLogin", "false");
                                Intent ImLoggedIn = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(ImLoggedIn);
                                finish();
                                //------------------------------------------------------------------
                            } else {
                                loadingDialog.dismissLoading();
                                Toast.makeText(LoginActivity.this, "Data insertion Wrong", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            loadingDialog.dismissLoading();
                            Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                            edit_text_username.setText("");
                            edit_text_password.setText("");
                            edit_text_username.requestFocus();
                        }
                    }
                    //
                    if (jsonArray.length() == 0) {
                        loadingDialog.dismissLoading();
                        edit_text_username.setText("");
                        edit_text_password.setText("");
                        edit_text_username.requestFocus();
                        Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myQueue.add(request);
    }

    public void intoArraysDataSemester1(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_1 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_1.length);
            DataSetConfig.Status_semester_1 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_1, DataSetConfig.Detail_semester_1.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_1, "Year I Semester I");
            semesterResultsTableSemester("Year1_Semester1", DataSetConfig.Code_semester_1, DataSetConfig.SubjectName_semester_1, DataSetConfig.Detail_semester_1, DataSetConfig.Status_semester_1, DataSetConfig.GpaValue_semester_1);
        }
    }

    public void intoArraysDataSemester2(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_2 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_2.length);
            DataSetConfig.Status_semester_2 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_2, DataSetConfig.Detail_semester_2.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_2, "Year I Semester II");
            semesterResultsTableSemester("Year1_Semester2", DataSetConfig.Code_semester_2, DataSetConfig.SubjectName_semester_2, DataSetConfig.Detail_semester_2, DataSetConfig.Status_semester_2, DataSetConfig.GpaValue_semester_2);
        }
    }

    public void intoArraysDataSemester3(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_3 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_3.length);
            DataSetConfig.Status_semester_3 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_3, DataSetConfig.Detail_semester_3.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_3, "Year II Semester I");
            semesterResultsTableSemester("Year2_Semester1", DataSetConfig.Code_semester_3, DataSetConfig.SubjectName_semester_3, DataSetConfig.Detail_semester_3, DataSetConfig.Status_semester_3, DataSetConfig.GpaValue_semester_3);
        }
    }

    public void intoArraysDataSemester4(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_4 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_4.length);
            DataSetConfig.Status_semester_4 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_4, DataSetConfig.Detail_semester_4.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_4, "Year II Semester II");
            semesterResultsTableSemester("Year2_Semester2", DataSetConfig.Code_semester_4, DataSetConfig.SubjectName_semester_4, DataSetConfig.Detail_semester_4, DataSetConfig.Status_semester_4, DataSetConfig.GpaValue_semester_4);
        }
    }

    public void intoArraysDataSemester5(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_5 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_5.length);
            DataSetConfig.Status_semester_5 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_5, DataSetConfig.Detail_semester_5.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_5, "Year III Semester I");
            semesterResultsTableSemester("Year3_Semester1", DataSetConfig.Code_semester_5, DataSetConfig.SubjectName_semester_5, DataSetConfig.Detail_semester_5, DataSetConfig.Status_semester_5, DataSetConfig.GpaValue_semester_5);
        }
    }

    public void intoArraysDataSemester6(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_semester_6 = DataSetConfig.dataIntoArray(text, DataSetConfig.Detail_semester_6.length);
            DataSetConfig.Status_semester_6 = DataSetConfig.statusIntoArray(DataSetConfig.Detail_semester_6, DataSetConfig.Detail_semester_6.length);
            semesterDetailTableSemester(DataSetConfig.Detail_semester_6, "Year III Semester II");
            semesterResultsTableSemester("Year3_Semester2", DataSetConfig.Code_semester_6, DataSetConfig.SubjectName_semester_6, DataSetConfig.Detail_semester_6, DataSetConfig.Status_semester_6, DataSetConfig.GpaValue_semester_6);
        }
    }

    public void intoArraysDataSemGpa(String text) {
        if (!text.equals("")) {
            DataSetConfig.Detail_sem_gpa = DataSetConfig.dataGpa(text, DataSetConfig.Detail_sem_gpa.length);
            sem_Gpa(DataSetConfig.Detail_sem_gpa);
        }
    }

    public boolean semesterDetailTableSemester(String[] array, String name) {
        boolean isInserted = databaseHelper.insertData_SEMESTER_DETAILS(name, array[array.length - 4], array[array.length - 3], array[array.length - 2], array[array.length - 1]);
        if (isInserted = true)
            return true;
        else
            return false;
    }

    public void sem_Gpa(String[] array) {
        databaseHelper.insertData_Sem_Gpa(array[0], array[1], array[2], array[3], array[4], array[5]);
    }

    public boolean semesterResultsTableSemester(String tb_name, String[] code, String[] sub_name, String[] result, String[] status, String[] gpa) {
        int y = 0;
        for (int i = 0; i < code.length; i++) {
            String temp = status[i];
            boolean isInserted = databaseHelper.insertDataSemesters(tb_name, code[i], sub_name[i], result[i], status[i], gpa[i]);
            if (isInserted = false)
                break;
            else
                y++;
            //-----------------------------------------------------
            if (temp.equals("PASS")) {
                boolean isInserted1 = databaseHelper.insertDataOthers("Pass_Subjects", code[i], sub_name[i], result[i]);
                if (isInserted1 = false)
                    break;
            } else if (temp.equals("REPEAT") || temp.equals("LESS_ATTENDS")) {
                boolean isInserted2 = databaseHelper.insertDataOthers("Repeat_Subjects", code[i], sub_name[i], result[i]);
                if (isInserted2 = false)
                    break;
            } else if (temp.equals("NOT_ATTEND")) {
                boolean isInserted3 = databaseHelper.insertDataOthers("Not_Attend_Subjects", code[i], sub_name[i], result[i]);
                if (isInserted3 = false)
                    break;
            }
        }
        if (y == code.length)
            return true;
        else
            return false;
    }

    public final boolean isInternetOn() {
        ConnectivityManager connec = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            // MESSAGE TO SCREEN FOR TESTING (IF REQ)
            //Toast.makeText(this, connectionType + ” connected”, Toast.LENGTH_SHORT).show();
            return true;
        } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }


}
