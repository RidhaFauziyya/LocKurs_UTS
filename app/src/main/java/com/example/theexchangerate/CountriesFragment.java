package com.example.theexchangerate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CountriesFragment extends Fragment {

    public CountriesFragment() {
        // Required empty public constructor
    }
    public static CountriesFragment newInstance(){
        return new CountriesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_countries, container, false);
        final ImageButton indonesia = rootView.findViewById(R.id.indonesian);
        final ImageButton us = rootView.findViewById(R.id.us);
        final ImageButton uk = rootView.findViewById(R.id.uk);
        final ImageButton sokor = rootView.findViewById(R.id.sokor);
        final ImageButton china = rootView.findViewById(R.id.thai);
        final ImageButton jpn = rootView.findViewById(R.id.jpn);

        indonesia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), IndonesiaClass.class);
                startActivity(add_mem);
            }
        });

        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), UsClass.class);
                startActivity(add_mem);
            }
        });

        uk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), UkClass.class);
                startActivity(add_mem);
            }
        });

        sokor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), SokorClass.class);
                startActivity(add_mem);
            }
        });

        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), ThaiClass.class);
                startActivity(add_mem);
            }
        });

        jpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), JpnClass.class);
                startActivity(add_mem);
            }
        });

        return rootView;
    }
}