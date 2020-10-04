package com.food_on.app.ChefFoodPanel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.food_on.app.R;

import java.util.List;

public class ChefhomeAdapter extends RecyclerView.Adapter<ChefhomeAdapter.ViewHolder> {

   private Context mcont;
   private List<UpdateDishModel>updateDishModellist;

   public ChefhomeAdapter(Context context,List<UpdateDishModel>updateDishModellist)
   {
       this.updateDishModellist=updateDishModellist;
       this.mcont=context;
   }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mcont).inflate(R.layout.chef_menu_update_delete,parent,false);
       return new ChefhomeAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final UpdateDishModel updateDishModel=updateDishModellist.get(position);
       holder.dishes.setText(updateDishModel.getDishes());
       updateDishModel.getRandomUID();
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(mcont,Update_Delete_Dish.class);
               intent.putExtra("updatedeletedish",updateDishModel.getRandomUID());
               mcont.startActivity(intent);

           }
       });
    }


    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView dishes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishes=itemView.findViewById(R.id.dish_name);

        }
    }
}
