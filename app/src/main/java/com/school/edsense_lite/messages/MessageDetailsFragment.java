package com.school.edsense_lite.messages;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.NavigationDrawerActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.KEY_MESSAGE_ID;
import static com.school.edsense_lite.utils.Constants.KEY_MESSAGE_MAP_ID;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_AVATAR_URL;

public class MessageDetailsFragment extends BaseFragment {

    @BindView(R.id.txt_msg_title)
    TextView txt_msg_title;
    @BindView(R.id.txt_msg_body)
    TextView txt_msg_body;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_sender_name)
    TextView txt_sender_name;
    @BindView(R.id.txt_sender_mail)
    TextView txt_sender_mail;

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

        View view = inflater.inflate(R.layout.layout_message_details, container, false);

        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        applyFonts();

        displayMessage();



        return view;

    }

    private void displayMessage(){
        MessageDetailsRequest messageDetailsRequest = new MessageDetailsRequest();
        messageDetailsRequest.setValue(new MessageDetailsRequest.Value());

        messageDetailsRequest.getValue().setId(getArguments().getString(KEY_MESSAGE_ID));
        messageDetailsRequest.getValue().setMapId(getArguments().getString(KEY_MESSAGE_MAP_ID));



        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        messagesApi.fetchNotificationDetails(bearerToken, messageDetailsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageDetailsResponse>() {
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
                    public void onNext(MessageDetailsResponse messagesResponse) {
                        progressDialog.dismiss();
                        if (messagesResponse.isIsSuccess() == true) {

                            txt_msg_title.setText(messagesResponse.getResponse().getRows().get(0).getSubject());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                txt_msg_body.setText(Html.fromHtml(messagesResponse.getResponse().getRows().get(0).getBody(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                txt_msg_body.setText(Html.fromHtml(messagesResponse.getResponse().getRows().get(0).getBody()));
                            }
                            txt_sender_name.setText(messagesResponse.getResponse().getRows().get(0).getToName());
                            txt_sender_mail.setText(messagesResponse.getResponse().getRows().get(0).getSenderemail());



                            PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
                            String primaryUrl = preferenceHelper.getString(getActivity(),Constants.PREF_KEY_SUBSCRIPTION_PRIMARY_URL,"");
                            //String userAvatarUrl = preferenceHelper.getString(getActivity(), KEY_PREF_AVATAR_URL, "");

                            Picasso.with(getActivity().getApplicationContext())
                                    .load(primaryUrl+messagesResponse.getResponse().getRows().get(0).getSenderimmageurl()).fit()
                                    .placeholder(R.drawable.logo)
                                    .error(R.drawable.logo)
                                    .into(imageView);

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

    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

//        to.setTypeface(tf);
//        subject.setTypeface(tf);
//        message.setTypeface(tf);
//        pageTitle.setTypeface(tf);

    }
}
