package com.school.edsense_lite.messages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MessageDetailsFragment extends BaseFragment {
    @Inject
    MessagesApi messagesApi;

    public static MessageDetailsFragment newInstance(){
        MessageDetailsFragment messageDetailsFragment = new MessageDetailsFragment();
        return messageDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_messages_item, container, false);

        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);

        return view;

    }
}
