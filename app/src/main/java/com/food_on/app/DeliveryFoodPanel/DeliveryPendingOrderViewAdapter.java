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

public class DeliveryPendingOrderViewAdapter extends RecyclerView.Adapter<DeliveryPendingOrderViewAdapter.ViewHolder> {


    private Context mcontext;
    private List<DeliveryShipOrders> deliveryShipOrderslist;

    public DeliveryPendingOrderViewAdapter(Context context, List<DeliveryShipOrders> deliveryShipOrderslist) {
        this.deliveryShipOrderslist = deliveryShipOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.delivery_pendingorder, parent, false);
        return new DeliveryPendingOrderViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DeliveryShipOrders deliveryShipOrders = deliveryShipOrderslist.get(position);
        holder.dishname.setText(deliveryShipOrders.getDishName());
        holder.price.setText("Price: ₹ " + deliveryShipOrders.getDishPrice());
        holder.quantity.setText("× " + deliveryShipOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + deliveryShipOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return deliveryShipOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname = itemView.findViewById(R.id.Dish1);
            price = itemView.findViewById(R.id.Price1);
            totalprice = itemView.findViewById(R.id.Total1);
            quantity = itemView.findViewById(R.id.Quantity1);
        }
    }
}
