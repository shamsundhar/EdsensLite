package com.school.edsense_lite.news;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.SectionResponse;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.today.EventsResponseModel;
import com.school.edsense_lite.today.NewsRequest;
import com.school.edsense_lite.today.NewsResponse;
import com.school.edsense_lite.today.TodayApi;
import com.school.edsense_lite.utils.Common;
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
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.newsRecyclerview)
    RecyclerView newsRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
    @BindView(R.id.tv1)
    TextView tv1;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    @Inject
    TodayApi todayApi;

    public static EdsenseDatabase mEdsenseDatabase;
    private ArrayList<News> newsList;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);

        applyFonts();
        empty_view.setText(R.string.empty_news_list_message);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
        newsRecyclerView.setAdapter(newsRecyclerViewAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        NewsRequest newsRequest = new NewsRequest();
        NewsRequest.Value value = newsRequest.new Value();
        value.setNewsTypeId("2");
        newsRequest.setValue(value);
        if(Common.isNetworkAvailable(getActivity())) {
            if (!bearerToken.isEmpty()) {
                todayApi.getNews(bearerToken, newsRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<NewsResponse>() {
                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                if (e instanceof StreamResetException) {
                                    //login again
                                    e.printStackTrace();
                                    relogin();
                                }
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("complete called");
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("onsubscribe called");
                            }

                            @Override
                            public void onNext(NewsResponse newsResponse) {
                                progressDialog.dismiss();
                                if (newsResponse.getIsSuccess().equals(true)) {
                                    if (newsResponse.getResponse() != null && !newsResponse.getResponse().isEmpty()) {
                                        for(News newsObject : newsResponse.getResponse()){
                                            mEdsenseDatabase.getNewsDao().insert(newsObject);
                                        }
                                    }
                                    displayNewsFromDB();


                                } else if (!newsResponse.getErrorCode().equals(200)) {
                                    //display error.
                                    new CustomAlertDialog().showAlert1(
                                            getActivity(),
                                            R.string.text_failed,
                                            newsResponse.getErrorMessage(),
                                            null);
                                }
                            }
                        });
            }
        }else {
        progressDialog.dismiss();
        displayNewsFromDB();
        }

        return view;
    }

    private void displayNewsFromDB(){
        newsList = (ArrayList<News>)mEdsenseDatabase.getNewsDao().getAllNews();
        if(newsList != null && !newsList.isEmpty()){
            empty_view.setVisibility(View.GONE);
            newsRecyclerView.setVisibility(View.VISIBLE);
            newsRecyclerViewAdapter.setItems(newsList);
            newsRecyclerViewAdapter.notifyDataSetChanged();
        }else{
            empty_view.setVisibility(View.VISIBLE);
            newsRecyclerView.setVisibility(View.GONE);
        }
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        tv1.setTypeface(tf);
    }
}
