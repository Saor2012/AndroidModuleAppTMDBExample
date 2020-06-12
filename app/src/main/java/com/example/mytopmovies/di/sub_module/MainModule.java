package com.example.mytopmovies.di.sub_module;


import com.example.mytopmovies.data.repository.IRepository;
import com.example.mytopmovies.data.repository.Repository;


import mytopmovies.domain.IInteractor;
import mytopmovies.domain.Interactor;
import yuri.lechshnko.com.presentation.activity.TopMoviesActivity;
import yuri.lechshnko.com.presentation.activity.TopMoviesContract;
import yuri.lechshnko.com.presentation.activity.TopMoviesPresenter;



import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @Binds
    abstract IInteractor bindTopMoviesInteractor(Interactor interactor);

    @Binds
    abstract TopMoviesContract.View bindTopMoviesActivity(TopMoviesActivity view);

    @Binds
    abstract TopMoviesContract.Presenter bindTopMoviesPresenter(TopMoviesPresenter presenter);

    @Binds
    abstract IRepository bindTopMoviesRepository(Repository repository);



}