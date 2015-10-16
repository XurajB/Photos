package com.iandrobot.example.photos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.iandrobot.example.photos.R;
import com.iandrobot.example.photos.data.Photo;
import com.iandrobot.example.photos.data.PhotoAdapter;
import com.iandrobot.example.photos.service.NetworkTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by surajbhattarai on 10/15/15.
 */
public class ResultFragment extends Fragment {

    @Bind(R.id.fragment_results_search_text)
    EditText searchTerm;

    @Bind(R.id.fragment_results_search_list)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle !=null) {
            String searchText = bundle.getString("search_term");
            searchTerm.setText(searchText);
            searchPhotos(searchText);
        }

        return view;
    }

    @OnClick(R.id.fragment_results_search_button)
    protected void searchPhotos() {
        String term = searchTerm.getText().toString();
        searchPhotos(term);
    }

    protected void searchPhotos(String term) {
        Log.v("PHOTOS", "Search term: " + term);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new PhotoAdapter(getPhotosFromService(term));
        recyclerView.setAdapter(mAdapter);
    }

    protected List<Photo> getPhotosFromService(String term) {
        List<Photo> photos = new ArrayList<>();

        NetworkTask networkTask = new NetworkTask();
        networkTask.downloadPhotoList(term);


        Photo p1 = new Photo();
        p1.setTitle("This is title bla bla bla");

        photos.add(p1);

        return photos;
    }
}
