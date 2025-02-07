package yuri.lechshnko.com.presentation.base;

import dagger.android.support.DaggerAppCompatDialogFragment;

public interface ActivityView {
    <T>void transactionActivity(Class<?> activity,T object);

    void transactionActivity(Class<?> activity);

//    void transitionFragment(DaggerFragment fragment, int container);

    void transitionFragmentDialog(DaggerAppCompatDialogFragment fragment, int container);

    void titleAppBar(String val);

    void back();

    void restart();
}
