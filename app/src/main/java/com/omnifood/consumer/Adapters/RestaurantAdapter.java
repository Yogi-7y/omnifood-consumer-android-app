package com.omnifood.consumer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omnifood.consumer.R;
import com.omnifood.consumer.Activities.RestaurantMealActivity;
import com.omnifood.consumer.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private static final String TAG = "RestaurantAdapter";
    private Context context;
    private ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_name_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called ");
        Glide.with(context)
                .asBitmap()
                .load(restaurants.get(position).getRestaurantThumbnail())
                .into(holder.restaurantThumbnail);
        holder.restaurantName.setText(restaurants.get(position).getRestaurantName());
        holder.restaurantDescription.setText(restaurants.get(position).getRestaurantAddress());

        holder.homeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + restaurants.get(position).getRestaurantName());
                Toast.makeText(context, "" + restaurants.get(position).getRestaurantName(), Toast.LENGTH_SHORT).show();
                Intent getMenuIntent = new Intent(context, RestaurantMealActivity.class);
                getMenuIntent.putExtra("restaurant", restaurants.get(position));
                Log.d(TAG, "onClick: Adapter Id: " + restaurants.get(position).getRestaurantId());
                Log.d(TAG, "onClick: R Name: " + restaurants.get(position).getRestaurantName());
                Log.d(TAG, "onClick: desc: " + restaurants.get(position).getRestaurantAddress());
                Log.d(TAG, "onClick: phone" + restaurants.get(position).getRestaurantPhone());
                getMenuIntent.putExtra("restaurant", restaurants.get(position));
                getMenuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(getMenuIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName;
        TextView restaurantDescription;
        ImageView restaurantThumbnail;
        CardView homeCardView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.card_view_restaurant_title);
            restaurantDescription = itemView.findViewById(R.id.card_view_restaurant_description);
            restaurantThumbnail = itemView.findViewById(R.id.card_view_restaurant_image);
            homeCardView = itemView.findViewById(R.id.home_card_view);
        }
    }

}
