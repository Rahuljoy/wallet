package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.rahul.wallet.interfaces.ContactNoService;
import com.example.rahul.wallet.interfaces.FundTransactionService;
import com.example.rahul.wallet.model.ContactNoObject;
import com.example.rahul.wallet.model.ContactNoRequest;
import com.example.rahul.wallet.model.FundTransactionObject;
import com.example.rahul.wallet.model.FundTransactionRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class AccountTransferActivity extends AppCompatActivity {

    EditText numberText;
    EditText amountText;
    EditText passwordText;
    Button sendButton;
    ImageButton addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_transfer);

        numberText=(EditText) findViewById(R.id.numberText);
        amountText=(EditText) findViewById(R.id.amountText);
        passwordText=(EditText) findViewById(R.id.passwordText);

        sendButton=(Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contactno = numberText.getText().toString();
                String amount = amountText.getText().toString();
                String password = passwordText.getText().toString();

                if (validateTransactionInformation(contactno,amount,password)) {
                    //do transaction
                    fundtransaction(contactno,amount,password);
                }
            }
        });

        addContactButton = (ImageButton) findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle extras = getIntent().getExtras();
                String user_id;
                if (extras != null) {
                    user_id = extras.getString("user_id");
                    Intent intent = new Intent(AccountTransferActivity.this,AddContactActivity.class);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                }

            }
        });

    }

    private boolean validateTransactionInformation(String contactno, String amount, String password) {
        if (contactno == null || contactno.trim().length() == 0 ){
            Toast.makeText(this, "contact no is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (amount == null || amount.trim().length() == 0 ){
            Toast.makeText(this, "amount is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password == null || password.trim().length() == 0 ){
            Toast.makeText(this, "password is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void fundtransaction(final String contactno, final String amount, final String password) {
        try {
            Bundle extras =getIntent().getExtras();
            final String user_id;
            if (extras != null) {
                user_id = extras.getString("user_id");
//                    System.out.println("UserIdForCard----->" + user_id);
                final FundTransactionRequest fundTransactionRequest= new FundTransactionRequest();
                fundTransactionRequest.setUser_id(user_id);

                fundTransactionRequest.setContact_no(contactno);
                fundTransactionRequest.setTransactions_amount(amount);
                fundTransactionRequest.setUser_password(password);

                FundTransactionService  fundTransactionService = RetrofitClient.getClient().create(FundTransactionService.class);
                final Call<FundTransactionObject> fundTransactionObjectCall= fundTransactionService.fundtrunsaction(user_id,fundTransactionRequest);
                fundTransactionObjectCall.enqueue(new Callback<FundTransactionObject>() {
                    @Override
                    public void onResponse(Call<FundTransactionObject> call, Response<FundTransactionObject> response) {
                        if (response.isSuccessful()){
                            FundTransactionObject fundTransactionObject=response.body();
                            if (fundTransactionObject.getTransaction_status().equals("Successfully")){
                                Intent intent=new Intent(AccountTransferActivity.this,UserHomeAreaActivity.class);
                                intent.putExtra("user_id",fundTransactionRequest.getUser_id());
//                                            System.out.println("User id for card -------->" + addCardRequest.getUser_id() );
                                startActivity(intent);
                                Toast.makeText(AccountTransferActivity.this, "Transaction Successfully Done",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AccountTransferActivity.this, "Error ! Transaction failed",Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(AccountTransferActivity.this, "Error!Try again",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FundTransactionObject> call, Throwable t) {
                        Toast.makeText(AccountTransferActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
 }
        }catch (Exception io){
            Log.d("TAG",io.getMessage());
        }
    }


}
