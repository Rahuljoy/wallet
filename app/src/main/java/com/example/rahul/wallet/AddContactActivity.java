package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rahul.wallet.interfaces.AddContactService;
import com.example.rahul.wallet.model.AddContactObject;
import com.example.rahul.wallet.model.AddContactRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddContactActivity extends AppCompatActivity {

    TextView accountTransactionTypeView;
    EditText contactNoText;
    EditText accountNameText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        accountTransactionTypeView = (TextView) findViewById(R.id.accountTransactionTypeView);
        contactNoText = (EditText) findViewById(R.id.contactNoText);
        accountNameText = (EditText) findViewById(R.id.accountNameText);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountTransactionType = accountTransactionTypeView.getText().toString();
                String contactNo = contactNoText.getText().toString();
                String accountName = accountNameText.getText().toString();

                if (validateAccountInformation(accountTransactionType,contactNo,accountName)) {
                    //do card Save
                    AccountInformationSave(accountTransactionType,contactNo,accountName);
                }
            }
        });

    }

    private boolean validateAccountInformation(String accountTransactionType, String contactNo, String accountName) {

        if (accountTransactionType == null || accountTransactionType.trim().length() == 0 ){
            Toast.makeText(this, "Account Transaction Type No is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (contactNo == null || contactNo.trim().length() == 0 ){
            Toast.makeText(this, "Contact No date is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (accountName == null || accountName.trim().length() == 0 ){
            Toast.makeText(this, "Account Name is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void AccountInformationSave(final String accountTransactionType, final String contactNo, final String accountName) {
        try {
            Bundle extras =getIntent().getExtras();
            final String user_id;
            if (extras != null) {
                user_id = extras.getString("user_id");

                final AddContactRequest addContactRequest = new AddContactRequest();
                addContactRequest.setUser_id(user_id);

                addContactRequest.setContact_no(contactNo);
                addContactRequest.setTransaction_type(accountTransactionType);
                addContactRequest.setAccount_name(accountName);

                AddContactService addContactService= RetrofitClient.getClient().create(AddContactService.class);
                final Call<AddContactObject> addContactObjectCall= addContactService.addAccountContact(user_id,addContactRequest);
                addContactObjectCall.enqueue(new Callback<AddContactObject>() {
                    @Override
                    public void onResponse(Call<AddContactObject> call, Response<AddContactObject> response) {
                        if (response.isSuccessful()){
                            AddContactObject addContactObject= response.body();
                            if (addContactObject.getUser_id()!=null){
                                Intent intent = new Intent(AddContactActivity.this,AccountTransferActivity.class);
                                intent.putExtra("user_id",user_id);
//                                System.out.println("contact--------------->"+user_id);
                                startActivity(intent);
                                Toast.makeText(AddContactActivity.this, "Account Save Successfully",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AddContactActivity.this, "Error Account",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddContactActivity.this,"Error!Try Again",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddContactObject> call, Throwable t) {
                        Toast.makeText(AddContactActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (Exception io){
            Log.d("TAG",io.getMessage());
        }

    }
}
