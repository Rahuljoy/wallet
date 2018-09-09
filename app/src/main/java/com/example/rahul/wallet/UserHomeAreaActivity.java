package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class UserHomeAreaActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView viewUsername;
    Switch logOut;
    Button transactionButton;
    Button StatementButton;
    Button cardButton;
    Button settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_area);

        Bundle extras =getIntent().getExtras();
        String user_id;
        if (extras != null){
            user_id=extras.getString("user_id");
            dashboardInformation(user_id);
        }
        viewUsername = (TextView) findViewById(R.id.viewUsername);
//        viewUsername.setText(user_id);

        logOut = (Switch) findViewById(R.id.logOut);
        logOut.setOnCheckedChangeListener(this);


        transactionButton = (Button) findViewById(R.id.transactionButton);
        transactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeAreaActivity.this, "Transaction",Toast.LENGTH_SHORT).show();
            }
        });

        StatementButton = (Button) findViewById(R.id.StatementButton);
        StatementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeAreaActivity.this, "Statement",Toast.LENGTH_SHORT).show();
            }
        });

        cardButton = (Button) findViewById(R.id.cardButton);
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeAreaActivity.this,ViewCardActivity.class);
                startActivity(intent);
                Toast.makeText(UserHomeAreaActivity.this, "card",Toast.LENGTH_SHORT).show();
            }
        });

        settingButton = (Button) findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeAreaActivity.this, "setting",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (logOut.isChecked()){
            finish();
            Toast.makeText(UserHomeAreaActivity.this,"Log Out Safely ",Toast.LENGTH_SHORT).show();
        }

    }
}
