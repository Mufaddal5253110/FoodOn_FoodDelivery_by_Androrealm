package com.food_on.app.CustomerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.food_on.app.R;
import com.food_on.app.ReusableCode.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerForgotpassword extends AppCompatActivity {


    TextInputLayout emaillid;
    Button Reset;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_forgotpassword);

        emaillid=(TextInputLayout)findViewById(R.id.email);
        Reset=(Button)findViewById(R.id.reset);

        Fauth=FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fauth.sendPasswordResetEmail(emaillid.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CustomerForgotpassword.this);
                            builder.setMessage("Password has been sent to your Email");
                            builder.setCancelable(false);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    Intent bb=new Intent(CustomerForgotpassword.this, CustomerPassword.class);
                                    startActivity(bb);

                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {

                            ReusableCodeForAll.ShowAlert(CustomerForgotpassword.this,"Error",task.getException().getMessage());
                        }
                    }
                });
            }
        });
    }
}
