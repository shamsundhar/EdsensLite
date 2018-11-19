package com.school.edsense_lite.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.school.edsense_lite.BaseActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.injection.components.AppComponent;
import com.school.edsense_lite.injection.components.DaggerAppComponent;
import com.school.edsense_lite.injection.components.DaggerFragmentComponent;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.utils.Common;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.http2.StreamResetException;

//public class BookAutoCompleteAdapter extends BaseAdapter implements Filterable {
//
//    private static final int MAX_RESULTS = 10;
//    private Context mContext;
//    @Inject
//    MessagesApi messagesApi;
//   // private List<Recipient> resultList = new ArrayList<Recipient>();
//    List<SearchUserResponse.Response> usersList = new ArrayList<>();
//
//    public BookAutoCompleteAdapter(Context context) {
//        mContext = context;
//    }
//
//    @Override
//    public int getCount() {
//        return usersList.size();
//    }
//
//    @Override
//    public SearchUserResponse.Response getItem(int index) {
//        return usersList.get(index);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) mContext
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
//        }
//        ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getTagName());
//        //  ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).getAuthor());
//        return convertView;
//    }
//
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if (constraint != null) {
//                    List<SearchUserResponse.Response> books = findRecipients(mContext, constraint.toString());
//
//                    // Assign the data to the FilterResults
//                    filterResults.values = books;
//                    filterResults.count = books.size();
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                if (results != null && results.count > 0) {
//                    usersList = (List<SearchUserResponse.Response>) results.values;
//                    notifyDataSetChanged();
//                } else {
//                    notifyDataSetInvalidated();
//                }
//            }};
//        return filter;
//    }
//
//    /**
//     * Returns a search result for the given book title.
//     */
//    private List<SearchUserResponse.Response> findRecipients(final Context context, String keyword) {
//        // GoogleBooksProtocol is a wrapper for the Google Books API
//        //  GoogleBooksProtocol protocol = new GoogleBooksProtocol(context, MAX_RESULTS);
//        //  return protocol.find(bookTitle);
//
//        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
//        String bearerToken = preferenceHelper.getString(context, Constants.PREF_KEY_BEARER_TOKEN, "");
//        SearchUserRequest searchUserRequest = new SearchUserRequest();
//        SearchUserRequest.Value value = searchUserRequest.new Value();
//        value.setKeyword(keyword);
//        value.setCategoryId(7);
//        value.setOrgID(0);
//        value.setTagContextName("SOM");
//        searchUserRequest.setValue(value);
////        if(Common.isNetworkAvailable(context)) {
//            messagesApi.searchUsernames(bearerToken, searchUserRequest)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<SearchUserResponse>() {
//                        @Override
//                        public void onError(Throwable e) {
//                           // progressDialog.dismiss();
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(SearchUserResponse searchUserResponse) {
//                            if (searchUserResponse.getIsSuccess()) {
//                                usersList.addAll(searchUserResponse.getResponse());
//                            } else if (searchUserResponse.getErrorCode() != 200) {
//                                //display error.
//                                new CustomAlertDialog().showAlert1(
//                                        context,
//                                        R.string.text_failed,
//                                        searchUserResponse.getErrorMessage(),
//                                        null);
//                            }
//                        }
//                    });
////        }
////        else{
////
////        }
//        return usersList;
//    }
//}