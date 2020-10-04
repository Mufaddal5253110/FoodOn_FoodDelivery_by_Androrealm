package com.food_on.app.CustomerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.food_on.app.Customer;
import com.food_on.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerPassword extends AppCompatActivity {


    TextInputLayout current, neww, confirm;
    Button change_pwd;
    TextView forgot;
    String cur, ne, conf, email, password;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_password);

        current = (TextInputLayout) findViewById(R.id.current_pwd);
        neww = (TextInputLayout) findViewById(R.id.new_pwd);
        confirm = (TextInputLayout) findViewById(R.id.confirm_pwd);
        change_pwd = (Button) findViewById(R.id.change);
        forgot = (TextView) findViewById(R.id.forgot_pwd);


        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Customer customer = dataSnapshot.getValue(Customer.class);
                email = customer.getEmailID();


                change_pwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        cur = current.getEditText().getText().toString().trim();
                        ne = neww.getEditText().getText().toString().trim();
                        conf = confirm.getEditText().getText().toString().trim();


                        if (isvalid()) {


                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            AuthCredential credential = EmailAuthProvider.getCredential(email, cur);

                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(ne).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                    FirebaseDatabase.getInstance().getReference("Customer").child(userid).child("Password").setValue(ne);
                                                    FirebaseDatabase.getInstance().getReference("Customer").child(userid).child("ConfirmPassword").setValue(conf);

                                                    Toast.makeText(CustomerPassword.this, "password updated", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(CustomerPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(CustomerPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }
                });

                forgot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent aa=new Intent(CustomerPassword.this,CustomerForgotpassword.class);
                        startActivity(aa);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean isvalid() {
        neww.setErrorEnabled(false);
        neww.setError("");
        confirm.setErrorEnabled(false);
        confirm.setError("");

        boolean isValidnewpassword = false, isValidconfirmpasswoord = false, isvalid = false;
        if (TextUtils.isEmpty(ne)) {
            neww.setErrorEnabled(true);
            neww.setError("New Password is required");

        } else {
            if (ne.length() < 6) {
                neww.setErrorEnabled(true);
                neww.setError("Password too weak");
                confirm.setError("password too weak");
            } else {
                isValidnewpassword = true;
            }
        }

        if (TextUtils.isEmpty(conf)) {
            confirm.setErrorEnabled(true);
            confirm.setError("Confirm Password is required");


        } else {
            if (!ne.equals(conf)) {
                neww.setErrorEnabled(true);
                neww.setError("Password doesn't match");
                confirm.setError("Password doesn't match");
            } else {
                isValidconfirmpasswoord = true;
            }
        }
        isvalid = (isValidnewpassword && isValidconfirmpasswoord) ? true : false;
        return isvalid;

    }

}
