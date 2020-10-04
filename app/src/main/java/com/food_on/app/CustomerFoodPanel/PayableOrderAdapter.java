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

public class PayableOrderAdapter extends RecyclerView.Adapter<PayableOrderAdapter.ViewHolder> {

    private Context context;
    private List<CustomerPaymentOrders> customerPaymentOrderslist;

    public PayableOrderAdapter(Context context, List<CustomerPaymentOrders> customerPendingOrderslist) {
        this.customerPaymentOrderslist = customerPendingOrderslist;
        this.context = context;
    }

    @NonNull
    @Override
    public PayableOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_payableorder, parent, false);
        return new PayableOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayableOrderAdapter.ViewHolder holder, int position) {

        final CustomerPaymentOrders customerPaymentOrders = customerPaymentOrderslist.get(position);
        holder.Dishname.setText(customerPaymentOrders.getDishName());
        holder.Price.setText("Price: ₹ " + customerPaymentOrders.getDishPrice());
        holder.Quantity.setText("× " + customerPaymentOrders.getDishQuantity());
        holder.Totalprice.setText("Total: ₹ " + customerPaymentOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return customerPaymentOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, Price, Quantity, Totalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dish);
            Price = itemView.findViewById(R.id.pri);
            Quantity = itemView.findViewById(R.id.qt);
            Totalprice = itemView.findViewById(R.id.Tot);
        }
    }
}
