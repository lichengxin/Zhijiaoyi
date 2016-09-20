package com.android.zhijiaoyi.bean;

/**
 * 作者： Li
 * 时间： 2016/9/20 9:48
 * QQ 1205303495
 * Expalin
 */

public class StockEntity {

    String stockName;//股票名称
    String stockCode;//股票代码
    String stockTodayPrice;//今日开盘
    String stockYesterdayPrice;//昨日开盘
    String stockCurrentPeice;//当前价格
    String stockDayHighPrice;//今日最高价
    String stockDayLowPrice;//今日最低价
    String stockRoseRate;//涨幅
    String stockDropRate;//跌
    String stockTransactionNums;//成交数量
    String stockTransactionAmounts;// 成交金额

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockTodayPrice() {
        return stockTodayPrice;
    }

    public void setStockTodayPrice(String stockTodayPrice) {
        this.stockTodayPrice = stockTodayPrice;
    }

    public String getStockYesterdayPrice() {
        return stockYesterdayPrice;
    }

    public void setStockYesterdayPrice(String stockYesterdayPrice) {
        this.stockYesterdayPrice = stockYesterdayPrice;
    }

    public String getStockCurrentPeice() {
        return stockCurrentPeice;
    }

    public void setStockCurrentPeice(String stockCurrentPeice) {
        this.stockCurrentPeice = stockCurrentPeice;
    }

    public String getStockDayHighPrice() {
        return stockDayHighPrice;
    }

    public void setStockDayHighPrice(String stockDayHighPrice) {
        this.stockDayHighPrice = stockDayHighPrice;
    }

    public String getStockDayLowPrice() {
        return stockDayLowPrice;
    }

    public void setStockDayLowPrice(String stockDayLowPrice) {
        this.stockDayLowPrice = stockDayLowPrice;
    }

    public String getStockRoseRate() {
        return stockRoseRate;
    }

    public void setStockRoseRate(String stockRoseRate) {
        this.stockRoseRate = stockRoseRate;
    }

    public String getStockDropRate() {
        return stockDropRate;
    }

    public void setStockDropRate(String stockDropRate) {
        this.stockDropRate = stockDropRate;
    }

    public String getStockTransactionNums() {
        return stockTransactionNums;
    }

    public void setStockTransactionNums(String stockTransactionNums) {
        this.stockTransactionNums = stockTransactionNums;
    }

    public String getStockTransactionAmounts() {
        return stockTransactionAmounts;
    }

    public void setStockTransactionAmounts(String stockTransactionAmounts) {
        this.stockTransactionAmounts = stockTransactionAmounts;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockName='" + stockName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockTodayPrice='" + stockTodayPrice + '\'' +
                ", stockYesterdayPrice='" + stockYesterdayPrice + '\'' +
                ", stockCurrentPeice='" + stockCurrentPeice + '\'' +
                ", stockDayHighPrice='" + stockDayHighPrice + '\'' +
                ", stockDayLowPrice='" + stockDayLowPrice + '\'' +
                ", stockRoseRate='" + stockRoseRate + '\'' +
                ", stockDropRate='" + stockDropRate + '\'' +
                ", stockTransactionNums='" + stockTransactionNums + '\'' +
                ", stockTransactionAmounts='" + stockTransactionAmounts + '\'' +
                '}';
    }
}
