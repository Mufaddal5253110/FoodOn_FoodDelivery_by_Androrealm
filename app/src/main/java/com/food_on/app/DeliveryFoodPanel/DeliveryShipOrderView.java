package com.food_on.app.DeliveryFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.food_on.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryShipOrderView extends AppCompatActivity {

    RecyclerView recyclerViewdish;
    private List<DeliveryShipFinalOrders> deliveryShipFinalOrdersList;
    private DeliveryShipOrderViewAdapter adapter;
    DatabaseReference reference;
    String RandomUID;
    TextView grandtotal, address, name, number, ChefName;
    LinearLayout l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_ship_order_view);
        recyclerViewdish = findViewById(R.id.delishipvieworder);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(DeliveryShipOrderView.this));
        l1 = (LinearLayout) findViewById(R.id.linear2);
        grandtotal = (TextView) findViewById(R.id.Shiptotal);
        ChefName = (TextView) findViewById(R.id.chefname1);
        address = (TextView) findViewById(R.id.ShipAddress);
        name = (TextView) findViewById(R.id.ShipName);
        number = (TextView) findViewById(R.id.ShipNumber);
        deliveryShipFinalOrdersList = new ArrayList<>();
        deliveryfinalorders();
    }

    private void deliveryfinalorders() {

        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                deliveryShipFinalOrdersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DeliveryShipFinalOrders deliveryShipFinalOrders = snapshot.getValue(DeliveryShipFinalOrders.class);
                    deliveryShipFinalOrdersList.add(deliveryShipFinalOrders);
                }
                if (deliveryShipFinalOrdersList.size() == 0) {
                    l1.setVisibility(View.INVISIBLE);

                } else {
                    l1.setVisibility(View.VISIBLE);
                }
                adapter = new DeliveryShipOrderViewAdapter(DeliveryShipOrderView.this, deliveryShipFinalOrdersList);
                recyclerViewdish.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DeliveryShipFinalOrders1 deliveryShipFinalOrders1 = dataSnapshot.getValue(DeliveryShipFinalOrders1.class);
                grandtotal.setText("₹ " + deliveryShipFinalOrders1.getGrandTotalPrice());
                address.setText(deliveryShipFinalOrders1.getAddress());
                name.setText(deliveryShipFinalOrders1.getName());
                number.setText("+91" + deliveryShipFinalOrders1.getMobileNumber());
                ChefName.setText("Chef " + deliveryShipFinalOrders1.getChefName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
