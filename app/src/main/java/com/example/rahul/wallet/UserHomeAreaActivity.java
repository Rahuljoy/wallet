package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.rahul.wallet.interfaces.DashboardService;
import com.example.rahul.wallet.model.DashboardObject;
import com.example.rahul.wallet.model.DashboardRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHomeAreaActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView viewUsername;
    TextView viewAccountNo;
    TextView viewAccountBalance;
    Switch logOut;
    Button transactionButton;
    Button StatementButton;
    Button cardButton;
    Button settingButton;


//private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_area);

        viewUsername = (TextView) findViewById(R.id.viewUsername);
        viewAccountNo = (TextView) findViewById(R.id.viewAccountNo);
        viewAccountBalance = (TextView) findViewById(R.id.viewAccountBalance);

        Bundle extras =getIntent().getExtras();
        if (extras != null){
           String user_id = extras.getString("user_id");
//System.out.println(user_id);
            dashboardInformation(user_id);
//            viewUsername.setText(user_id);
            viewAccountNo.setText(user_id);
            viewAccountBalance.setText(user_id);
        }

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

    private void dashboardInformation(final String user_id) {
        try{
            DashboardRequest dashboardRequest = new DashboardRequest();
            dashboardRequest.setUser_id(user_id);
//            System.out.println(user_id);
            DashboardService dashboardService = RetrofitClient.getClient().create(DashboardService.class);
            final Call<DashboardObject> dashboardObjectCall = dashboardService.userInformation(user_id);
            dashboardObjectCall.enqueue(new Callback<DashboardObject>() {
                @Override
                public void onResponse(Call<DashboardObject> call, Response<DashboardObject> response) {
                    if (response.isSuccessful()){
                        DashboardObject dashboardObject = response.body();
                        viewUsername.setText(dashboardObject.getUser_name());
                    }
                    else {
                        Toast.makeText(UserHomeAreaActivity.this,"Error!Try Again",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DashboardObject> call, Throwable t) {
                    Toast.makeText(UserHomeAreaActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception io){
            Log.d("TAG",io.getMessage());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (logOut.isChecked()){
            finish();
            Toast.makeText(UserHomeAreaActivity.this,"Log Out Safely ",Toast.LENGTH_SHORT).show();
        }

    }
}
