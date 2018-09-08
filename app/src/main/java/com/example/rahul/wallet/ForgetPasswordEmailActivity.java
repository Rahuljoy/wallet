package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rahul.wallet.classes.GMailSender;

public class ForgetPasswordEmailActivity extends AppCompatActivity {

    TextView emailView;
    Button confirmEmailButton;
private String e_mail=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_email);

        emailView=(TextView) findViewById(R.id.emailTextView);

        Bundle extras =getIntent().getExtras();
//        String e_mail;
        if (extras != null){
            e_mail=extras.getString("e_mail");
            emailView.setText(e_mail);
        }

        confirmEmailButton = (Button) findViewById(R.id.confirmEmailButton);

        confirmEmailButton.setOnClickListener(new View.OnClickListener() {
//            String e_mail;
            @Override
            public void onClick(View v) {
                try {
                    e_mail=emailView.getText().toString();
                    GMailSender sender = new GMailSender("onlinebank707@gmail.com", "rahul rafi707");
                    sender.sendMail("Password Confirmation",
                            "password reset Successfully",
                            "onlinebank707@gmail.com",
                            e_mail);
                }catch (Exception e){
                    Log.e("SendMail",e.getMessage(),e);
                }
                Intent intent = new Intent(ForgetPasswordEmailActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(ForgetPasswordEmailActivity.this, "Confirmation send successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
