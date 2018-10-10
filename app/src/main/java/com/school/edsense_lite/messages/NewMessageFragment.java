package com.school.edsense_lite.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;

import butterknife.ButterKnife;

public class NewMessageFragment extends BaseFragment {



    public static NewMessageFragment newInstance() {
        NewMessageFragment fragment = new NewMessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_messages, container, false);

        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        return view;
    }

}
