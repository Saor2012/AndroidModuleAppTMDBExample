package yuri.lechshnko.com.presentation.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
//import yuri.lechshnko.com.presentation.activity.model.*;
import yuri.lechshnko.com.presentation.ConstantsApp;
import yuri.lechshnko.com.presentation.R;
import yuri.lechshnko.com.presentation.activity.common.DividerItemDecoration;
import yuri.lechshnko.com.presentation.activity.common.ListAdapter;
import yuri.lechshnko.com.presentation.base.BaseActivity;
import yuri.lechshnko.com.presentation.base.BasePresenter;
import yuri.lechshnko.com.presentation.databinding.ActivityTopMoviesBinding;
import yuri.lechshnko.com.presentation.route.IRoute;

import com.example.mytopmovies.data.BaseModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatDialogFragment;

public class TopMoviesActivity extends BaseActivity<ActivityTopMoviesBinding> implements TopMoviesContract.View {

    @Inject
    TopMoviesContract.Presenter presenter;

    @Inject
    IRoute route;

    private ListAdapter listAdapter;
    private List<BaseModel> resultList = new ArrayList<>();
    private boolean endOfList = false;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_top_movies;
    }//R.layout.activity_top_movies

    @Override
    protected void createActivity(@Nullable Bundle savedInstanceState) {
        route.onStart(this);
        listAdapter = new ListAdapter(resultList);

        getBinding().recyclerView.setAdapter(listAdapter);
        getBinding().recyclerView.addItemDecoration(new DividerItemDecoration(this));

        getBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getBinding().recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = Objects.requireNonNull(recyclerView.getLayoutManager()).getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (!getBinding().swiperefresh.isRefreshing() && !endOfList) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - ConstantsApp.PAGINATION_MARGIN
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= ConstantsApp.PAGE_SIZE) {
                        presenter.onLoadNextPage();
                    }
                }
            }
        });
    }

    @Override
    protected void stopActivity() {
        super.onStop();
        presenter.rxUnsubscribe();
        resultList.clear();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void startActivity() {
        getBinding().setEvent(presenter);
        presenter.setView(this);
        presenter.onLoadNextPage();

    }

    @Override
    protected void pauseActivity() {

    }

    @Override
    protected void resumeActivity() {

    }

    @Override
    protected void destroyActivity() {
        if (presenter != null) presenter = null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public <T> void transactionActivity(Class<?> activity, T object) {
        super.onTransactionActivity(activity, true, object);
    }

    @Override
    public void transactionActivity(Class<?> activity) {
        super.onTransactionActivity(activity, true);
    }

//    @Override
//    public void transitionFragment(DaggerFragment fragment, int container) {
//        super.onTransactionFragmentWithBackStack(fragment,container);
//    }

    @Override
    public void transitionFragmentDialog(DaggerAppCompatDialogFragment fragment, int container) {
        //TODO NOT USED
    }

    @Override
    public void titleAppBar(String val) {
        //TODO NOT USED
    }

    @Override
    public void back() {
        super.onBackPressed();
    }

    @Override
    public void restart() {
        super.restartAppBase();
    }

    @Override
    public void updateData(com.example.mytopmovies.data.BaseModel baseModel) {
        resultList.add(baseModel);
        listAdapter.notifyItemInserted(resultList.size() - 1);
    }

    @Override
    public void showSnackbar(String s) {
        Snackbar.make(getBinding().listActivityRootView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshing(boolean active) {
        getBinding().swiperefresh.setRefreshing(active);
    }
}
