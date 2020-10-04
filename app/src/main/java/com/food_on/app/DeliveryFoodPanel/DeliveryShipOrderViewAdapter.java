package com.food_on.app.DeliveryFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food_on.app.R;
import java.util.List;

public class DeliveryShipOrderViewAdapter extends RecyclerView.Adapter<DeliveryShipOrderViewAdapter.ViewHolder> {


    private Context mcontext;
    private List<DeliveryShipFinalOrders> deliveryShipFinalOrderslist;

    public DeliveryShipOrderViewAdapter(Context context, List<DeliveryShipFinalOrders> deliveryShipFinalOrderslist) {
        this.deliveryShipFinalOrderslist = deliveryShipFinalOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.shiporderview, parent, false);
        return new DeliveryShipOrderViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DeliveryShipFinalOrders deliveryShipFinalOrders = deliveryShipFinalOrderslist.get(position);
        holder.dishname.setText(deliveryShipFinalOrders.getDishName());
        holder.price.setText("Price: ₹ " + deliveryShipFinalOrders.getDishPrice());
        holder.quantity.setText("× " + deliveryShipFinalOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + deliveryShipFinalOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return deliveryShipFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname = itemView.findViewById(R.id.dish2);
            price = itemView.findViewById(R.id.Price2);
            totalprice = itemView.findViewById(R.id.Total2);
            quantity = itemView.findViewById(R.id.Qty2);
        }
    }
}
