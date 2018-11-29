package com.school.edsense_lite.messages;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.utils.Common;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_COMPOSE_MESSAGE;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_MESSAGE_DETAILS;
import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;
import static com.school.edsense_lite.utils.Constants.KEY_MESSAGE_ID;
import static com.school.edsense_lite.utils.Constants.KEY_MESSAGE_MAP_ID;

public class MessagesFragment extends BaseFragment {
    @BindView(R.id.messagesRecyclerview)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.new_message_button)
    FloatingActionButton newMessageButton;

    private MessagesRecyclerViewAdapter messagesRecyclerViewAdapter;
    public static EdsenseDatabase mEdsenseDatabase;

    @Inject
    MessagesApi messagesApi;

    private ArrayList<MessagesResponseModel> messagesResponseList;

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
        mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        applyFonts();

        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String primaryUrl = preferenceHelper.getString(getActivity(),Constants.PREF_KEY_SUBSCRIPTION_PRIMARY_URL,"");
        messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(getActivity().getApplicationContext(), primaryUrl);
        messagesRecyclerViewAdapter.setOnItemClickListener(new MessageItemClickListener() {
            @Override
            public void onMessageItemClicked(int position) {
                
                Intent in = new Intent(getActivity(), AWFActivity.class);
                Bundle b = new Bundle();
                if(messagesResponseList != null && messagesResponseList.size()>0){
                    b.putString(KEY_MESSAGE_ID,""+messagesResponseList.get(position).getId());
                    b.putString(KEY_MESSAGE_MAP_ID,""+messagesResponseList.get(position).getMapId());
                }
                in.putExtras(b);
                in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_MESSAGE_DETAILS);
                getActivity().startActivity(in);
            }
        });


        
        //API call
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");

        MessagesRequest request = new MessagesRequest();
        request.setValue(new MessagesRequest.Value());
        request.getValue().setLevelId("2|1");
        request.getValue().setPageStartIndex("1");
        request.getValue().setPageSize("10");
        request.getValue().setFilters(" ");
        JsonObject jsonObject = new JsonObject();

        JsonObject valueObje = new JsonObject();
        valueObje.addProperty("filters","");
        valueObje.addProperty("levelId","2|1");
        valueObje.addProperty("pageSize","10");
        valueObje.addProperty("pageStartIndex","1");

        jsonObject.addProperty("value", String.valueOf(valueObje));
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
        //String jsonString = gson.toJson(request);//"{\"value\":{\"filters\":\" \",\"levelId\":\"2\",\"pageSize\":\"10\",\"pageStartIndex\":\"1\"}}";
        //Log.d("----",""+jsonString);
        if(Common.isNetworkAvailable(getActivity())) {
            messagesApi.fetch(bearerToken, request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MessagesResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            if(e instanceof StreamResetException)
                            {
                                //login again
                                e.printStackTrace();
                                relogin();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MessagesResponse messagesResponse) {
                            if (messagesResponse.isIsSuccess()) {

//                            if(getMessagesList() != null) {
//                                for (MessagesResponseModel message : getMessagesList()) {
                                if (messagesResponse.getResponse() != null) {
                                    for (MessagesResponseModel message : messagesResponse.getResponse()) {
                                        if (message != null) {
                                            mEdsenseDatabase.messagesDao().insert(message);
                                        }
                                    }
                                }
                                displayMessagesFromDB(progressDialog);
                            } else if (messagesResponse.getErrorCode() != 200) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_failed,
                                        messagesResponse.getErrorMessage(),
                                        null);
                            }
                        }
                    });
        }
        else{
            displayMessagesFromDB(progressDialog);
        }


        return view;
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        titleTV.setTypeface(tf);

    }
    @OnClick(R.id.new_message_button)
    public void newMessage(){
        Intent in = new Intent(getActivity(), AWFActivity.class);
        in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_COMPOSE_MESSAGE);
        getActivity().startActivity(in);
    }
    private void displayMessagesFromDB(ProgressDialog progressDialog){
        messagesResponseList =  (ArrayList<MessagesResponseModel>) mEdsenseDatabase.messagesDao().getAllMessages();
        progressDialog.dismiss();
        messagesRecyclerViewAdapter.setItems(messagesResponseList);
        messagesRecyclerView.setAdapter(messagesRecyclerViewAdapter);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messagesRecyclerViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<MessagesResponseModel> getMessagesList() {
        ArrayList<MessagesResponseModel> items = new ArrayList<>();
        items.add(new MessagesResponseModel(1,111,"org1","org","sub","12-12-1998","high",1122,
                "Sample context displayname",12345,4,1,12,"14321423","https://raw.githubusercontent.com/gcacace/android-socialbuttons/master/screenshot.png",
                "Sample message from dev","sample image"));
        //items.add(new MessagesResponseModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1998"));
//        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1994"));
//        items.add(new MessagesModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text","12-12-1991"));
//        items.add(new MessagesModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text","12-12-1995"));
//        items.add(new MessagesModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1997"));
//        items.add(new MessagesModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text","12-12-1994"));
//        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1999"));
//        items.add(new MessagesModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text","12-12-1990"));
//        items.add(new MessagesModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text","12-12-1993"));
//        items.add(new MessagesModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text","12-12-1996"));

        return items;
    }
}
