package com.example.rahul.wallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.rahul.wallet.classes.GMailSender;

public class ForgetPasswordEmailActivity extends AppCompatActivity {

    TextView emailView;
    Button confirmEmailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_email);

        emailView=(TextView) findViewById(R.id.emailTextView);

        Bundle extras =getIntent().getExtras();
        String e_mail;
        if (extras != null){
            e_mail=extras.getString("e_mail");
            emailView.setText(e_mail);
        }

//        emailView.getText().toString();
        confirmEmailButton = (Button) findViewById(R.id.confirmEmailButton);

        confirmEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage();

            }
        });

    }

    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(ForgetPasswordEmailActivity.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();

        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("onlinebank707@gmail.com", "rahul rafi707");
                    sender.sendMail("Confirmation Password ",
                            "Password reset successfully.Now login with user name and password ",
                            "onlinebank707@gmail.com",
                            emailView.getText().toString());
                    dialog.dismiss();

                    Intent intent = new Intent(ForgetPasswordEmailActivity.this,LoginActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    Log.e("sendMail", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}
