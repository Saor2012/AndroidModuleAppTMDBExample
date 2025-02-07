package com.example.mytopmovies.di.singelton_module;

import androidx.databinding.library.baseAdapters.BuildConfig;

import yuri.lechshnko.com.presentation.ConstantsApp;
import com.example.mytopmovies.data.MoreInfoApiService;
import com.example.mytopmovies.data.MovieApiService;
import com.example.mytopmovies.di.Named;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ModuleApi {

    private static OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return builder.build();

    }

    @Named("api_movie")
    @Provides
    static OkHttpClient provideMovieOkHttp(){
        return provideOkHttpClient();
    }

    @Named("api_more")
    @Provides
    static OkHttpClient provideMoreOkHttp(){
        return provideOkHttpClient();
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    static MovieApiService provideApiService(Gson gson, @Named("api_movie") OkHttpClient okHttpClient) {
        return createApi(ConstantsApp.BASE_URL, MovieApiService.class, okHttpClient, GsonConverterFactory.create(gson));
    }

    @Singleton
    @Provides
    static MoreInfoApiService provideNewApiService(Gson gson, @Named("api_more") OkHttpClient okHttpClient) {
        return createApi(ConstantsApp.BASE_URL_INFO, MoreInfoApiService.class, okHttpClient, GsonConverterFactory.create(gson));
    }


    private static <T> T createApi(String baseUrl ,Class<T> serviceApi, OkHttpClient okHttpClient, Converter.Factory factoryConverter) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(factoryConverter);
        return builder.build().create(serviceApi);
    }

}

