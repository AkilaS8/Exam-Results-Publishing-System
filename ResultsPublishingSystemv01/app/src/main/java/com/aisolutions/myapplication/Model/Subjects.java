package com.aisolutions.myapplication.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.aisolutions.myapplication.Adapter.SubjectsAdapter;
import com.aisolutions.myapplication.Details.DataSetConfig;
import com.aisolutions.myapplication.R;

public class Subjects extends AppCompatActivity {

    RecyclerView recyclerView;
    SubjectsAdapter subjectsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        recyclerView = findViewById(R.id.subjectRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Intent intent =getIntent();
        String title = intent.getStringExtra("sem");

        // add back arrow to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.cutom_appbar);
        getSupportActionBar().setBackgroundDrawable(d);
        getSupportActionBar().setTitle(title);

        if (title.equals("Year I Semester I")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_1,DataSetConfig.SubjectName_semester_1,DataSetConfig.GpaValue_semester_1);
            recyclerView.setAdapter(subjectsAdapter);
        } else if (title.equals("Year I Semester II")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_2,DataSetConfig.SubjectName_semester_2,DataSetConfig.GpaValue_semester_2);
            recyclerView.setAdapter(subjectsAdapter);
        } else if (title.equals("Year II Semester I")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_3,DataSetConfig.SubjectName_semester_3,DataSetConfig.GpaValue_semester_3);
            recyclerView.setAdapter(subjectsAdapter);
        } else if (title.equals("Year II Semester II")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_4,DataSetConfig.SubjectName_semester_4,DataSetConfig.GpaValue_semester_4);
            recyclerView.setAdapter(subjectsAdapter);
        } else if (title.equals("Year III Semester I")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_5,DataSetConfig.SubjectName_semester_5,DataSetConfig.GpaValue_semester_5);
            recyclerView.setAdapter(subjectsAdapter);
        } else if (title.equals("Year III Semester II")){
            subjectsAdapter = new SubjectsAdapter(this, DataSetConfig.Code_semester_6,DataSetConfig.SubjectName_semester_6,DataSetConfig.GpaValue_semester_6);
            recyclerView.setAdapter(subjectsAdapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
