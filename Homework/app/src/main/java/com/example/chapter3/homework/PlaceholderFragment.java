package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;
import java.util.Objects;

public class PlaceholderFragment extends Fragment {
    static String[] friendList = {
            "带带大师兄",
            "川建国",
            "奥观海",
            "明仁天皇",
            "YYUT",
            "高槻律" };
    
    ListView listView;
    LottieAnimationView animationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = Objects.requireNonNull(getView()).findViewById(R.id.list_view);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_list_item_1, friendList);
        listView.setAdapter(itemsAdapter);
        animationView = getView().findViewById(R.id.animation_view_ex3);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setVisibility(View.VISIBLE);
                listView.setAlpha(0f);
                listView.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .setListener(null);

                animationView.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationView.setVisibility(View.GONE);
                            }
                        });
            }
        }, 5000);
    }
}
