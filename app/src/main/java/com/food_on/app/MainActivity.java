package com.food_on.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.food_on.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView imageVieww;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageVieww=(ImageView)findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.textView7);
        imageVieww.animate().alpha(0f).setDuration(0);
        textView.animate().alpha(0f).setDuration(0);
        imageVieww.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.animate().alpha(1f).setDuration(800);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Fauth = FirebaseAuth.getInstance();
                if (Fauth.getCurrentUser() != null) {
                    if (Fauth.getCurrentUser().isEmailVerified()) {
                        Fauth = FirebaseAuth.getInstance();
                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid() + "/Role");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String role = dataSnapshot.getValue(String.class);
                                if (role.equals("Customer")) {
                                    Intent n = new Intent(MainActivity.this, CustomerFoodPanel_BottomNavigation.class);
                                    startActivity(n);
                                    finish();
                                }
                                if (role.equals("Chef")) {
                                    Intent a = new Intent(MainActivity.this, ChefFoodPanel_BottomNavigation.class);
                                    startActivity(a);
                                    finish();
                                }
                                if (role.equals("DeliveryPerson")) {
                                    Intent intent = new Intent(MainActivity.this, Delivery_FoodPanelBottomNavigation.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Check whether you have verified your details, Otherwise please verify");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        Fauth.signOut();
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, 3000);
    }
}
