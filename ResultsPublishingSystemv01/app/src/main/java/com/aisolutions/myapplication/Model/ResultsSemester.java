package com.aisolutions.myapplication.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aisolutions.myapplication.Adapter.ResultsAdapter;
import com.aisolutions.myapplication.Database.DatabaseHelper;
import com.aisolutions.myapplication.R;

import java.util.ArrayList;

public class ResultsSemester extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout noItem;
    DatabaseHelper databaseHelper;
    ResultsAdapter resultsAdapter;
    ImageView mainBack;

    ArrayList<String> code, name, result, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_semester);

        recyclerView = findViewById(R.id.semesterRecycler);
        noItem = findViewById(R.id.no_itemsView);
        mainBack = findViewById(R.id.circle2);

        databaseHelper = new DatabaseHelper(this);

        code = new ArrayList<>();
        name = new ArrayList<>();
        result = new ArrayList<>();
        status = new ArrayList<>();

        // add back arrow to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.cutom_appbar);
        getSupportActionBar().setBackgroundDrawable(d);

        Intent intent = getIntent();
        String title = intent.getStringExtra("semester");

        // set title
        getSupportActionBar().setTitle(title);

        String tb_name = findTable(title);

        displayData(tb_name);

        resultsAdapter = new ResultsAdapter(ResultsSemester.this, code, name, status, result);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ResultsSemester.this));
    }

    private String findTable(String title) {
        String tb_name = "";
        if (title.equals("Year I Semester I"))
            tb_name = "Year1_Semester1";
        else if (title.equals("Year I Semester II"))
            tb_name = "Year1_Semester2";
        else if (title.equals("Year II Semester I"))
            tb_name = "Year2_Semester1";
        else if (title.equals("Year II Semester II"))
            tb_name = "Year2_Semester2";
        else if (title.equals("Year III Semester I"))
            tb_name = "Year3_Semester1";
        else if (title.equals("Year III Semester II"))
            tb_name = "Year3_Semester2";
        return tb_name;
    }

    public void displayData(String tableName) {
        Cursor cursor = databaseHelper.getAllData(tableName);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                code.add(cursor.getString(1));
                name.add(cursor.getString(2));
                result.add(cursor.getString(3));
                status.add(cursor.getString(4));
            }
        }
        if (cursor == null || cursor.getCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            mainBack.setVisibility(View.GONE);
            noItem.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
