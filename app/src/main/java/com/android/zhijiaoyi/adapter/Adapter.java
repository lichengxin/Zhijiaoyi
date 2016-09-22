package com.android.zhijiaoyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.bean.DianZan;

import java.util.List;

public class Adapter extends BaseAdapter {

	private List<DianZan> list;
	private Context context;

	public Adapter(Context context, List<DianZan> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.imageView1);
			holder.imageView1 = (ImageView) convertView
					.findViewById(R.id.imageView2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (list.get(position).isDianZan()) {
			holder.imageView.setImageResource(R.drawable.dianzan1);

		} else {
			holder.imageView.setImageResource(R.drawable.dianzan);

		}
		if (list.get(position).isShouCang()) {
			holder.imageView1.setImageResource(R.drawable.shoucang1);

		} else {
			holder.imageView1.setImageResource(R.drawable.shoucang);

		}
		holder.imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.get(position).setDianZan(!list.get(position).isDianZan());

				Adapter.this.notifyDataSetChanged();
			}
		});
		holder.imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.get(position)
						.setShouCang(!list.get(position).isShouCang());
				Adapter.this.notifyDataSetChanged();
			}
		});
		// holder.imageView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// list.get(position).setDianZan(!list.get(position).isDianZan());
		// if (list.get(position).isDianZan()) {
		// holder.imageView.setImageResource(R.drawable.dianzan1);
		// Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
		// } else {
		// holder.imageView.setImageResource(R.drawable.dianzan);
		// Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT).show();
		// }
		// }
		// });
		// holder.imageView1.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// list.get(position)
		// .setShouCang(!list.get(position).isShouCang());
		// if (list.get(position).isShouCang()) {
		// holder.imageView1.setImageResource(R.drawable.shoucang1);
		// Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
		// } else {
		// holder.imageView1.setImageResource(R.drawable.shoucang);
		// Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
		// }
		// }
		// });
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		ImageView imageView1;
	}

}
