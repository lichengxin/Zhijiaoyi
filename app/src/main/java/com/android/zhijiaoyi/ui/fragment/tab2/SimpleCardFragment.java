package com.android.zhijiaoyi.ui.fragment.tab2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleCardFragment extends BaseFragment {


    private String mTitle;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_simple_card, null);
        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
        card_title_tv.setText(mTitle);

        return v;
    }

}
