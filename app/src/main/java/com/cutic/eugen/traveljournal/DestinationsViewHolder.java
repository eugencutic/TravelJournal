package com.cutic.eugen.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DestinationsViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewTitle;
    public TextView mTextViewLocation;
    public ImageView mImageViewPhoto;

    public DestinationsViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewTitle = itemView.findViewById(R.id.text_view_destination_title);
        mTextViewLocation = itemView.findViewById(R.id.text_view_destination_location);
        mImageViewPhoto = itemView.findViewById(R.id.image_view_destination_photo);
    }
}
