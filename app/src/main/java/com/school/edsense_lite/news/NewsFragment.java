package com.school.edsense_lite.news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.SectionResponse;
import com.school.edsense_lite.today.NewsRequest;
import com.school.edsense_lite.today.NewsResponse;
import com.school.edsense_lite.today.TodayApi;
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

public class NewsFragment extends BaseFragment {
    @BindView(R.id.newsRecyclerview)
    RecyclerView newsRecyclerView;

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    @Inject
    TodayApi todayApi;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);

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
        if(!bearerToken.isEmpty()) {
            todayApi.getNews(bearerToken, newsRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            System.out.println("error called::"+e.fillInStackTrace());
                            progressDialog.dismiss();
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
                                newsRecyclerViewAdapter.setItems(new ArrayList<Object>(newsResponse.getResponse()));
                                newsRecyclerViewAdapter.notifyDataSetChanged();

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

        return view;
    }
    private ArrayList<Object> getNewsList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new News("Bandh", "lorem ipsum","12-12-1998"));
        items.add(new News("Bandasash", "lorem ipsum","12-12-1994"));
        items.add(new News("Bansasadh", "lorem ipsum","12-12-1991"));
        items.add(new News("Bansasadh", "lorem ipsum","12-12-1995"));
        items.add(new News("Basasndh", "lorem ipsum","12-12-1997"));
        items.add(new News("Basasndh", "lorem ipsum","12-12-1994"));
        items.add(new News("Basasandh", "lorem ipsum","12-12-1999"));
        items.add(new News("Basasasndh", "lorem ipsum","12-12-1990"));
        items.add(new News("Basasandh", "lorem ipsum","12-12-1993"));
        items.add(new News("Baasasandh", "lorem ipsum","12-12-1996"));

        return items;
    }
}
