package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rahul.wallet.interfaces.MobileRechargeService;
import com.example.rahul.wallet.model.MobileRechargeObject;
import com.example.rahul.wallet.model.MobileRechargeRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeActivity extends AppCompatActivity {

    EditText numberText;
    EditText amountText;
    EditText passwordText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);

        numberText = (EditText) findViewById(R.id.numberText);
        amountText = (EditText) findViewById(R.id.amountText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactno = numberText.getText().toString();
                String amount = amountText.getText().toString();
                String password = passwordText.getText().toString();

                if (validaterecharge(contactno, amount, password)) {
                    rechargetransaction(contactno, amount, password);
                }

            }
        });
    }

    private boolean validaterecharge(String contactno, String amount, String password) {
        if (contactno == null || contactno.trim().length() == 0) {
            Toast.makeText(this, "contact no is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (amount == null || amount.trim().length() == 0) {
            Toast.makeText(this, "amount is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void rechargetransaction(final String contactno, final String amount, final String password) {
        try {
            Bundle extras = getIntent().getExtras();
            final String user_id;
            if (extras != null) {
                user_id = extras.getString("user_id");
//                    System.out.println("UserIdForCard----->" + user_id);
                final MobileRechargeRequest mobileRechargeRequest = new MobileRechargeRequest();
                mobileRechargeRequest.setUser_id(user_id);

                mobileRechargeRequest.setContact_no(contactno);
                mobileRechargeRequest.setTransactions_amount(amount);
                mobileRechargeRequest.setUser_password(password);

                MobileRechargeService mobileRechargeService = RetrofitClient.getClient().create(MobileRechargeService.class);
                final Call<MobileRechargeObject> mobileRechargeObjectCall = mobileRechargeService.mobilerecharge(user_id, mobileRechargeRequest);
                mobileRechargeObjectCall.enqueue(new Callback<MobileRechargeObject>() {
                    @Override
                    public void onResponse(Call<MobileRechargeObject> call, Response<MobileRechargeObject> response) {
                        if (response.isSuccessful()){
                            MobileRechargeObject mobileRechargeObject=response.body();
                            if (mobileRechargeObject.getTransaction_status()!=null){
                                Intent intent=new Intent(MobileRechargeActivity.this,UserHomeAreaActivity.class);
                                intent.putExtra("user_id",mobileRechargeRequest.getUser_id());
//                                            System.out.println("User id for card -------->" + addCardRequest.getUser_id() );
                                startActivity(intent);
                                Toast.makeText(MobileRechargeActivity.this, "Transaction Successfully Done",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MobileRechargeActivity.this, "Error ! Transaction failed",Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(MobileRechargeActivity.this, "Error!Try again",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<MobileRechargeObject> call, Throwable t) {
                        Toast.makeText(MobileRechargeActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
            }catch(Exception io){
                Log.d("TAG", io.getMessage());

            }
        }
}
