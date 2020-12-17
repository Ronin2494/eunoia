package com.example.eunoia;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class expertProfile extends AppCompatActivity {

    ImageView img, back;
    TextView fname, lname, city, education, speciality, description;
    Dialog dialog;
    Button askBtn;

    private Button paymentBtn;
    private ImageView logo_E;

    private int PayPal_REQ_CODE = 7171;

    private  static PayPalConfiguration paypalConfig = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(paypalClientID.paypal_Client_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_profile);


        img= findViewById(R.id.profileImage);
        fname = findViewById(R.id.drFirstName);
        lname = findViewById(R.id.drlastName);
        city = findViewById(R.id.drCity);
        education = findViewById(R.id.education);
        speciality = findViewById(R.id.speciality);
        description = findViewById(R.id.description);

        back =findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(expertProfile.this, expert.class);
                startActivity(i);
            }
        });


        Intent intent = getIntent();

        String fn = intent.getStringExtra("name");
        String ln = intent.getStringExtra("last");
        String c = intent.getStringExtra("city");
        int image = intent.getIntExtra("image", 0);
        String ed = intent.getStringExtra("qualification");
        String special = intent.getStringExtra("speciality");
        String desc = intent.getStringExtra("description");


        fname.setText(fn);
        lname.setText(ln);
        city.setText(c);
        img.setImageResource(image);
        education.setText(ed);
        speciality.setText(special);
        description.setText(desc);



        //for paypal
        askBtn = findViewById(R.id.chat);

        askBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog= new Dialog(expertProfile.this);
                dialog.setContentView(R.layout.subscription);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                logo_E = (ImageView) dialog.findViewById(R.id.logo_1);
                paymentBtn = dialog.findViewById(R.id.subscibeButton);

                Intent in = new Intent(expertProfile.this, PayPalService.class);
                in.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
                startService(in);

                paymentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PaypalPayment();
                    }


                });
            }
        });


    }



    private void PaypalPayment(){

        PayPalPayment payment = new PayPalPayment(new BigDecimal(50), "USD",
                "Test payment", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, PayPal_REQ_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PayPal_REQ_CODE){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "payment made successfully", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }else{
                Toast.makeText(this, "Payment Unsuccessful", Toast.LENGTH_SHORT ).show();
            }
        }
    }

    @Override
    protected void onDestroy() {

        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

}
