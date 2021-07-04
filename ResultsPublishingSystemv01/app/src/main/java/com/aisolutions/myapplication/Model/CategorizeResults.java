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

import com.aisolutions.myapplication.Adapter.CategorizeAdapter;
import com.aisolutions.myapplication.Database.DatabaseHelper;
import com.aisolutions.myapplication.R;

import java.util.ArrayList;

public class CategorizeResults extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout noItem, repeatItem;
    DatabaseHelper databaseHelper;
    CategorizeAdapter categorizeAdapter;
    ImageView mainBack;
    String cat="";

    ArrayList<String> code, name, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorize_results);

        recyclerView = findViewById(R.id.mainRecycler);
        noItem = findViewById(R.id.no_itemsView);
        repeatItem = findViewById(R.id.no_repeat_itemsView);
        mainBack = findViewById(R.id.circle2);

        databaseHelper = new DatabaseHelper(this);

        code = new ArrayList<>();
        name = new ArrayList<>();
        result = new ArrayList<>();

        // add back arrow to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.cutom_appbar);
        getSupportActionBar().setBackgroundDrawable(d);

        Intent intent =getIntent();
        cat = intent.getStringExtra("category");

        getSupportActionBar().setTitle(cat);

        String tb_name = findTable(cat);

        displayData(tb_name);

        categorizeAdapter = new CategorizeAdapter(CategorizeResults.this,code,name,result,cat);
        recyclerView.setAdapter(categorizeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategorizeResults.this));

    }

    private String findTable(String cat){
        String tb_name ="";

        if (cat.equals("Pass Subjects")){
            tb_name = "Pass_Subjects";
        } else if (cat.equals("Repeat Subjects")){
            tb_name = "Repeat_Subjects";
        } else if (cat.equals("Not Attended Subjects")){
            tb_name = "Not_Attend_Subjects";
        }
        return tb_name;
    }

    public void displayData(String tb_name){
        Cursor cursor = databaseHelper.getAllData(tb_name);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                code.add(cursor.getString(1));
                name.add(cursor.getString(2));
                result.add(cursor.getString(3));
            }
        }
        if (cursor == null || cursor.getCount() == 0) {
            if (tb_name.equals("Repeat_Subjects")){
                recyclerView.setVisibility(View.GONE);
                mainBack.setVisibility(View.GONE);
                repeatItem.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
                mainBack.setVisibility(View.GONE);
                noItem.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
