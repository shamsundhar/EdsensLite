package com.school.edsense_lite.messages;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.today.ScheduleResponse;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_COMPOSE_MESSAGE;
import static com.school.edsense_lite.utils.Constants.RECIPIENT_DELIMETER;

public class NewMessageFragment extends BaseFragment {

    @BindView(R.id.pagetitle)
    TextView pageTitle;
    @BindView(R.id.txtMsg)
    EditText message;
    @BindView(R.id.txtSub)
    EditText subject;
    //    @BindView(R.id.toEditText)
//    DelayAutoCompleteTextView to;
    @BindView(R.id.toEditText)
    AppCompatAutoCompleteTextView to;
    //    @BindView(R.id.ccEditText)
//    DelayAutoCompleteTextView cc;
    private int THRESHOLD = 4;
    @Inject
    MessagesApi messagesApi;
    private AutoSuggestAdapter autoSuggestAdapter;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    List<SearchUserResponse.Response> usersList = new ArrayList<>();
    private Handler handler;
    Integer selectedToId;
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

//        to.setThreshold(THRESHOLD);
//        to.setAdapter(new BookAutoCompleteAdapter(getActivity())); // 'this' is Activity instance
//        to.setLoadingIndicator(
//                (android.widget.ProgressBar) view.findViewById(R.id.pb_loading_indicator));
//        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                SearchUserResponse.Response recipient = (SearchUserResponse.Response) adapterView.getItemAtPosition(position);
////                String existingString = to.getText().toString().trim();
////                if(existingString.length() > 0)
////                {
////                    existingString = existingString + RECIPIENT_DELIMETER + recipient.getTagName();
////                }
////                else {
////                    existingString = recipient.getTagName();
////                }
//                to.setText(recipient.getTagName());
//                selectedToId = recipient.getTagId();
//            }
//        });

//Setting up the adapter for AutoSuggest
        autoSuggestAdapter = new AutoSuggestAdapter(getActivity(),
                android.R.layout.simple_dropdown_item_1line);
        to.setThreshold(2);
        to.setAdapter(autoSuggestAdapter);
        to.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                    //    to.setText(autoSuggestAdapter.getObject(position).getTagName());
                        SearchUserResponse.Response recipient = (SearchUserResponse.Response) autoSuggestAdapter.getObject(position);
//                String existingString = to.getText().toString().trim();
//                if(existingString.length() > 0)
//                {
//                    existingString = existingString + RECIPIENT_DELIMETER + recipient.getTagName();
//                }
//                else {
//                    existingString = recipient.getTagName();
//                }
                to.setText(recipient.getTagName());
                selectedToId = recipient.getTagId();
                    }
                });

        to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(to.getText())) {
                     //   makeApiCall(to.getText().toString());
                        findRecipients(getActivity(), to.getText().toString());
                    }
                }
                return false;
            }
        });

        return view;
    }
    private void makeApiCall(String text) {
//        ApiCall.make(this, text, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //parsing logic, please change it as per your requirement
//                List<String> stringList = new ArrayList<>();
//                try {
//                    JSONObject responseObject = new JSONObject(response);
//                    JSONArray array = responseObject.getJSONArray("results");
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject row = array.getJSONObject(i);
//                        stringList.add(row.getString("trackName"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //IMPORTANT: set data here and notify
//                autoSuggestAdapter.setData(stringList);
//                autoSuggestAdapter.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        to.setTypeface(tf);
        subject.setTypeface(tf);
        message.setTypeface(tf);
        pageTitle.setTypeface(tf);

    }
    @OnClick(R.id.send_message_button)
    public void newMessage(){
        if(validate()) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();
            PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
            String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
            String recipientsString = "<recipients><to><recipient groupTypeId=\"1\">" + selectedToId + "</recipient></to></recipients>";
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            sendMessageRequest.setBody(message.getText().toString().trim());
            sendMessageRequest.setContextTypeId(1);
            sendMessageRequest.setMapId(2);
            sendMessageRequest.setRecipientsInfo(recipientsString);
            sendMessageRequest.setSubject(subject.getText().toString().trim());
            messagesApi.sendNotification(bearerToken, sendMessageRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MessagesResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MessagesResponse messagesResponse) {
                            progressDialog.dismiss();
                            if (messagesResponse.isIsSuccess() == true) {
                                getActivity().finish();
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
    }
//    class BookAutoCompleteAdapter extends BaseAdapter implements Filterable {
//
//        private static final int MAX_RESULTS = 10;
//        private Context mContext;
//        List<SearchUserResponse.Response> usersList = new ArrayList<>();
//
//        public BookAutoCompleteAdapter(Context context) {
//            mContext = context;
//        }
//
//        @Override
//        public int getCount() {
//            return usersList.size();
//        }
//
//        @Override
//        public SearchUserResponse.Response getItem(int index) {
//            return usersList.get(index);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) mContext
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
//            }
//            ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getTagName());
//            //  ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).getAuthor());
//            return convertView;
//        }
//
//        @Override
//        public Filter getFilter() {
//            Filter filter = new Filter() {
//                @Override
//                protected FilterResults performFiltering(CharSequence constraint) {
//                    FilterResults filterResults = new FilterResults();
//                    if (constraint != null) {
//                        List<SearchUserResponse.Response> books = findRecipients(mContext, constraint.toString());
//
//                        // Assign the data to the FilterResults
//                        filterResults.values = books;
//                        filterResults.count = books.size();
//                    }
//                    return filterResults;
//                }
//
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults results) {
//                    if (results != null && results.count > 0) {
//                        usersList = (List<SearchUserResponse.Response>) results.values;
//                        notifyDataSetChanged();
//                    } else {
//                        notifyDataSetInvalidated();
//                    }
//                }};
//            return filter;
//        }
//
//        /**
//         * Returns a search result for the given book title.
//         */
//
//    }
    private Boolean validate(){
        Boolean flag = true;
        String msgString = message.getText().toString().trim();
        String subjectString = subject.getText().toString().trim();
        if(selectedToId != null && selectedToId > 0){
            to.setError(null);
            if(msgString.isEmpty())
            {
                flag = false;
                message.setError("Message should not be empty");
            }
            else{
                message.setError(null);
                if(subjectString.isEmpty()){
                    flag = false;
                    subject.setError("Subject should not be empty");
                }
                else{
                    subject.setError(null);
                }
            }

        }
        else{
            flag = false;
            to.setError("To should not be empty");
        }

        return flag;
    }
    private List<SearchUserResponse.Response> findRecipients(final Context context, String keyword) {
        // GoogleBooksProtocol is a wrapper for the Google Books API
        //  GoogleBooksProtocol protocol = new GoogleBooksProtocol(context, MAX_RESULTS);
        //  return protocol.find(bookTitle);

        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(context, Constants.PREF_KEY_BEARER_TOKEN, "");
        SearchUserRequest searchUserRequest = new SearchUserRequest();
        SearchUserRequest.Value value = searchUserRequest.new Value();
        value.setKeyword(keyword);
        value.setCategoryId(7);
        value.setOrgID(0);
        value.setTagContextName("SOM");
        searchUserRequest.setValue(value);
//        if(Common.isNetworkAvailable(context)) {
        messagesApi.searchUsernames(bearerToken, searchUserRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchUserResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        // progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchUserResponse searchUserResponse) {
                        if (searchUserResponse.getIsSuccess()) {
                           // usersList.addAll(searchUserResponse.getResponse());
                            autoSuggestAdapter.setData(searchUserResponse.getResponse());
                            autoSuggestAdapter.notifyDataSetChanged();
                        } else if (searchUserResponse.getErrorCode() != 200) {
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    context,
                                    R.string.text_failed,
                                    searchUserResponse.getErrorMessage(),
                                    null);
                        }
                    }
                });
//        }
//        else{
//
//        }
        return usersList;
    }
}
