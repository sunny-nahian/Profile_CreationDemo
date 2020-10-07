package com.sunny.profilepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Intent next_Activity;
    // For Button Intent adding
    ImageButton button;
    TextView bornUi, studentUi, studentIdUi, followersUi, counryUi, userNameUi;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_Activity = new Intent(this, UpdateProfileActivity.class);
        bornUi = (TextView) findViewById(R.id.bornUi) ;
        studentUi = (TextView) findViewById(R.id.studentUi) ;
        studentIdUi = (TextView) findViewById(R.id.studiesUI) ;
        followersUi = (TextView) findViewById(R.id.followersUi) ;
        counryUi = (TextView) findViewById(R.id.counryUi) ;
        userNameUi = (TextView) findViewById(R.id.userNameUi);

        sharedPreferences =  getSharedPreferences("userDetails", MODE_PRIVATE);
        bornUi.setText(sharedPreferences.getString("dateKey","DEFAULT_NAME"));
        studentIdUi.setText(sharedPreferences.getString("jodKey", "DEFAULT_NAME"));
        userNameUi.setText(sharedPreferences.getString("usernameKey", "DEFAULT_NAME"));

        // For Button intent adding
        button= findViewById(R.id.icon_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UpdateProfileActivity.class));
            }
        });  // the End Button intent Adding
    }
    // For Menu Layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.settingId){
            Toast.makeText(MainActivity.this,"String is Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.sharedId){
            // onno kothao message share kora
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "...Online Service...";
            String body = " This is very Helpful Applications.\n You can use it, Hopefully you satisfied.....\n thank you.\n com.sunny.profilepage";
            // for Sent/pathabo
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent," Visit Us"));
        } // sent share end
        if (item.getItemId()==R.id.feedbackId){
            Toast.makeText(MainActivity.this,"Feedback is Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.contractId){
            Toast.makeText(MainActivity.this,"Contract number is Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.aboutUsId){
            Toast.makeText(MainActivity.this,"About Us is Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //end Menu_Layout
}

