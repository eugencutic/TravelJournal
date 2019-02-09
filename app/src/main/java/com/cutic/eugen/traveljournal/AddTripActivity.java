package com.cutic.eugen.traveljournal;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AddTripActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_DESTINATION = 5;
    private static final int PICK_END_DATE = 0;
    private static final int PICK_START_DATE = 1;
    private ImageView mImageViewDestinationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        //setSupportActionBar((Toolbar) findViewById(R.id.manage_trip_bar));
        mImageViewDestinationImage = (ImageView)findViewById(R.id.img_destination);
    }

    public void setStartDate(Integer day, Integer month, Integer year) {
        String date = day.toString() + "/" + month.toString() + "/" + year.toString();
        Button buttonStartDate = (Button)findViewById(R.id.button_start_date);
        buttonStartDate.setText(date);
    }

    public void setEndDate(Integer day, Integer month, Integer year) {
        String date = day.toString() + "/" + month.toString() + "/" + year.toString();
        Button buttonStartDate = (Button)findViewById(R.id.button_end_date);
        buttonStartDate.setText(date);
    }

    public void btnAddPicOnClick(View view) {
        pickImage();
    }

    public void btnSaveOnClick(View view) {
        finish();
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
