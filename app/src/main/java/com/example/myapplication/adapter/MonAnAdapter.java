package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.model.MonAn;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class MonAnAdapter extends BaseAdapter implements Filterable {
    public Activity context;
    public LayoutInflater inflater;
    List<MonAn> arrMonAn;
    List<MonAn> arrSortMonAn;
    MonAnDAO monAnDAO;
    private Filter monAnFilter;

    public MonAnAdapter(Activity context, List<MonAn> arrayMonAn) {
        super();
        this.context = context;
        this.arrMonAn = arrayMonAn;
        this.arrSortMonAn = arrayMonAn;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        monAnDAO = new MonAnDAO(context);
    }

    @Override
    public int getCount() {
        return arrMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return arrMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_mon_an, null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtFoodName = convertView.findViewById(R.id.tvBookName);
            holder.txtFoodPrice = convertView.findViewById(R.id.tvBookPrice);
            holder.txtSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monAnDAO.deleteMonAnByID(arrMonAn.get(position).getMaMonAn());
                    arrMonAn.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        MonAn _entry = arrMonAn.get(position);
        if (position % 3 == 0) {
            holder.img.setImageResource(R.drawable.monan1);
        } else {
            holder.img.setImageResource(R.drawable.monan2);
        }
        holder.txtFoodName.setText("Mã món ăn: " + _entry.getMaMonAn());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        holder.txtFoodPrice.setText("Giá: " + _entry.getGiaBan() + "");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<MonAn> items) {
        this.arrMonAn = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrMonAn = arrSortMonAn;
    }

    public Filter getFilter() {
        if (monAnFilter == null)
            monAnFilter = new CustomFilter();
        return monAnFilter;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtFoodName;
        TextView txtFoodPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortMonAn;
                results.count = arrSortMonAn.size();
            } else {
                List<MonAn> lsMonAn = new ArrayList<MonAn>();
                for (MonAn p : arrMonAn) {
                    if (p.getMaMonAn().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsMonAn.add(p);
                }
                results.values = lsMonAn;
                results.count = lsMonAn.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrMonAn = (List<MonAn>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}