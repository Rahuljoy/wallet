package com.example.rahul.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TransactionMenuActivity extends AppCompatActivity {

    Button accountTransfer;
    Button mobileRecharge;
    Button onlinePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_menu);

        accountTransfer = (Button) findViewById(R.id.accountTransfer);
        accountTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                String user_id;
                if (extras != null) {
                    user_id = extras.getString("user_id");
//                    System.out.println("user id ----->"+user_id);
                    Toast.makeText(TransactionMenuActivity.this, "Transaction", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mobileRecharge = (Button) findViewById(R.id.mobileRecharge);
        mobileRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TransactionMenuActivity.this, "Mobile recharge", Toast.LENGTH_SHORT).show();
            }
        });


        onlinePayment = (Button) findViewById(R.id.onlinePayment);
        onlinePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TransactionMenuActivity.this, "Online Payment Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
