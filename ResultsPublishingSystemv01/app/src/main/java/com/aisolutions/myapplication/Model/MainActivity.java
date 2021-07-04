package com.aisolutions.myapplication.Model;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.multidex.MultiDex;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aisolutions.myapplication.Database.DatabaseHelper;
import com.aisolutions.myapplication.Details.SharedPrefs;
import com.aisolutions.myapplication.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import static com.aisolutions.myapplication.R.drawable.fade_fill;

public class MainActivity extends AppCompatActivity {
    TextView btn_logout, rankView, gpaView, nameView;
    RelativeLayout viewResults, viewPass, viewRepeat, viewNotAttend, viewSubject, viewGpa;
    DatabaseHelper databaseHelperMain;
    String[] data = new String[3];

    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_logout = findViewById(R.id.btn_logout);
        viewResults = findViewById(R.id.viewResult);
        viewPass = findViewById(R.id.viewPass);
        viewRepeat = findViewById(R.id.viewRepeat);
        viewNotAttend = findViewById(R.id.viewNotAttend);
        viewSubject = findViewById(R.id.viewSubject);
        viewGpa = findViewById(R.id.viewGpa);
        rankView = findViewById(R.id.main_rank_txt);
        nameView = findViewById(R.id.walletuser);
        gpaView = findViewById(R.id.main_gpa_text);

        databaseHelperMain = new DatabaseHelper(this);

        data = getData();
        rankView.setText(data[0]);
        gpaView.setText(data[1]);
        nameView.setText(data[2]);

        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultsMain.class);
                startActivity(intent);
            }
        });

        viewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategorizeResults.class);
                intent.putExtra("category", "Pass Subjects");
                startActivity(intent);
            }
        });

        viewRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategorizeResults.class);
                intent.putExtra("category", "Repeat Subjects");
                startActivity(intent);
            }
        });

        viewNotAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategorizeResults.class);
                intent.putExtra("category", "Not Attended Subjects");
                startActivity(intent);
            }
        });

        viewSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubjectsMain.class);
                startActivity(intent);
            }
        });

        viewGpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Gpa.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefs.saveSharedSetting(MainActivity.this, "MyLogin", "true");
                //And when you click on Logout button, You will set the value to True AGAIN
                Intent LogOut = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LogOut);
                databaseHelperMain.onLogout(MainActivity.this);
                finish();
            }
        });
        CekSession();

        //------------char setting--------------------------
        chart = findViewById(R.id.chart1);
        float[] array = getDataChart();
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(array), "Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();

        chart.setNoDataText("No data to Preview");
        chart.setDrawBorders(false);
        // chart.setBorderColor(Color.BLUE);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setSpaceMax(1);
        chart.getXAxis().setAxisMinimum((float) 0.5);
        chart.getXAxis().setAxisMaximum((float) 6.5);

        chart.animateXY(0, 4000, Easing.EaseInBounce, Easing.EaseInCirc);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        lineDataSet1.setLineWidth(3);
        lineDataSet1.setColor(Color.LTGRAY);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCircleRadius(5);
        lineDataSet1.setCircleColor(Color.RED);
        lineDataSet1.setValueTextSize(15);
        lineDataSet1.setValueTextColor(Color.BLUE);

    }

    private void CekSession() {
        Boolean Check = Boolean.valueOf(SharedPrefs.readSharedSetting(MainActivity.this, "MyLogin", "true"));

        Intent introIntent = new Intent(MainActivity.this, LoginActivity.class);
        introIntent.putExtra("MyLogin", Check);

        //The Value if you click on Login Activity and Set the value is FALSE and whe false the activity will be visible
        if (Check) {
            startActivity(introIntent);
            finish();
        } //If no the Main Activity not Do Anything
    }

    public String[] getData() {
        String[] data = new String[3];

        Cursor cursor = databaseHelperMain.getAllData("Details");

        while (cursor.moveToNext()) {
            data[0] = cursor.getString(4);
            data[1] = cursor.getString(3);
            data[2] = cursor.getString(2);
        }

        return data;
    }

    //----------------------------------------------------------------------------------------------
    public float[] getDataChart() {
        float[] data = new float[6];

        Cursor cursor = databaseHelperMain.getAllData("Sem_Gpa");

        while (cursor.moveToNext()) {
            data[0] = Float.parseFloat(cursor.getString(0));
            data[1] = Float.parseFloat(cursor.getString(1));
            data[2] = Float.parseFloat(cursor.getString(2));
            data[3] = Float.parseFloat(cursor.getString(3));
            data[4] = Float.parseFloat(cursor.getString(4));
            data[5] = Float.parseFloat(cursor.getString(5));
        }

        return data;
    }

    private ArrayList<Entry> dataValues1(float[] array) {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(1, array[0]));
        dataVals.add(new Entry(2, array[1]));
        dataVals.add(new Entry(3, array[2]));
        dataVals.add(new Entry(4, array[3]));
        dataVals.add(new Entry(5, array[4]));
        dataVals.add(new Entry(6, array[5]));

        return dataVals;
    }

}
