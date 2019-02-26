package com.cutic.eugen.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsViewHolder> {

    private List<Trip> mTrips;

    public DestinationsAdapter(List<Trip> mTrips) {
        this.mTrips = mTrips;
    }

    @NonNull
    @Override
    public DestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_destination_item, viewGroup, false);

        return new DestinationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationsViewHolder destinationsViewHolder, int i) {
        Trip trip = mTrips.get(i);

        destinationsViewHolder.mTextViewLocation.setText(trip.getDestination());
        destinationsViewHolder.mTextViewTitle.setText(trip.getTitle());

    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }
}
