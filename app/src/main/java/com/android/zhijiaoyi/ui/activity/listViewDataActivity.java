package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.bean.StockItem;
import com.android.zhijiaoyi.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ListViewDataActivity extends BaseActivity {

    private List<StockItem> lists = new ArrayList<>();
    private ListView mLvList;
    private SearchAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 1000; i++) {
            lists.add(new StockItem("600001", "浦发银行"));
        }
        initView();
    }

    @Override
    public String returnToolBarTitle() {
        return "listView数据item显示异常";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view_data;
    }

    private void initView() {
        mLvList = (ListView) findViewById(R.id.lv_list);
        adapter = new SearchAdapter();
        mLvList.setAdapter(adapter);
    }

    public class ViewHolder {
        public TextView stockName;
        public TextView stockCode;
        public CheckBox mCheckBox;
//        public ImageView addImage;
//        public TextView addTv;
    }

    public class SearchAdapter extends BaseAdapter {
        //定义一个列表用于保存选中项目
        private List<Integer> mSelect = new ArrayList<Integer>();

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//
//            // TODO Auto-generated method stub
//            final int mPosition = position;
//
//            ViewHolder holder = null;
//            StockItem brandItemInfo = (StockItem) getItem(position);
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(ListViewDataActivity.this).inflate(R.layout.search_item, parent, false);
//                holder.stockName = (TextView) convertView.findViewById(R.id.tv_stockName);
//                holder.stockCode = (TextView) convertView.findViewById(R.id.tv_stockCode);
//                holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
//                final ViewHolder finalViewHolder = holder;
//
//                holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                        StockItem info = (StockItem) finalViewHolder.mCheckBox.getTag();
//                        info.setSelected(compoundButton.isChecked());
//                    }
//                });
//
//
//                convertView.setTag(holder);
//                holder.mCheckBox.setTag(brandItemInfo);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//                holder.mCheckBox.setTag(brandItemInfo);
//            }
//
//
//            final String stockName = lists.get(position).stockName;
//            final String stockCode = lists.get(position).stockCode;
//            holder.stockName.setText(stockName);
//            holder.stockCode.setText(stockCode);
//
//
//            return convertView;
//        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            StockItem brandItemInfo = (StockItem) getItem(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ListViewDataActivity.this).inflate(R.layout.search_item, parent, false);
                holder.stockName = (TextView) convertView.findViewById(R.id.tv_stockName);
                holder.stockCode = (TextView) convertView.findViewById(R.id.tv_stockCode);
                holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                convertView.setTag(holder);
                holder.mCheckBox.setTag(brandItemInfo);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.mCheckBox.setTag(brandItemInfo);
            }


            final String stockName = lists.get(position).stockName;
            final String stockCode = lists.get(position).stockCode;
            final ViewHolder finalViewHolder = holder;
            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    StockItem info = (StockItem) finalViewHolder.mCheckBox.getTag();
                    info.setSelected(compoundButton.isChecked());

                }
            });
            holder.stockName.setText(stockName);
            holder.stockCode.setText(stockCode);
            holder.mCheckBox.setChecked(brandItemInfo.isSelected());
            return convertView;
        }
    }
}
