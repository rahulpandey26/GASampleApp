package com.example.rahulkumarpandey.gasampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rahul Kumar Pandey on 18-11-2016.
 */

public class FooterFragment extends Fragment {

    public FooterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_footer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view because fragment can't be tracked automatically like activity by using ga_autoActivityTracking to true in app_tracker.xml. so track fragment mannually by code
        MyApplication.getInstance().trackScreenView("Footer Fragment");
    }
}
