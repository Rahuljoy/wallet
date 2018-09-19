package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ViewCardActivity extends AppCompatActivity {

    ImageButton addCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        addCardButton = (ImageButton) findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewCardActivity.this, "Coming Soon",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewCardActivity.this,AddCardActivity.class);
                startActivity(intent);
            }
        });
    }
}
