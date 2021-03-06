package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.dao.HoaDonChiTietDAO;
import com.example.myapplication.model.HoaDonChiTiet;
import com.example.myapplication.R;

import java.util.List;


public class HoaDonChiTietAdapter extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public HoaDonChiTietAdapter(Activity context, List<HoaDonChiTiet> arrayHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrayHoaDonChiTiet;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);
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
            convertView = inflater.inflate(R.layout.item_hoadonchitiet, null);
            holder.txtMaMonAn = convertView.findViewById(R.id.tvMaSach);
            holder.txtSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.txtGiaBan = convertView.findViewById(R.id.tvGiaBia);
            holder.txtThanhTien = convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(position).getMaHDCT()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        HoaDonChiTiet _entry = arrHoaDonChiTiet.get(position);
        holder.txtMaMonAn.setText("M?? m??n: " + _entry.getMonAn().getMaMonAn());
        holder.txtSoLuong.setText("S??? l?????ng: " + _entry.getSoLuongMua());
        holder.txtGiaBan.setText("Gi?? b??n: " + _entry.getMonAn().getGiaBan() + " vnd");
        holder.txtThanhTien.setText("Th??nh ti???n: " + _entry.getSoLuongMua() * _entry.getMonAn().getGiaBan() + " vnd");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonChiTiet> items) {
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView txtMaMonAn;
        TextView txtSoLuong;
        TextView txtGiaBan;
        TextView txtThanhTien;
        ImageView imgDelete;
    }
}