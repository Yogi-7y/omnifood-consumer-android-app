package com.omnifood.consumer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omnifood.consumer.R;
import com.omnifood.consumer.RestaurantMealActivity;
import com.omnifood.consumer.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {


    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
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
        holder.restaurantName.setText(restaurants.get(position).getRestaurantName());
        holder.restaurantThumbnail.setImageResource(restaurants.get(position).getRestaurantThumbnail());
        holder.restaurantDescription.setText(restaurants.get(position).getRestaurantAddress());

        holder.homeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Name: " + restaurants.get(position).getRestaurantName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, RestaurantMealActivity.class);
                context.startActivity(intent);
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
