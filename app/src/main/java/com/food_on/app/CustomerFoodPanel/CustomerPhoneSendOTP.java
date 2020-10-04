package com.food_on.app.CustomerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food_on.app.Customer;
import com.food_on.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class CustomerPhoneSendOTP extends AppCompatActivity {

    String phonenumber,OldNumber;
    EditText entercode;
    String verificationId;
    FirebaseAuth FAuth;
    Button verify, Resend;
    TextView txt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_phone_send_otp);

        phonenumber = getIntent().getStringExtra("phonenumber").trim();
        sendverificationcode(phonenumber);

        entercode = (EditText) findViewById(R.id.phoneno);
        txt = (TextView) findViewById(R.id.text);
        Resend = (Button) findViewById(R.id.Resendotp);
        FAuth = FirebaseAuth.getInstance();
        Resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);
        verify = (Button) findViewById(R.id.Verify);

        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Customer customer = dataSnapshot.getValue(Customer.class);
                OldNumber=customer.getMobileno();
                verify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Resend.setVisibility(View.INVISIBLE);
                        String code = entercode.getText().toString().trim();
                        if (code.isEmpty() && code.length() < 6) {
                            entercode.setError("Enter code");
                            entercode.requestFocus();
                            return;
                        }
                        verifyCode(code);
                    }

                });

                new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code within " + millisUntilFinished / 1000 + " Seconds");
                    }

                    @Override
                    public void onFinish() {
                        Resend.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);

                    }
                }.start();

                Resend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Resend.setVisibility(View.INVISIBLE);
                        Resendotp(phonenumber);

                        new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                txt.setVisibility(View.VISIBLE);
                                txt.setText("Resend Code within " + millisUntilFinished / 1000 + " Seconds");
                            }

                            @Override
                            public void onFinish() {
                                Resend.setVisibility(View.VISIBLE);
                                txt.setVisibility(View.INVISIBLE);

                            }
                        }.start();

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Resendotp(String phonenumber) {

        sendverificationcode(phonenumber);
    }

    private void verifyCode(String code) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OldNumber,code);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePhoneNumber(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CustomerPhoneSendOTP.this, "phone number updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CustomerPhoneSendOTP.this, "This 2: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(CustomerPhoneSendOTP.this, "This 3: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void sendverificationcode(String number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                CustomerPhoneSendOTP.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        Toast.makeText(CustomerPhoneSendOTP.this, "sent", Toast.LENGTH_SHORT).show();
                        verificationId = s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(CustomerPhoneSendOTP.this, "Receive", Toast.LENGTH_SHORT).show();

                        String code = phoneAuthCredential.getSmsCode();
                        if (code != null) {
                            entercode.setText(code);
                            verifyCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        Toast.makeText(CustomerPhoneSendOTP.this, "This 1:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}

