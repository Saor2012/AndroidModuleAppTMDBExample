package yuri.lechshnko.com.presentation.route;

import yuri.lechshnko.com.presentation.ConstantsApp;

import com.example.mytopmovies.data.BaseModel;
import yuri.lechshnko.com.presentation.base.ActivityView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Route implements IRoute {
    private ActivityView view;

    @Inject
    public Route() {
    }


    @Override
    public void onStart(ActivityView view) {
        this.view = view;
    }

    @Override
    public void onStop() {
        if (view != null) view = null;
    }

    @Override
    public void transaction(String routeFragment, BaseModel object, String jsonObject) {
        switch (routeFragment){
            case ConstantsApp.MY_FRAGMENT :
//                view.transitionFragment(ExampleFragment.newInstance(jsonObject,null),
//                        R.id.main_content);
//                break;
        }
    }

    private int getResLayout(int resLayout){
        switch (resLayout){
//            case ConstantsApp
//                    .MAIN_RES : return R.id.main_content;
            default: return 0;
        }
    }

    @Override
    public void back() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void titleAppBar(String val) {

    }
}

