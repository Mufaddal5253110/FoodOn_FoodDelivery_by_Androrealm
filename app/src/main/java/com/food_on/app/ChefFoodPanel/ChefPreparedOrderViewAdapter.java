package com.food_on.app.ChefFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food_on.app.R;
import java.util.List;

public class ChefPreparedOrderViewAdapter extends RecyclerView.Adapter<ChefPreparedOrderViewAdapter.ViewHolder> {

    private Context mcontext;
    private List<ChefFinalOrders> chefFinalOrderslist;

    public ChefPreparedOrderViewAdapter(Context context, List<ChefFinalOrders> chefFinalOrderslist) {
        this.chefFinalOrderslist = chefFinalOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.chef_preparedorderview, parent, false);
        return new ChefPreparedOrderViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ChefFinalOrders chefFinalOrders=chefFinalOrderslist.get(position);
        holder.dishname.setText(chefFinalOrders.getDishName());
        holder.price.setText("Price: ₹ " + chefFinalOrders.getDishPrice());
        holder.quantity.setText("× " + chefFinalOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + chefFinalOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return chefFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Cdishname);
            price = itemView.findViewById(R.id.Cdishprice);
            totalprice = itemView.findViewById(R.id.Ctotalprice);
            quantity = itemView.findViewById(R.id.Cdishqty);
        }
    }
}
