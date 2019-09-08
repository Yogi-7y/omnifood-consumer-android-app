package com.omnifood.consumer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omnifood.consumer.Activities.MealDetailActivity;
import com.omnifood.consumer.Activities.RestaurantMealActivity;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealListViewHolder> {
    private static final String TAG = "MealListAdapter";

    private ArrayList<Meal> meals;
    private Context context;

    public MealListAdapter(Context context, ArrayList<Meal> meals ) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public MealListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reataurant_meal_list_item, parent, false);
        return new MealListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MealListViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(context)
                .asBitmap()
                .load(meals.get(position).getMealThumbnail())
                .into(holder.mealListImage);
        holder.mealListDescription.setText(meals.get(position).getMealDescription());
        holder.mealListPrice.setText(meals.get(position).getMealPrice());
        holder.mealListName.setText(meals.get(position).getMealName());
        
        holder.mealListParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Name: " + holder.mealListName.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                Intent mealDetailIntent = new Intent(context, MealDetailActivity.class);
                mealDetailIntent.putExtra("mealDetail", meals.get(position));
                mealDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mealDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class MealListViewHolder extends RecyclerView.ViewHolder {


        ImageView mealListImage;
        TextView mealListName, mealListPrice, mealListDescription;
        RelativeLayout mealListParent;
        public MealListViewHolder(@NonNull View itemView) {
            super(itemView);

            mealListImage = itemView.findViewById(R.id.meal_image);
            mealListName = itemView.findViewById(R.id.meal_name);
            mealListPrice = itemView.findViewById(R.id.meal_price);
            mealListDescription = itemView.findViewById(R.id.meal_description);
            mealListParent = itemView.findViewById(R.id.meal_list_parent);
        }
    }

}
