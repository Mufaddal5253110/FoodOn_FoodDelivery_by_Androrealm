package com.food_on.app.ChefFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.food_on.app.R;
import com.food_on.app.SendNotification.APIService;
import com.food_on.app.SendNotification.Client;
import com.food_on.app.SendNotification.Data;
import com.food_on.app.SendNotification.MyResponse;
import com.food_on.app.SendNotification.NotificationSender;

import com.food_on.app.SendNotification.Client;
import com.food_on.app.SendNotification.Data;
import com.food_on.app.SendNotification.MyResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChefOrdertobePrepareView extends AppCompatActivity {

    RecyclerView recyclerViewdish;
    private List<ChefWaitingOrders> chefWaitingOrdersList;
    private ChefOrdertobePrepareViewAdapter adapter;
    DatabaseReference reference;
    String RandomUID, userid;
    TextView grandtotal, note, address, name, number;
    LinearLayout l1;
    Button Preparing;
    private ProgressDialog progressDialog;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_ordertobe_prepare_view);
        recyclerViewdish = findViewById(R.id.Recycle_viewOrder);
        grandtotal = findViewById(R.id.rupees);
        note = findViewById(R.id.NOTE);
        address = findViewById(R.id.ad);
        name = findViewById(R.id.nm);
        number = findViewById(R.id.num);
        l1 = findViewById(R.id.button1);
        Preparing = findViewById(R.id.preparing);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        progressDialog = new ProgressDialog(ChefOrdertobePrepareView.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(ChefOrdertobePrepareView.this));
        chefWaitingOrdersList = new ArrayList<>();
        CheforderdishesView();
    }

    private void CheforderdishesView() {
        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chefWaitingOrdersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChefWaitingOrders chefWaitingOrders = snapshot.getValue(ChefWaitingOrders.class);
                    chefWaitingOrdersList.add(chefWaitingOrders);
                }
                if (chefWaitingOrdersList.size() == 0) {
                    l1.setVisibility(View.INVISIBLE);

                } else {
                    l1.setVisibility(View.VISIBLE);
                    Preparing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            progressDialog.setMessage("Please wait...");
                            progressDialog.show();

                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        final ChefWaitingOrders chefWaitingOrders = dataSnapshot1.getValue(ChefWaitingOrders.class);
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        String dishid = chefWaitingOrders.getDishId();
                                        userid = chefWaitingOrders.getUserId();
                                        hashMap.put("ChefId", chefWaitingOrders.getChefId());
                                        hashMap.put("DishId", chefWaitingOrders.getDishId());
                                        hashMap.put("DishName", chefWaitingOrders.getDishName());
                                        hashMap.put("DishPrice", chefWaitingOrders.getDishPrice());
                                        hashMap.put("DishQuantity", chefWaitingOrders.getDishQuantity());
                                        hashMap.put("RandomUID", RandomUID);
                                        hashMap.put("TotalPrice", chefWaitingOrders.getTotalPrice());
                                        hashMap.put("UserId", chefWaitingOrders.getUserId());
                                        FirebaseDatabase.getInstance().getReference("ChefFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap);
                                    }
                                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                                    data.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final ChefWaitingOrders1 chefWaitingOrders1 = dataSnapshot.getValue(ChefWaitingOrders1.class);
                                            HashMap<String, String> hashMap1 = new HashMap<>();
                                            hashMap1.put("Address", chefWaitingOrders1.getAddress());
                                            hashMap1.put("GrandTotalPrice", chefWaitingOrders1.getGrandTotalPrice());
                                            hashMap1.put("MobileNumber", chefWaitingOrders1.getMobileNumber());
                                            hashMap1.put("Name", chefWaitingOrders1.getName());
                                            hashMap1.put("RandomUID", RandomUID);
                                            hashMap1.put("Status", "Chef is preparing your Order...");
                                            FirebaseDatabase.getInstance().getReference("ChefFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(RandomUID).child("OtherInformation").child("Status").setValue("Chef is preparing your order...").addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {

                                                                            FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                @Override
                                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                    String usertoken = dataSnapshot.getValue(String.class);
                                                                                    sendNotifications(usertoken, "Estimated Time", "Chef is Preparing your Order", "Preparing");
                                                                                    progressDialog.dismiss();
                                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChefOrdertobePrepareView.this);
                                                                                    builder.setMessage("See Orders which are Prepared");
                                                                                    builder.setCancelable(false);
                                                                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                                            dialog.dismiss();
                                                                                            Intent b = new Intent(ChefOrdertobePrepareView.this, ChefOrderTobePrepared.class);
                                                                                            startActivity(b);
                                                                                            finish();


                                                                                        }
                                                                                    });
                                                                                    AlertDialog alert = builder.create();
                                                                                    alert.show();
                                                                                }

                                                                                @Override
                                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                }
                                                                            });

                                                                        }
                                                                    });
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    });

                }
                adapter = new ChefOrdertobePrepareViewAdapter(ChefOrdertobePrepareView.this, chefWaitingOrdersList);
                recyclerViewdish.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ChefWaitingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChefWaitingOrders1 chefWaitingOrders1 = dataSnapshot.getValue(ChefWaitingOrders1.class);
                grandtotal.setText("₹ " + chefWaitingOrders1.getGrandTotalPrice());
                note.setText(chefWaitingOrders1.getNote());
                address.setText(chefWaitingOrders1.getAddress());
                name.setText(chefWaitingOrders1.getName());
                number.setText("+91" + chefWaitingOrders1.getMobileNumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendNotifications(String usertoken, String title, String message, String preparing) {
        Data data = new Data(title, message, preparing);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(ChefOrdertobePrepareView.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }
}
