package com.iandrobot.example.photos.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.iandrobot.example.photos.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by surajbhattarai on 10/15/15.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.fragment_main_search_term)
    EditText searchTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.fragment_main_search_button)
    protected void loadResultsFragment() {

        ResultFragment resultFragment = new ResultFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments

        Bundle args = new Bundle();
        args.putString("search_term", searchTerm.getText().toString());

        resultFragment.setArguments(args);

        // Replace the fragment
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack("main_fragment")
                .commit();

    }
}
