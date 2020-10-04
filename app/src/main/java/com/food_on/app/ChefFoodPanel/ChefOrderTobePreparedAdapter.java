package com.food_on.app.ChefFoodPanel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food_on.app.R;
import java.util.List;

public class ChefOrderTobePreparedAdapter extends RecyclerView.Adapter<ChefOrderTobePreparedAdapter.ViewHolder> {

    private Context context;
    private List<ChefWaitingOrders1> chefWaitingOrders1list;

    public ChefOrderTobePreparedAdapter(Context context, List<ChefWaitingOrders1> chefWaitingOrders1list) {
        this.chefWaitingOrders1list = chefWaitingOrders1list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chef_ordertobeprepared, parent, false);
        return new ChefOrderTobePreparedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChefWaitingOrders1 chefWaitingOrders1 = chefWaitingOrders1list.get(position);
        holder.Address.setText(chefWaitingOrders1.getAddress());
        holder.grandtotalprice.setText("Grand Total: ₹ " + chefWaitingOrders1.getGrandTotalPrice());
        final String random = chefWaitingOrders1.getRandomUID();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChefOrdertobePrepareView.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
                ((ChefOrderTobePrepared) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chefWaitingOrders1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Address, grandtotalprice;
        Button Vieworder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Address = itemView.findViewById(R.id.cust_address);
            grandtotalprice = itemView.findViewById(R.id.Grandtotalprice);
            Vieworder = itemView.findViewById(R.id.View_order);
        }
    }
}
