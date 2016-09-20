package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.bean.Stock;
import com.android.zhijiaoyi.bean.StockEntity;
import com.android.zhijiaoyi.constans.Constant;
import com.android.zhijiaoyi.util.AppJsonFileReader;
import com.android.zhijiaoyi.util.GsonUtil;
import com.android.zhijiaoyi.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class JsonActivity extends AppCompatActivity {

    private FrameLayout mBack;
    private TextView mTitle;
    private TextView mTvDisPlay;
    private List<String> list = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initView();
        String json = AppJsonFileReader.getJson(this, "stock.json");
        Stock stock = GsonUtil.GsonToBean(json, Stock.class);
        List<Stock.StocksBean> stocks = stock.getStocks();
        sb.delete(0, sb.length());
        for (int i = 0; i < 100; i++) {
            list.add(stock.getStocks().get(i).getC());
        }
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(list.get(i)).append(",");
            } else {
                sb.append(list.get(i));
            }
        }
        LogUtils.logE(JsonActivity.class, sb.toString());
        String url = Constant.sina_data + sb;
        HttpRequest.get(url, new StringHttpRequestCallback() {
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);
                LogUtils.logE(JsonActivity.class, "value:" + s);
                parseData(s);
            }


        });

    }

    private List<StockEntity> lists = new ArrayList<>();

    private void parseData(String data) {

        String[] dataArr = data.split(";");
        lists.clear();
        LogUtils.logE(JsonActivity.class, "data:" + dataArr.length);
        for (int i = 0; i < dataArr.length-1; i++) {
            lists.add(splitString(dataArr[i]));
        }
        LogUtils.logE(JsonActivity.class, "size:" + lists.size());
    }

    //解析单条数据
    private StockEntity splitString(String s) {
        StockEntity entity = new StockEntity();
        if (s.contains("=") && s.length() > 40) {
            String[] arr = s.split("=");
            String left = arr[0];
            if (left.length() > 6) {
                String code = left.substring(left.length() - 6, left.length());
                entity.setStockCode(code);
            }
            String right = arr[1];
            if (!right.isEmpty() && right.length() > 30) {
                String[] arrRight = right.split(",");
                entity.setStockName(arrRight[0]);
                entity.setStockTodayPrice(arrRight[1]);
                entity.setStockYesterdayPrice(arrRight[2]);
                entity.setStockCurrentPeice(arrRight[3]);
                entity.setStockDayHighPrice(arrRight[4]);
                entity.setStockDayLowPrice(arrRight[5]);
                entity.setStockTransactionAmounts(arrRight[9] + "元");
                entity.setStockTransactionNums(arrRight[8] + "股");
            } else {
                entity.setStockName("--");
                entity.setStockTodayPrice("--");
                entity.setStockYesterdayPrice("--");
                entity.setStockCurrentPeice("--");
                entity.setStockDayHighPrice("--");
                entity.setStockDayLowPrice("--");
                entity.setStockTransactionAmounts("--");
                entity.setStockTransactionNums("--");
            }
        }
        LogUtils.logE(JsonActivity.class, entity.toString());
        return entity;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mTvDisPlay = (TextView) findViewById(R.id.tv_disPlay);
    }
}
