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

public class ChefOrdertobePrepareViewAdapter extends RecyclerView.Adapter<ChefOrdertobePrepareViewAdapter.ViewHolder> {

    private Context mcontext;
    private List<ChefWaitingOrders> chefWaitingOrderslist;

    public ChefOrdertobePrepareViewAdapter(Context context, List<ChefWaitingOrders> chefWaitingOrderslist) {
        this.chefWaitingOrderslist = chefWaitingOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.chef_ordertobeprepared_view, parent, false);
        return new ChefOrdertobePrepareViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ChefWaitingOrders chefWaitingOrders = chefWaitingOrderslist.get(position);
        holder.dishname.setText(chefWaitingOrders.getDishName());
        holder.price.setText("Price: ₹ " + chefWaitingOrders.getDishPrice());
        holder.quantity.setText("× " + chefWaitingOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + chefWaitingOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return chefWaitingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Dname);
            price = itemView.findViewById(R.id.Dprice);
            totalprice = itemView.findViewById(R.id.Tprice);
            quantity = itemView.findViewById(R.id.Dqty);
        }
    }
}
