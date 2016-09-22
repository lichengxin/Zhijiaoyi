package com.android.zhijiaoyi.bean;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private boolean isShowText;
    private boolean isShowImg;

    public StockItem(TextView tv, ImageView iv) {
        this.tv = tv;
        this.iv = iv;
        if (isShowText()) {
            iv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }
    }

    private TextView tv;
    private ImageView iv;

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public boolean isShowText() {
        return isShowText;
    }

    public void setShowText(boolean showText) {
        isShowText = showText;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public void setShowImg(boolean showImg) {
        isShowImg = showImg;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
