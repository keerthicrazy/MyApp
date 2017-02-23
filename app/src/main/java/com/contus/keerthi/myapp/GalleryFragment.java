package com.contus.keerthi.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.POJO.Gallery;
import com.contus.keerthi.myapp.custom.CustomGalleryAdapter;
import com.contus.keerthi.myapp.custom.GetImages;
import com.contus.keerthi.myapp.custom.GetNews;

import java.util.ArrayList;

/**
 * Created by user on 20/2/17.
 */

public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Gallery> galleryArrayList;
    GetImages getImages;
    CustomGalleryAdapter customGalleryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery,container,false);
        galleryArrayList = new ArrayList<Gallery>();
        recyclerView = (RecyclerView)view.findViewById(R.id.gallery_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        getImages = new GetImages();
        galleryArrayList = getImages.downloadImages();
        customGalleryAdapter = new CustomGalleryAdapter(galleryArrayList,getActivity());
        recyclerView.setAdapter(customGalleryAdapter);
        return view;
    }
}
