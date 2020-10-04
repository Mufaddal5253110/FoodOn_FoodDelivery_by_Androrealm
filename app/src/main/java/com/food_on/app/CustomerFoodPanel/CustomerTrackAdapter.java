package com.food_on.app.CustomerFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food_on.app.R;
import java.util.List;

public class CustomerTrackAdapter extends RecyclerView.Adapter<CustomerTrackAdapter.ViewHolder> {

    private Context context;
    private List<CustomerFinalOrders> customerFinalOrderslist;

    public CustomerTrackAdapter(Context context, List<CustomerFinalOrders> customerFinalOrderslist) {
        this.customerFinalOrderslist = customerFinalOrderslist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_trackorder, parent, false);
        return new CustomerTrackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CustomerFinalOrders customerFinalOrders = customerFinalOrderslist.get(position);
        holder.Dishname.setText(customerFinalOrders.getDishName());
        holder.Quantity.setText(customerFinalOrders.getDishQuantity() + "× ");
        holder.Totalprice.setText("₹ " + customerFinalOrders.getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return customerFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Dishname, Quantity, Totalprice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dishnm);
            Quantity = itemView.findViewById(R.id.dishqty);
            Totalprice = itemView.findViewById(R.id.totRS);
        }
    }
}
