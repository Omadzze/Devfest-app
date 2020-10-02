package com.example.devfest.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devfest.Adapter.AgendaAdapter;
import com.example.devfest.Model.AgendaItem;
import com.example.devfest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CloudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloudFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<AgendaItem> list;
    private AgendaAdapter agendaAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CloudFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CloudFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CloudFragment newInstance(String param1, String param2) {
        CloudFragment fragment = new CloudFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);

        recyclerView = view.findViewById(R.id.cloudRecycler);
        final ImageView trex = view.findViewById(R.id.trexImageCloud);
        TextView noInternet = view.findViewById(R.id.no_internet_text_Cloud);
        final ProgressBar progressBar = view.findViewById(R.id.progressBarCloud);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        list = new ArrayList<AgendaItem>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("agenda").child("2020-10-18").child("timeslots");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot valueRes : snapshot.getChildren()) {
                        AgendaItem agendaItem = valueRes.getValue(AgendaItem.class);
                        if (agendaItem.isCloud()) {
                            list.add(agendaItem);
                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    agendaAdapter = new AgendaAdapter(getContext(), list);
                    recyclerView.setAdapter(agendaAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            trex.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}