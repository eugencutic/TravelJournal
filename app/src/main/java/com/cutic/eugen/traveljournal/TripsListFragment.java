package com.cutic.eugen.traveljournal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    private List<DestinationItem> mDestinations;

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
        mDestinations = getDestinationsList();

        mRecyclerViewDestinations = view.findViewById(R.id.recycler_view_destinations);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getBaseContext());
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

}
