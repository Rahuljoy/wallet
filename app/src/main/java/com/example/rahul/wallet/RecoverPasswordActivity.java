package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rahul.wallet.interfaces.ForgetPasswordService;
import com.example.rahul.wallet.model.ForgetPasswordObject;
import com.example.rahul.wallet.model.ForgetPasswordRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecoverPasswordActivity extends AppCompatActivity {

    EditText userNameText;
    EditText passwordText;
    Button submitPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        userNameText = (EditText) findViewById(R.id.userNameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        submitPasswordButton = (Button) findViewById(R.id.submitPasswordButton);

        submitPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userNameText.getText().toString();
                String userpassword = passwordText.getText().toString();

                if (validateSubmit(username,userpassword)){
                    //do reset password
                    resetPassword(username,userpassword);
                }
            }
        });
    }
    private boolean validateSubmit(String username, String userpassword) {
        if (username == null || username.trim().length() == 0 ){
            Toast.makeText(this, "submit username ",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userpassword == null || userpassword.trim().length() == 0 ){
            Toast.makeText(this, "submit your new password ",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
//
    private void resetPassword(final String username, final String userpassword) {
        try{
            ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();

            forgetPasswordRequest.setUser_name(username);
            forgetPasswordRequest.setUser_password(userpassword);

            ForgetPasswordService forgetPasswordService = RetrofitClient.getClient().create(ForgetPasswordService.class);
            final Call<ForgetPasswordObject> forgetPasswordObjectCall = forgetPasswordService.recoverPassword(forgetPasswordRequest);

            forgetPasswordObjectCall.enqueue(new Callback<ForgetPasswordObject>() {
                @Override
                public void onResponse(Call<ForgetPasswordObject> call, Response<ForgetPasswordObject> response) {
                    ForgetPasswordObject forgetPasswordObject = response.body();
                    if (response.isSuccessful()){
                     if (forgetPasswordObject.getE_mail() !=null){
                         Intent intent = new Intent(RecoverPasswordActivity.this,ForgetPasswordEmailActivity.class);
                         intent.putExtra("e_mail",forgetPasswordObject.getE_mail());
                         startActivity(intent);
                         Toast.makeText(RecoverPasswordActivity.this, "password Submit!Please..Confirmation your e_mail to submit successfully",Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(RecoverPasswordActivity.this, "Error password Submit",Toast.LENGTH_SHORT).show();
                     }

                   } else {
                       Toast.makeText(RecoverPasswordActivity.this,"Error!Try Again",Toast.LENGTH_SHORT).show();
                   }
                }
                @Override
                public void onFailure(Call<ForgetPasswordObject> call, Throwable t) {
                    Toast.makeText(RecoverPasswordActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception io){
            Log.d("TAG",io.getMessage());
        }
    }
}
