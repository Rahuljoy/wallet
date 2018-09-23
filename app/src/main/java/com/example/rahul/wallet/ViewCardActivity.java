package com.example.rahul.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rahul.wallet.interfaces.ViewCardService;
import com.example.rahul.wallet.model.ViewCardObject;
import com.example.rahul.wallet.model.ViewCardRequest;
import com.example.rahul.wallet.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCardActivity extends AppCompatActivity {

//    ImageView cardImageView;
    TextView viewCard;
    TextView viewFullName;
    TextView viewAccountBalance;
    TextView viewExpairDate;
    ImageButton addCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

//        cardImageView=(ImageView) findViewById(R.id.cardImageView);
        viewCard=(TextView) findViewById(R.id.viewCard);
        viewFullName=(TextView) findViewById(R.id.viewFullName);
        viewAccountBalance=(TextView) findViewById(R.id.viewAccountBalance);
        viewExpairDate=(TextView) findViewById(R.id.viewExpairDate);

        Bundle extras =getIntent().getExtras();
        String user_id;
        if (extras != null){
            user_id = extras.getString("user_id");

            viewCardInformation(user_id);
//            viewAccountBalance.setText(user_id);
        }

        addCardButton = (ImageButton) findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewCardActivity.this, "Coming Soon",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void viewCardInformation(final String user_id) {
        try{
            ViewCardRequest viewCardRequest = new ViewCardRequest();
            viewCardRequest.setUser_id(user_id);

            ViewCardService viewCardService = RetrofitClient.getClient().create(ViewCardService.class);
            final Call<ViewCardObject> viewCardObjectCall = viewCardService.cardInformation(user_id);
            viewCardObjectCall.enqueue(new Callback<ViewCardObject>() {
                @Override
                public void onResponse(Call<ViewCardObject> call, Response<ViewCardObject> response) {
                    if (response.isSuccessful()){
                        ViewCardObject viewCardObject = response.body();
                        if (viewCardObject.getCard_type()!=null) {
                            viewCard.setText(viewCardObject.getCard_type());
                            if (viewCardObject.getFull_name() != null) {
                                viewFullName.setText(viewCardObject.getFull_name());
                                if (viewCardObject.getBalance() != null) {
                                    viewAccountBalance.setText(viewCardObject.getBalance());
                                    if (viewCardObject.getExpair_date() != null) {
                                        viewExpairDate.setText(viewCardObject.getExpair_date());
                                    } else {
                                        Toast.makeText(ViewCardActivity.this, "Expair Date Error", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ViewCardActivity.this, "Account Balance Problem", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ViewCardActivity.this, "username is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ViewCardActivity.this, "Card type Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(ViewCardActivity.this,"Error!Try Again for load the card",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ViewCardObject> call, Throwable t) {
                    Toast.makeText(ViewCardActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception io){
            Log.d("TAG",io.getMessage());
        }
    }
}
