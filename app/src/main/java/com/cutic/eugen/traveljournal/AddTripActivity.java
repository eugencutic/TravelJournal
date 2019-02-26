package com.cutic.eugen.traveljournal;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.media.Rating;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cutic.eugen.traveljournal.TripType;

public class AddTripActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_DESTINATION = 5;
    private static final int PICK_END_DATE = 0;
    private static final int PICK_START_DATE = 1;
    private ImageView mImageViewDestinationImage;
    private EditText mEditTextTripName;
    private EditText mEditTextDestination;
    private RadioGroup mRadioGroupTripType;
    private TextView mTextViewPrice;
    private SeekBar mSeekBarPrice;
    private RatingBar mRatingBar;
    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private Trip mTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        //setSupportActionBar((Toolbar) findViewById(R.id.manage_trip_bar));
        mImageViewDestinationImage = (ImageView)findViewById(R.id.img_destination);

        mEditTextTripName = findViewById(R.id.edit_text_trip_name);
        mEditTextDestination = findViewById(R.id.edit_text_destination);
        mRadioGroupTripType = findViewById(R.id.trip_type_radio);
        mTextViewPrice = findViewById(R.id.text_view_price);
        mSeekBarPrice = findViewById(R.id.seek_bar_price);
        mRatingBar = findViewById(R.id.rating_bar_rate_trip);
        mButtonStartDate = findViewById(R.id.button_start_date);
        mButtonEndDate = findViewById(R.id.button_end_date);

        mSeekBarPrice.setMax(4000);

        mSeekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String priceText = "Price ";
                priceText += progress;
                priceText += " (EUR)";
                mTextViewPrice.setText(priceText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mTrip = new Trip();
    }

    public void setStartDate(Integer day, Integer month, Integer year) {
        String date = day.toString() + "/" + month.toString() + "/" + year.toString();
        mTrip.setStartDate(new Date(year, month, day));
        Button buttonStartDate = (Button)findViewById(R.id.button_start_date);
        buttonStartDate.setText(date);
    }

    public void setEndDate(Integer day, Integer month, Integer year) {
        String date = day.toString() + "/" + month.toString() + "/" + year.toString();
        mTrip.setEndDate(new Date(year, month, day));
        Button buttonStartDate = (Button)findViewById(R.id.button_end_date);
        buttonStartDate.setText(date);
    }

    public void btnAddPicOnClick(View view) {
        pickImage();
    }

    public void btnSaveOnClick(View view) {

        mTrip.setTitle(mEditTextTripName.getText().toString());
        mTrip.setDestination(mEditTextDestination.getText().toString());
        mTrip.setPrice(mSeekBarPrice.getProgress());
        mTrip.setRating(mRatingBar.getRating());

        switch(mRadioGroupTripType.getCheckedRadioButtonId()) {
            case R.id.radio_button_city:
                mTrip.setTripType(TripType.CITY_BREAK);
                break;
            case R.id.radio_button_seaside:
                mTrip.setTripType(TripType.SEASIDE);
                break;
            case R.id.radio_button_mountains:
                mTrip.setTripType(TripType.MOUNTAINS);
                break;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> trip = new HashMap<>();
        trip.put("title", mTrip.getTitle());
        trip.put("destination", mTrip.getDestination());
        switch(mTrip.getTripType())
        {
            case CITY_BREAK:
                trip.put("type", 0);
                break;
            case SEASIDE:
                trip.put("type", 1);
                break;
            case MOUNTAINS:
                trip.put("type", 2);
                break;
        }
        trip.put("price", mTrip.getPrice());
        trip.put("rating", mTrip.getRating());
        trip.put("start_date", mTrip.getStartDate());
        trip.put("end_date", mTrip.getEndDate());
        trip.put("is_favourite", mTrip.getIsFavourite());

        String tripId = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .collection("trips").document().getId();

        mTrip.setID(tripId);
        trip.put("id", tripId);

        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("trips").document(tripId).set(mTrip)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Logging.show("trip", "added");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logging.show("trip", "failed");
                        finish();
                    }});
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_DESTINATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PHOTO_FOR_DESTINATION) {
            if(resultCode == Activity.RESULT_OK)
            {
                if(data == null) {
                    Toast.makeText(this, "Image Pick Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri imgUri = data.getData();
                Picasso.get().load(imgUri).into(mImageViewDestinationImage);
            }
        }
    }

    public void btnStartDateOnClick(View view) {
        DialogFragment dateFragment = new CustomDatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("pick_date", PICK_START_DATE);
        dateFragment.setArguments(args);
        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void btnEndDateOnClick(View view) {
        DialogFragment dateFragment = new CustomDatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("pick_date", PICK_END_DATE);
        dateFragment.setArguments(args);
        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }
}
