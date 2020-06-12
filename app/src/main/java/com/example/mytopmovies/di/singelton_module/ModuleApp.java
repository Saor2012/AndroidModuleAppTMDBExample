package com.example.mytopmovies.di.singelton_module;

import android.content.Context;

import androidx.room.Room;

import com.example.mytopmovies.App;
import com.example.mytopmovies.data.locale_storage.AppDataBase;
import com.example.mytopmovies.data.locale_storage.LocaleDao;
import yuri.lechshnko.com.presentation.route.IRoute;
import yuri.lechshnko.com.presentation.route.Route;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ModuleApp {

    @Singleton
    @Binds
    abstract Context provideContext(App app);

    @Singleton
    @Provides
    static AppDataBase provideRoomDataBase(App context) {
        return Room.databaseBuilder(
                context.getApplicationContext(), AppDataBase.class, "locale")
                .build();
    }

    @Singleton
    @Provides
    static LocaleDao provideLocaleDao(AppDataBase base){
        return base.getDao();
    }

    @Singleton
    @Provides
    static IRoute provideRoute(){
        return new Route();
    }

}

