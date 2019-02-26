package com.cutic.eugen.traveljournal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DestinationsViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewTitle;
    public TextView mTextViewLocation;
    public ImageView mImageViewPhoto;
    public ImageButton mImageButtonDelete;
    public Trip Trip;

    public DestinationsViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewTitle = itemView.findViewById(R.id.text_view_destination_title);
        mTextViewLocation = itemView.findViewById(R.id.text_view_destination_location);
        mImageViewPhoto = itemView.findViewById(R.id.image_view_destination_photo);
        mImageButtonDelete = itemView.findViewById(R.id.button_delete);

        mImageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("trips").document(Trip.getID()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Logging.show(this, "deleted");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logging.show(this, "deleting failed");
                    }
                });
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //TODO: show details (solve null references issues)
                Intent intent = new Intent(v.getContext(), TripDetailsActivity.class);
                intent.putExtra("tripToShow", Trip);
                if (intent.resolveActivity(v.getContext().getPackageManager()) != null )
                    v.getContext().startActivity(intent);

                return false;
            }
        });

    }
}
