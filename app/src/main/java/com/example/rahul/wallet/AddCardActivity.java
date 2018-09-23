package com.example.rahul.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rahul.wallet.interfaces.AddCardService;
import com.example.rahul.wallet.model.AddCardObject;
import com.example.rahul.wallet.model.AddCardRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCardActivity extends AppCompatActivity {

    EditText cardNoText;
    EditText expairDateText;
    EditText fullNameText;
    EditText cvcText;
    Button cardSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        cardNoText = (EditText) findViewById(R.id.cardNoText);
        expairDateText = (EditText) findViewById(R.id.expairDateText);
        fullNameText = (EditText) findViewById(R.id.fullNameText);
        cvcText = (EditText) findViewById(R.id.cvcText);


        cardSaveButton = (Button) findViewById(R.id.cardSaveButton);
        cardSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardno = cardNoText.getText().toString();
                String expairdate = expairDateText.getText().toString();
                String fullname = fullNameText.getText().toString();
                String cvc = cvcText.getText().toString();

                if (validateCardInformation(cardno,expairdate,fullname,cvc)) {
                    //do card Save
                    cardSave(cardno,expairdate,fullname,cvc);
                }
            }
        });
    }

    private boolean validateCardInformation(String cardno, String expairdate, String fullname, String cvc) {

        if (cardno == null || cardno.trim().length() == 0 ){
            Toast.makeText(this, "Card No is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (expairdate == null || expairdate.trim().length() == 0 ){
            Toast.makeText(this, "expair date is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fullname == null || fullname.trim().length() == 0 ){
            Toast.makeText(this, "full name is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cvc == null || cvc.trim().length() == 0 ){
            Toast.makeText(this, "cvc is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void cardSave(final String cardno, final String expairdate, final String fullname, final String cvc) {
            try{
                Bundle extras =getIntent().getExtras();
                final String user_id;
                if (extras != null) {
                    user_id = extras.getString("user_id");
//                    System.out.println("UserIdForCard----->" + user_id);
                    final AddCardRequest addCardRequest = new AddCardRequest();
                    addCardRequest.setUser_id(user_id);

                    addCardRequest.setCard_no(cardno);
                    addCardRequest.setExpair_date(expairdate);
                    addCardRequest.setFull_name(fullname);
                    addCardRequest.setC_v_s_code(cvc);

                 //   Do here
                    AddCardService addCardService = RetrofitClient.getClient().create(AddCardService.class);
                    final Call<AddCardObject> addCardObjectCall = addCardService.addCard(user_id,addCardRequest);
                    addCardObjectCall.enqueue(new Callback<AddCardObject>() {
                        @Override
                        public void onResponse(Call<AddCardObject> call, Response<AddCardObject> response) {
                            if (response.isSuccessful()){
                                AddCardObject addCardObject=response.body();
                                if (addCardObject.getCard_type()!=null){
                                    if (addCardObject.getBalance()!=null){
                                        if (addCardObject.getCard_pin()!=null){
                                            Intent intent=new Intent(AddCardActivity.this,ViewCardActivity.class);
                                            intent.putExtra("user_id",addCardRequest.getUser_id());
//                                            System.out.println("User id for card -------->" + addCardRequest.getUser_id() );
                                            startActivity(intent);
                                            Toast.makeText(AddCardActivity.this, "Card Add Successfully",Toast.LENGTH_LONG).show();
                                            //this line will shift
                                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                                    .putBoolean("runFastCardAdd", false).apply();
                                        }else {
                                            Toast.makeText(AddCardActivity.this, "Card Pin Wrong",Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(AddCardActivity.this, "Card Balance Wrong",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(AddCardActivity.this, "Card Type Wrong",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(AddCardActivity.this, "Error!Try again",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCardObject> call, Throwable t) {
                            Toast.makeText(AddCardActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }catch (Exception io){
                Log.d("TAG",io.getMessage());
            }

    }

}
