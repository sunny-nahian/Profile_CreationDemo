package com.sunny.profilepage;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.list_txt);
        strings = new String[2];
        strings[0] = "AB";
        strings[1] = "3D";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.list_iten_txt, strings);

        listView.setAdapter(adapter);
    }
}