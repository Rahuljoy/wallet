package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rahul.wallet.model.LoginObject;
import com.example.rahul.wallet.model.LoginRequest;
import com.example.rahul.wallet.interfaces.LoginService;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText userNameText;
    EditText passwordText;
    Button loginButton;
    Button forgetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameText = (EditText) findViewById(R.id.userNameText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = userNameText.getText().toString();
                String userpassword = passwordText.getText().toString();
                    //validetion
                if (validateLogin(username, userpassword)) {
                    //do login
                    userLogin(username, userpassword);
                }
            }
        });


        forgetPasswordButton = (Button) findViewById(R.id.forgetPasswordButton);

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RecoverPasswordActivity.class));
            }
        });

    }


    private boolean validateLogin(String username ,String userpassword){
        if (username == null || username.trim().length() == 0 ){
            Toast.makeText(this, "username is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (userpassword == null || userpassword.trim().length() == 0 ){
            Toast.makeText(this, "password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void userLogin(final String username, final String userpassword){
try{
    LoginRequest loginRequest = new LoginRequest();

    loginRequest.setUser_name(username);
    loginRequest.setUser_password(userpassword);

    LoginService loginService = RetrofitClient.getClient().create(LoginService.class);
    final Call<LoginObject> loginObjectCall = loginService.login(loginRequest);
    loginObjectCall.enqueue(new Callback<LoginObject>() {

        @Override
        public void onResponse(Call<LoginObject> call, Response<LoginObject> response) {
            if (response.isSuccessful()){
                LoginObject loginObject = response.body();
                   if (loginObject.getUser_id()!=null){
                       if (loginObject.getUser_active()!=null){

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("runFastCardAdd", true);
//        System.out.println("IsFirst ---> " + isFirstRun);
        if (isFirstRun){
            Intent intent = new Intent(LoginActivity.this,AddCardActivity.class);
            intent.putExtra("user_id",loginObject.getUser_id());
            startActivity(intent);
            Toast.makeText(LoginActivity.this,"Please Add Your Card",Toast.LENGTH_SHORT).show();

        } else  {
            Intent intent = new Intent(LoginActivity.this,UserHomeAreaActivity.class);
            intent.putExtra("user_id",loginObject.getUser_id());
            startActivity(intent);
            Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
        }
                       } else {
                           Toast.makeText(LoginActivity.this,"user Not active",Toast.LENGTH_SHORT).show();
                       }
                    } else {
                        Toast.makeText(LoginActivity.this,"username or password is incorrect",Toast.LENGTH_SHORT).show();
                    }
            } else {
                Toast.makeText(LoginActivity.this, "Error!Try again",Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        public void onFailure(Call<LoginObject> call, Throwable t) {
            Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
} catch (Exception io){
    Log.d("TAG",io.getMessage());
}
   }
}
