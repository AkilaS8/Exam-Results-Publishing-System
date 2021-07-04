package com.aisolutions.myapplication.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aisolutions.myapplication.Adapter.SemestersAdapter;
import com.aisolutions.myapplication.Database.DatabaseHelper;
import com.aisolutions.myapplication.R;

import java.util.ArrayList;

public class ResultsMain extends AppCompatActivity {
    RecyclerView recyclerViewMain;
    LinearLayout noItem;
    DatabaseHelper databaseHelper;
    SemestersAdapter semestersAdapter;
    ImageView mainBack;

    ArrayList<String> name, pass, repeat, notA, lessA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_main);

        recyclerViewMain = findViewById(R.id.mainRecycler);
        noItem = findViewById(R.id.no_itemsView);
        mainBack= findViewById(R.id.circle2);


        // add back arrow to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // set title
        getSupportActionBar().setTitle("Results");
        Drawable d = getResources().getDrawable(R.drawable.cutom_appbar);
        getSupportActionBar().setBackgroundDrawable(d);

        databaseHelper = new DatabaseHelper(this);

        name = new ArrayList<>();
        pass = new ArrayList<>();
        repeat = new ArrayList<>();
        notA = new ArrayList<>();
        lessA = new ArrayList<>();

        displayData();

        semestersAdapter = new SemestersAdapter(ResultsMain.this, name, pass, repeat, notA, lessA);
        recyclerViewMain.setAdapter(semestersAdapter);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(ResultsMain.this));
    }

    public void displayData() {
        Cursor cursor = databaseHelper.getAllData("Semester_Details");

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(1));
                pass.add(cursor.getString(2));
                repeat.add(cursor.getString(3));
                notA.add(cursor.getString(4));
                lessA.add(cursor.getString(5));
            }
        }
        if (cursor == null || cursor.getCount() == 0) {
            recyclerViewMain.setVisibility(View.GONE);
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
