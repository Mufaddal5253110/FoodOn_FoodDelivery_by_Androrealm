package com.food_on.app.CustomerFoodPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.food_on.app.R;
import com.google.android.material.textfield.TextInputLayout;

public class CustomerOnlinePayment extends AppCompatActivity {

    TextInputLayout cardname, cardnumber, expirydate, cvv;
    Button Addcard;
    String name, number, date, CVV;
    String RandomUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_online_payment);

        Addcard = (Button) findViewById(R.id.addcard);
        cardname = (TextInputLayout) findViewById(R.id.nameoncard);
        cardnumber = (TextInputLayout) findViewById(R.id.cardnumber);
        expirydate = (TextInputLayout) findViewById(R.id.expirydate);
        cvv = (TextInputLayout) findViewById(R.id.CVV);
        RandomUID = getIntent().getStringExtra("randomUID");

        Addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = cardname.getEditText().getText().toString().trim();
                number = cardnumber.getEditText().getText().toString().trim();
                date = expirydate.getEditText().getText().toString().trim();
                CVV = cvv.getEditText().getText().toString().trim();
                if (valid()) {
                    Intent intent = new Intent(CustomerOnlinePayment.this, CustomerPaymentOTP.class);
                    intent.putExtra("RandomUID",RandomUID);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean valid() {


        cardname.setErrorEnabled(false);
        cardname.setError("");
        cardnumber.setErrorEnabled(false);
        cardnumber.setError("");
        expirydate.setErrorEnabled(false);
        expirydate.setError("");
        cvv.setErrorEnabled(false);
        cvv.setError("");


        boolean isValidname = false, isValidlnumber = false, isValidexpiry = false, isValidcvv = false, isvalid = false;
        if (TextUtils.isEmpty(name)) {
            cardname.setErrorEnabled(true);
            cardname.setError("Cardname is required");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(number)) {
            cardnumber.setErrorEnabled(true);
            cardnumber.setError("Cardnumber is required");
        } else {
            if (number.length() < 16) {
                cardnumber.setErrorEnabled(true);
                cardnumber.setError("Invalid number");
            } else {
                isValidlnumber = true;
            }
        }
        if (TextUtils.isEmpty(date)) {
            expirydate.setErrorEnabled(true);
            expirydate.setError("Expiry date is required");
        } else {
            isValidexpiry = true;

        }
        if (TextUtils.isEmpty(CVV)) {
            cvv.setErrorEnabled(true);
            cvv.setError("CVV is required");
        } else {
            if (CVV.length() < 3) {
                cvv.setErrorEnabled(true);
                cvv.setError("Invalid CVV number");
            } else {
                isValidcvv = true;
            }
        }
        isvalid = (isValidname && isValidlnumber && isValidexpiry && isValidcvv) ? true : false;
        return isvalid;

    }
}
