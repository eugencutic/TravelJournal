package com.cutic.eugen.traveljournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;

public class TripDetailsActivity extends AppCompatActivity {

    private Trip mTrip;

    private TextView mTextViewTitle;
    private TextView mTextViewDestination;
    private TextView mTextViewPrice;
    private TextView mTextViewStartDate;
    private TextView mTextViewEndDate;
    private TextView mTextViewTripType;
    private RatingBar mRatingBarTripRating;
    private ImageView mImageViewTripPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        Intent intent = getIntent();
        mTrip = (Trip)intent.getSerializableExtra("tripToShow");

        mTextViewTitle = findViewById(R.id.text_view_details_title);
        mTextViewDestination = findViewById(R.id.text_view_details_destiantion);
        mTextViewPrice = findViewById(R.id.text_view_details_price);
        mTextViewTripType = findViewById(R.id.text_view_details_trip_type);
        mTextViewStartDate = findViewById(R.id.text_view_details_start_date);
        mTextViewEndDate = findViewById(R.id.text_view_details_end_date);
        mRatingBarTripRating = findViewById(R.id.rating_bar_details_trip_rating);
        mImageViewTripPic = findViewById(R.id.image_view_details_trip_pic);

        Logging.show(this, "retrieved ui elements");

        showTrip();
    }

    private void showTrip() {
        Logging.show(this, mTrip.getTitle());

        mTextViewTitle.setText(mTrip.getTitle());
        mTextViewDestination.setText(mTrip.getDestination());
        String priceString = mTrip.getPrice() + "";
        mTextViewPrice.setText(priceString);
        mTextViewTripType.setText(mTrip.getTripType().toString());

        Date startDate = mTrip.getStartDate();
        String startDateString = startDate.getDay() + "/" + startDate.getMonth() + "/" + startDate.getYear();
        Date endDate = mTrip.getEndDate();
        String endDateString = endDate.getDay() + "/" + endDate.getMonth() + "/" + endDate.getYear();

        mTextViewStartDate.setText(startDateString);
        mTextViewEndDate.setText(endDateString);

        mRatingBarTripRating.setRating((float)mTrip.getRating());
        //TODO: show pic
    }
}
