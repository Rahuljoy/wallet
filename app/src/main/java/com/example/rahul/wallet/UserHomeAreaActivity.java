package com.example.rahul.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeAreaActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView viewUsername;
    Switch logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_area);

        viewUsername = (TextView) findViewById(R.id.viewUsername);

        Bundle extras =getIntent().getExtras();
        String username;

        if (extras != null){
            username=extras.getString("username");
            viewUsername.setText(username);

        }

        logOut = (Switch) findViewById(R.id.logOut);

        logOut.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (logOut.isChecked()){
            finish();
            Toast.makeText(UserHomeAreaActivity.this,"Log Out Safely ",Toast.LENGTH_SHORT).show();
        }

    }
}
