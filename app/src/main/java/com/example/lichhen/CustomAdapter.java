package com.example.lichhen;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class CustomAdapter extends BaseAdapter {

    private ArrayList<LichHen> _Contacts;
    private Activity context;
    private LayoutInflater inflater;

    public CustomAdapter(Activity context, ArrayList<LichHen> _Contacts) {
        super();
        this.context = context;
        this._Contacts = _Contacts;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return _Contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return _Contacts.get(position).getTime();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView tvtime, tvten,tvchuthich;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;//xử lý listview, tuy chỉnh 1 view.
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_row_view, null);
            holder.tvtime = (TextView) convertView.findViewById(R.id.tvtime);
            holder.tvten = (TextView) convertView.findViewById(R.id.tvten);
            holder.tvchuthich = (TextView) convertView.findViewById(R.id.tvchuthich);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.tvtime.setText("" + _Contacts.get(position).getTime());
        holder.tvten.setText("" + "" + _Contacts.get(position).getTen());
        holder.tvchuthich.setText("" + "" + _Contacts.get(position).getChuthich());

        return convertView;
    }
}