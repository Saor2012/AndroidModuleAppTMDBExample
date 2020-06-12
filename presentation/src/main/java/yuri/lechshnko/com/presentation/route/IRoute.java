package yuri.lechshnko.com.presentation.route;

import com.example.mytopmovies.data.BaseModel;

import yuri.lechshnko.com.presentation.base.ActivityView;

public interface IRoute {
    void onStart(ActivityView view);

    void onStop();

    void transaction(String routeFragment, BaseModel object, String jsonObject);

    void back();

    void restart();

    void titleAppBar(String val);

}
