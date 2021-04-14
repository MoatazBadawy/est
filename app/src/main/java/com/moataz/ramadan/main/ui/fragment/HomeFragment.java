package com.moataz.ramadan.main.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moataz.ramadan.R;
import com.moataz.ramadan.main.utils.IOnBackPressed;


public class HomeFragment extends Fragment implements IOnBackPressed {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getFragmentManager().popBackStack();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public boolean onBackPressed() {
        //exit the app when press back
        getActivity().moveTaskToBack(true);
        getActivity().finish();
        return true;
    }
}