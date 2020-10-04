package com.food_on.app.ChefFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.food_on.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chef_order_dishes extends AppCompatActivity {

    RecyclerView recyclerViewdish;
    private List<ChefPendingOrders> chefPendingOrdersList;
    private ChefOrderDishesAdapter adapter;
    DatabaseReference reference;
    String RandomUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_order_dishes);
        recyclerViewdish = findViewById(R.id.Recycle_orders_dish);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(this));
        chefPendingOrdersList = new ArrayList<>();
        Cheforderdishes();

    }

    private void Cheforderdishes() {

        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("ChefPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chefPendingOrdersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChefPendingOrders chefPendingOrders = snapshot.getValue(ChefPendingOrders.class);
                    chefPendingOrdersList.add(chefPendingOrders);
                }
                adapter = new ChefOrderDishesAdapter(Chef_order_dishes.this, chefPendingOrdersList);
                recyclerViewdish.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
