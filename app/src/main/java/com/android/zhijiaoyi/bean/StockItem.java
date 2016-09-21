package com.android.zhijiaoyi.bean;

/**
 * 作者： Li
 * 时间： 2016/9/21 15:19
 * QQ 1205303495
 * Expalin
 */

public class StockItem {
    public StockItem(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public String stockCode = null;
    public String stockName = null;
    private boolean selected;
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
