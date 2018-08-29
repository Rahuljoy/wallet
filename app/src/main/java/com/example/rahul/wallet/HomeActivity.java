package com.example.rahul.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView viewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUsername = (TextView) findViewById(R.id.viewUsername);

        Bundle extras =getIntent().getExtras();
        String username;

        if (extras != null){
            username=extras.getString("username");
            viewUsername.setText(username);

        }
    }
}
