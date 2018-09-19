package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    Button cardSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        cardSaveButton = (Button) findViewById(R.id.cardSaveButton);
        cardSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddCardActivity.this, "Card Add Successfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(AddCardActivity.this,ViewCardActivity.class);
                startActivity(intent);
            }
        });
    }
}
