package com.example.map_e.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.map_e.AddReviewActivity;
import com.example.map_e.DetailsActivity;
import com.example.map_e.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
                index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = null;
        switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                //do something
                root = inflater.inflate(R.layout.fragment_overview, container, false);
                break;
            case 2:
                //do something
                root = inflater.inflate(R.layout.fragment_contact, container, false);
                break;
            case 3:
                //do something
                root = inflater.inflate(R.layout.fragment_amenities, container, false);
                break;
            case 4:
                //do something
                root = inflater.inflate(R.layout.reviews_please, container, false);
                break;
            case 5:
                break;
        }
        return root;
    }
}