package com.aisolutions.myapplication.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.aisolutions.myapplication.R;

public class Gpa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        // add back arrow to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable d = getResources().getDrawable(R.drawable.cutom_appbar);
        getSupportActionBar().setBackgroundDrawable(d);

        // set title
        getSupportActionBar().setTitle("Grading System");
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
