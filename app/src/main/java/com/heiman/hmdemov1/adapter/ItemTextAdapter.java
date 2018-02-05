package com.heiman.hmdemov1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heiman.hmdemov1.modle.MainModle;
import com.heiman.hmdemov1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTextAdapter extends BaseAdapter {

    private List<MainModle> objects = new ArrayList<MainModle>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemTextAdapter(Context context,List<MainModle> list) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = list;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MainModle getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_text, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MainModle)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MainModle object, ViewHolder holder) {
        //TODO implement

        holder.tvName.setText(object.getName());
    }

    static class ViewHolder {
        @BindView(R.id.tv_name) TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
