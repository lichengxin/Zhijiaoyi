package com.android.zhijiaoyi.bean;

import java.util.List;

/**
 * 作者： Li
 * 时间： 2016/9/19 18:25
 * QQ 1205303495
 * Expalin
 */

public class Stock {

    /**
     * c : sh600000
     * n : 浦发银行
     */

    private List<StocksBean> stocks;

    public List<StocksBean> getStocks() {
        return stocks;
    }

    public void setStocks(List<StocksBean> stocks) {
        this.stocks = stocks;
    }

    public static class StocksBean {
        private String c;
        private String n;

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }
    }
}
