package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rahul.wallet.model.ResourceObject;
import com.example.rahul.wallet.remote.ApiUtils;
import com.example.rahul.wallet.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText userNameText;
    EditText passwordText;
    Button loginButton;
    UserService userService;
    Button forgetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameText = (EditText) findViewById(R.id.userNameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        userService = ApiUtils.getUserService();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameText.getText().toString();
                String password = passwordText.getText().toString();
                //validetion
                if (validateLogin(username,password)){
                    //do login
                    userLogin(username,password);

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

    private boolean validateLogin(String username ,String password){
        if (username == null || username.trim().length() == 0 ){
            Toast.makeText(this, "username is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password == null || password.trim().length() == 0 ){
            Toast.makeText(this, "password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void userLogin(String username, String password){

        Call<ResourceObject> call = userService.login(username,password);
        call.enqueue(new Callback<ResourceObject>() {
            @Override
            public void onResponse(Call<ResourceObject> call, Response<ResourceObject> response) {
                if (response.isSuccessful()){
                    ResourceObject resourceObject = response.body();
                    if (resourceObject.getMessage().equals("true")){

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }else {
                        Toast.makeText(LoginActivity.this,"username or password is incorrect",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Error!Try again",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResourceObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


   }

}
