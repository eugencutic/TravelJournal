package com.cutic.eugen.traveljournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DestinationsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewDestinations;
    private List<DestinationItem> mDestinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);

        mDestinations = getDestinationsList();

        mRecyclerViewDestinations = findViewById(R.id.recycler_view_destinations);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewDestinations.setLayoutManager(layoutManager);

        DestinationsAdapter destinationsAdapter = new DestinationsAdapter(mDestinations);
        mRecyclerViewDestinations.setAdapter(destinationsAdapter);


    }

    private List<DestinationItem> getDestinationsList() {
        List<DestinationItem> destinations = new ArrayList<>();

        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));
        destinations.add(new DestinationItem("Holiday 2017", "Islands"));

        return destinations;
    }

    public void btnAddTripOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
