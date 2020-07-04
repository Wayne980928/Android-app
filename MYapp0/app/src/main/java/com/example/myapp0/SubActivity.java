package com.example.myapp0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


public class SubActivity extends AppCompatActivity {
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;
    public String answ;
    public String answ0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        answ0 = intent.getStringExtra("month");
        answ = intent.getStringExtra("day");

        setContentView(R.layout.cell_layout);


        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this,answ0,answ);
        calendarGridView.setAdapter(mCalendarAdapter);

    }
}
