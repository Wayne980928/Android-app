package com.example.myapp0;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public String ans ;
    private EditText Firstname;
    private EditText Lastname;
    private Button btn;
    private TextView outputText;
    private ArrayList<String> name;
    private ArrayList<String> name_f;
    private ArrayList<String> name_l;
    public ArrayList<String> number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        outputText = findViewById(R.id.OutText);
        Lastname = findViewById(R.id.editText2);
        Firstname = findViewById(R.id.editText3);
        btn = findViewById(R.id.tag);
        name_f = new ArrayList<>();
        name_f.add("Kawamitsu");
        name_l = new ArrayList<>();
        name_l.add("Makoto");
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String F_name = Firstname.getText().toString();
                String L_name = Lastname.getText().toString();
                String full_name = F_name + L_name;
                StringBuilder stb = new StringBuilder();
                if(full_name.equals(("KawamitsuMakoto"))){
                    stb.append("January 28th");
                    outputText.setText(stb);
                    ans = "January";
                }
            }
        });
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                if (ans.equals("January")){
                    intent.putExtra("month", "1");
                    intent.putExtra("day","28");
            }
                startActivity(intent);
            }
    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
