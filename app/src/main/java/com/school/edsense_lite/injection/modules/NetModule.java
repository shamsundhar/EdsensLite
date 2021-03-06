package com.school.edsense_lite.injection.modules;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.school.edsense_lite.BuildConfig;
import com.school.edsense_lite.attendance.AttendanceApi;
import com.school.edsense_lite.firebase.FCMApi;
import com.school.edsense_lite.injection.scopes.PerApplication;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.messages.MessagesApi;
import com.school.edsense_lite.subscription.SubsciptionApi;
import com.school.edsense_lite.today.TodayApi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

//    @Provides
//    @PerApplication
//    static Gson provideGson() {
//        return new GsonBuilder()
//                // Custom type adapters for models are not needed when using Gson, but this
//                // type adapter is a good example if you want to write one yourself.
//                .registerTypeAdapter(Country.class, CountryTypeAdapter.INSTANCE)
//                // These type adapters for RealmLists are needed, since RealmString and RealmStringMapEntry
//                // wrappers are not recognized by Gson in the default configuration.
//                .create();
//    }

    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @PerApplication
    static OkHttpClient.Builder provideHttpClientBuilder(OkHttpClient okHttpClient){
        OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();

        //adding request headers interceptor.
//        httpClientBuilder.addInterceptor(new Interceptor() {
//            @Override public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder builder = request.newBuilder();
//                builder.addHeader("Client-Service", "shkang$@)!*");
//                builder.addHeader("Auth-key", "psangady#2018");
//                builder.addHeader("Content-Type", "application/json");
//                builder.build();
//                return chain.proceed(request);
//            }
//        });
        //adding logging interceptor
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder;
    }

    @Provides
    @PerApplication
    static LoginApi provideLoginApi(OkHttpClient.Builder httpClientBuilder ) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(LoginApi.class);
    }
    @Provides
    @PerApplication
    static TodayApi provideTodayApi(OkHttpClient.Builder httpClientBuilder){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(TodayApi.class);
    }
    @Provides
    @PerApplication
    static AttendanceApi provideAttendanceApi(OkHttpClient.Builder httpClientBuilder){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(AttendanceApi.class);
    }
    @Provides
    @PerApplication
    static SubsciptionApi provideSubscriptionApi(OkHttpClient.Builder httpClientBuilder){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(SubsciptionApi.class);
    }
    @Provides
    @PerApplication
    static MessagesApi provideMessagesApi(OkHttpClient.Builder httpClientBuilder){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(MessagesApi.class);
    }
    @Provides
    @PerApplication
    static FCMApi provideFCMApi(OkHttpClient.Builder httpClientBuilder){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(FCMApi.class);
    }

//    @Provides
//    @PerApplication
//    static ShopApi provideShopApi(OkHttpClient.Builder httpClientBuilder){
//        return new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//                .callFactory(httpClientBuilder.build())
//                .build().create(ShopApi.class);
//    }
}
