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

public class DeliveryPendingOrderView extends AppCompatActivity {

    RecyclerView recyclerViewdish;
    private List<DeliveryShipOrders> deliveryShipOrdersList;
    private DeliveryPendingOrderViewAdapter adapter;
    DatabaseReference reference;
    String RandomUID;
    TextView grandtotal, address, name, number,ChefName;
    LinearLayout l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_pending_order_view);
        recyclerViewdish = findViewById(R.id.delivieworder);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(DeliveryPendingOrderView.this));
        l1 = (LinearLayout) findViewById(R.id.linear1);
        grandtotal = (TextView) findViewById(R.id.Dtotal);
        address = (TextView) findViewById(R.id.DAddress);
        ChefName=(TextView)findViewById(R.id.chefname);
        name = (TextView) findViewById(R.id.DName);
        number = (TextView) findViewById(R.id.DNumber);
        deliveryShipOrdersList = new ArrayList<>();
        deliveryorders();
    }

    private void deliveryorders() {
        RandomUID = getIntent().getStringExtra("Random");

        reference = FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                deliveryShipOrdersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DeliveryShipOrders deliveryShipOrders = snapshot.getValue(DeliveryShipOrders.class);
                    deliveryShipOrdersList.add(deliveryShipOrders);
                }
                if (deliveryShipOrdersList.size() == 0) {
                    l1.setVisibility(View.INVISIBLE);

                } else {
                    l1.setVisibility(View.VISIBLE);
                }
                adapter = new DeliveryPendingOrderViewAdapter(DeliveryPendingOrderView.this, deliveryShipOrdersList);
                recyclerViewdish.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DeliveryShipOrders1 deliveryShipOrders1 = dataSnapshot.getValue(DeliveryShipOrders1.class);
                grandtotal.setText("₹ " + deliveryShipOrders1.getGrandTotalPrice());
                address.setText(deliveryShipOrders1.getAddress());
                name.setText(deliveryShipOrders1.getName());
                number.setText("+91" + deliveryShipOrders1.getMobileNumber());
                ChefName.setText("Chef "+ deliveryShipOrders1.getChefName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
