package yuri.lechshnko.com.presentation.activity;
//import yuri.lechshnko.com.presentation.activity.model.*;
import com.example.mytopmovies.data.BaseModel;

import yuri.lechshnko.com.presentation.base.ActivityView;
import yuri.lechshnko.com.presentation.base.BasePresenter;

public interface TopMoviesContract {

    interface View extends ActivityView {

        void updateData(BaseModel baseModel);

        void showSnackbar(String s);

        void setRefreshing(boolean active);
    }

    interface Presenter extends BasePresenter {

        void loadData(int currentPage);

        void rxUnsubscribe();

        void setView(TopMoviesContract.View view);

        void onLoadNextPage();
    }

}
