package com.school.edsense_lite.messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.NavigationDrawerActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.news.News;
import com.school.edsense_lite.today.TodayFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_COMPOSE_MESSAGE;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_NEWS;

public class MessagesFragment extends BaseFragment {
    @BindView(R.id.messagesRecyclerview)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.new_message_button)
    FloatingActionButton newMessageButton;

    private MessagesRecyclerViewAdapter messagesRecyclerViewAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter();
        messagesRecyclerViewAdapter.setItems(getMessagesList());
        messagesRecyclerView.setAdapter(messagesRecyclerViewAdapter);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        messagesRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }

    @OnClick(R.id.new_message_button)
    public void newMessage(){
        Intent in = new Intent(getActivity(), AWFActivity.class);
        in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_COMPOSE_MESSAGE);
        getActivity().startActivity(in);
    }


    private ArrayList<Object> getMessagesList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new MessagesModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1998"));
        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1994"));
        items.add(new MessagesModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text","12-12-1991"));
        items.add(new MessagesModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text","12-12-1995"));
        items.add(new MessagesModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1997"));
        items.add(new MessagesModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text","12-12-1994"));
        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1999"));
        items.add(new MessagesModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1990"));
        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1993"));
        items.add(new MessagesModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text","12-12-1996"));

        return items;
    }
}
