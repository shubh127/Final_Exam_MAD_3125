package com.example.finalexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final List<Place> places;
    private final OnPlaceSelected listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvCost;
        private final ImageView ivPlaceImg;
        private final CardView cvParent;
        private final RadioButton rbCheck;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvCost = view.findViewById(R.id.tv_cost);
            ivPlaceImg = view.findViewById(R.id.iv_place_img);
            cvParent = view.findViewById(R.id.cv_parent);
            rbCheck = view.findViewById(R.id.rb_check);
        }
    }

    public PlaceAdapter(List<Place> places, OnPlaceSelected listener) {
        this.places = places;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_place_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Place place = places.get(position);
        viewHolder.rbCheck.setClickable(false);
        viewHolder.rbCheck.setChecked(place.isSelected());
        viewHolder.tvName.setText(place.getName());
        viewHolder.tvCost.setText(String.format(Locale.getDefault(),
                "Visit Fee: $%d", place.getPriceOfVisit()));
        viewHolder.ivPlaceImg.setImageDrawable(ContextCompat.
                getDrawable(viewHolder.ivPlaceImg.getContext(), place.getImageId()));
        viewHolder.cvParent.setOnClickListener(view -> {
            listener.onPlaceSelected(place);
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    interface OnPlaceSelected {
        void onPlaceSelected(Place place);
    }
}