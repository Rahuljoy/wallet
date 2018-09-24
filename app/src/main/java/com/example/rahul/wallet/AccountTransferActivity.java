package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccountTransferActivity extends AppCompatActivity {

    ImageButton addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_transfer);

        addContactButton = (ImageButton) findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle extras = getIntent().getExtras();
                String user_id;
                if (extras != null) {
                    user_id = extras.getString("user_id");
                    Intent intent = new Intent(AccountTransferActivity.this,AddContactActivity.class);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                }

            }
        });

    }
}
