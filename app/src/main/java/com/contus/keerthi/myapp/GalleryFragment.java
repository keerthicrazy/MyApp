package com.contus.keerthi.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Custom.CustomNewsAdapter;
import com.contus.keerthi.myapp.POJO.Gallery;
import com.contus.keerthi.myapp.Custom.CustomGalleryAdapter;
import com.contus.keerthi.myapp.Custom.GetImages;
import com.contus.keerthi.myapp.POJO.News;

import java.util.ArrayList;

/**Fragment with recyclerview to show images
 * Created by Keerthivasan on 20/2/17.
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
        getImages = new GetImages(getActivity());
        galleryArrayList = getImages.getImages();
        customGalleryAdapter = new CustomGalleryAdapter(galleryArrayList,getActivity(),adapterInterface);
        recyclerView.setAdapter(customGalleryAdapter);
        return view;
    }


    CustomGalleryAdapter.AdapterInterface adapterInterface = new CustomGalleryAdapter.AdapterInterface() {
        @Override
        public void fetchData(View view,int position) {
            Gallery gallery = galleryArrayList.get(position);
            Intent intent = new Intent(view.getContext(), ImageViewerActivity.class);
            intent.putExtra("imgUrl",gallery.getImageUrl());
            startActivity(intent);
        }
    };

}
