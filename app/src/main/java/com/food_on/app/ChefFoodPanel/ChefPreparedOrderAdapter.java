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

public class ChefPreparedOrderAdapter extends RecyclerView.Adapter<ChefPreparedOrderAdapter.ViewHolder> {

    private Context context;
    private List<ChefFinalOrders1> chefFinalOrders1list;

    public ChefPreparedOrderAdapter(Context context, List<ChefFinalOrders1> chefFinalOrders1list) {
        this.chefFinalOrders1list = chefFinalOrders1list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chef_preparedorder, parent, false);
        return new ChefPreparedOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChefFinalOrders1 chefFinalOrders1 = chefFinalOrders1list.get(position);
        holder.Address.setText(chefFinalOrders1.getAddress());
        holder.grandtotalprice.setText("Grand Total: ₹ " + chefFinalOrders1.getGrandTotalPrice());
        final String random = chefFinalOrders1.getRandomUID();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChefPreparedOrderView.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
                ((ChefPreparedOrder) context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return chefFinalOrders1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Address, grandtotalprice;
        Button Vieworder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Address = itemView.findViewById(R.id.customer_address);
            grandtotalprice = itemView.findViewById(R.id.customer_totalprice);
            Vieworder = itemView.findViewById(R.id.View);
        }
    }
}
