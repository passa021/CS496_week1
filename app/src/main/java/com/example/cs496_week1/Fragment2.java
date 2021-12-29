package com.example.cs496_week1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_2,container, false);

        int[] imageIDs = new int[]{
                R.drawable.image_01,
                R.drawable.image_02,
                R.drawable.image_03,
                R.drawable.image_04,
                R.drawable.image_05,
                R.drawable.image_06,
                R.drawable.image_07,
                R.drawable.image_08,
                R.drawable.image_09,
                R.drawable.image_10,
                R.drawable.image_11,
                R.drawable.image_12,
                R.drawable.image_13,
                R.drawable.image_14,
                R.drawable.image_15,
                R.drawable.image_16,
                R.drawable.image_17,
                R.drawable.image_18,
                R.drawable.image_19,
                R.drawable.image_20,

        };

        GridView gridViewImages = (GridView) rootView.findViewById(R.id.gridViewImages);
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), imageIDs);
        gridViewImages.setAdapter(imageAdapter);

        return rootView;
    }

}