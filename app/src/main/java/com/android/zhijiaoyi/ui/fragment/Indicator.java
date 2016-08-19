package com.android.zhijiaoyi.ui.fragment;

import com.android.zhijiaoyi.R;

/**
 * 作者： Li
 * 时间： 2016/8/19 14:57
 * QQ 1205303495
 * Expalin
 */
public enum Indicator {
    FINANCE(0, R.drawable.main_tab0, FinancingFragment.class),
    MARKET(1, R.drawable.main_tab1, MarketFragment.class),
    TRADE(2, R.drawable.main_tab2, TradeFragment.class),
    INFORMATION(3, R.drawable.main_tab3, InformationFragment.class),
    DISCOVER(4, R.drawable.main_tab4, DiscoverFragment.class);

    private int id;
    private int tab;
    private Class<?> clc;

    Indicator(int id, int tab, Class<?> clc) {
        this.id = id;
        this.tab = tab;
        this.clc = clc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public Class<?> getClc() {
        return clc;
    }

    public void setClc(Class<?> clc) {
        this.clc = clc;
    }
}
