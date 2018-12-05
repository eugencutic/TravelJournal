package com.cutic.eugen.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsViewHolder> {

    private List<DestinationItem> mDestinations;

    public DestinationsAdapter(List<DestinationItem> mDestinations) {
        this.mDestinations = mDestinations;
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
        DestinationItem destinationItem = mDestinations.get(i);

        destinationsViewHolder.mTextViewLocation.setText(destinationItem.getLocation());
        destinationsViewHolder.mTextViewTitle.setText(destinationItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDestinations.size();
    }
}
