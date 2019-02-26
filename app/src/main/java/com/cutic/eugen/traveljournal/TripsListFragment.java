package com.cutic.eugen.traveljournal;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/*
 * A simple {@link Fragment} subclass.
 * Use the {@link TripsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripsListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerViewDestinations;
    private List<Trip> mTrips;

    private FirestoreRecyclerAdapter<Trip, DestinationsViewHolder> adapter;

    public TripsListFragment() {
        // Required empty public constructor
    }

    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TripsListFragment.
     */
    // TODO: Add boolean parameter to show favorite list or not
    /*public static TripsListFragment newInstance(String param1, String param2) {
        TripsListFragment fragment = new TripsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //mTrips = getTripsList();

        mRecyclerViewDestinations = view.findViewById(R.id.recycler_view_destinations);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getBaseContext());
        mRecyclerViewDestinations.setLayoutManager(layoutManager);

        //DestinationsAdapter destinationsAdapter = new DestinationsAdapter(mTrips);
        //mRecyclerViewDestinations.setAdapter(destinationsAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("users")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("trips");

        FirestoreRecyclerOptions<Trip> options = new FirestoreRecyclerOptions.Builder<Trip>()
                                                    .setQuery(query, Trip.class)
                                                    .build();

        adapter = new FirestoreRecyclerAdapter<Trip, DestinationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DestinationsViewHolder holder, int position, @NonNull Trip model) {
                holder.Trip = model;

                holder.mTextViewLocation.setText(model.getDestination());
                holder.mTextViewTitle.setText(model.getTitle());
                if (model.getImageUri() != null)
                    Picasso.get().load(Uri.parse(model.getImageUri()))
                            .resize(100, 100).centerInside().into(holder.mImageViewPhoto);
            }

            @NonNull
            @Override
            public DestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = (View) LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_destination_item, viewGroup, false);
                return new DestinationsViewHolder(view);
            }
        };

        mRecyclerViewDestinations.setAdapter(adapter);
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null)
            adapter.stopListening();
    }

    private List<Trip> getTripsList() {
        mTrips = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("trips")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Trip trip = new Trip();
                                trip.setID(doc.getId());
                                trip.setTitle(doc.get("title").toString());
                                trip.setDestination(doc.get("destination").toString());
                                //trip.setPrice((int) doc.get("price"));
                                //trip.setRating((double) doc.get("rating"));
                                //trip.setIsFavourite((Boolean) doc.get("is_favourite"));
                                //trip.setTripType((TripType) doc.get("type"));
                                //trip.setStartDate((Date) doc.get("start_date"));
                                //trip.setEndDate((Date) doc.get("end_date"));

                                TripsListFragment.this.mTrips.add(trip);
                            }
                        }
                        else {
                            Logging.show(this, "query failed");
                        }
                    }
                });

        return mTrips;
    }

}
