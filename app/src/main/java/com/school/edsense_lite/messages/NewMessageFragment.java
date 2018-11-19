package com.school.edsense_lite.messages;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
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

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.RECIPIENT_DELIMETER;

public class NewMessageFragment extends BaseFragment {


    @BindView(R.id.txtMsg)
    EditText message;
    @BindView(R.id.txtSub)
    EditText subject;
    @BindView(R.id.toEditText)
    DelayAutoCompleteTextView to;
    @BindView(R.id.ccEditText)
    DelayAutoCompleteTextView cc;
    private int THRESHOLD = 4;
    @Inject
    MessagesApi messagesApi;

    Integer selectedToId, selectedCcId;
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

        to.setThreshold(THRESHOLD);
        to.setAdapter(new BookAutoCompleteAdapter(getActivity())); // 'this' is Activity instance
        to.setLoadingIndicator(
                (android.widget.ProgressBar) view.findViewById(R.id.pb_loading_indicator));
        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SearchUserResponse.Response recipient = (SearchUserResponse.Response) adapterView.getItemAtPosition(position);
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

        cc.setThreshold(THRESHOLD);
        cc.setAdapter(new BookAutoCompleteAdapter(getActivity())); // 'this' is Activity instance
        cc.setLoadingIndicator(
                (android.widget.ProgressBar) view.findViewById(R.id.cc_pb_loading_indicator));
        cc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SearchUserResponse.Response recipient = (SearchUserResponse.Response) adapterView.getItemAtPosition(position);
//                String existingString = cc.getText().toString().trim();
//                if(existingString.length() > 0)
//                {
//                    existingString = existingString + RECIPIENT_DELIMETER + recipient.getTagName();
//                }
//                else {
//                    existingString = recipient.getTagName();
//                }
                cc.setText(recipient.getTagName());
                selectedCcId = recipient.getTagId();
            }
        });

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

    class BookAutoCompleteAdapter extends BaseAdapter implements Filterable {

        private static final int MAX_RESULTS = 10;
        private Context mContext;
        List<SearchUserResponse.Response> usersList = new ArrayList<>();

        public BookAutoCompleteAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return usersList.size();
        }

        @Override
        public SearchUserResponse.Response getItem(int index) {
            return usersList.get(index);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getTagName());
            //  ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).getAuthor());
            return convertView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        List<SearchUserResponse.Response> books = findRecipients(mContext, constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = books;
                        filterResults.count = books.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        usersList = (List<SearchUserResponse.Response>) results.values;
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }};
            return filter;
        }

        /**
         * Returns a search result for the given book title.
         */
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
                                usersList.addAll(searchUserResponse.getResponse());
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
}
