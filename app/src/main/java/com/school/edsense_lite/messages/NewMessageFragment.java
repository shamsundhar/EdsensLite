package com.school.edsense_lite.messages;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMessageFragment extends BaseFragment {


    @BindView(R.id.txtMsg)
    EditText message;
    @BindView(R.id.txtSub)
    EditText subject;
    @BindView(R.id.txtTo)
    EditText to;
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
        applyFonts();


        return view;
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        to.setTypeface(tf);
        subject.setTypeface(tf);
        message.setTypeface(tf);

    }
}
