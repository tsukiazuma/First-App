package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.HoaDonChiTietAdapter;
import com.example.myapplication.dao.HoaDonChiTietDAO;
import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.model.HoaDon;
import com.example.myapplication.model.HoaDonChiTiet;
import com.example.myapplication.model.MonAn;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class    HoaDonDetailActivity extends AppCompatActivity {

    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    EditText edMaMonAn, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    MonAnDAO monAnDAO;
    ListView lvCart;
    HoaDonChiTietAdapter adapter = null;
    double thanhTien = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THANH TOÁN");
        setContentView(R.layout.activity_hoa_don_detail);
        edMaMonAn = findViewById(R.id.edMaSach);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edSoLuong = findViewById(R.id.edSoLuongMua);
        lvCart = findViewById(R.id.lvCart);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        adapter = new HoaDonChiTietAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonDetailActivity.this);
        monAnDAO = new MonAnDAO(HoaDonDetailActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                MonAn monAn = monAnDAO.getMonAnByID(edMaMonAn.getText().toString());
                if (monAn != null) {
                    int pos = checkMaMonAn(dsHDCT, edMaMonAn.getText().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(), new
                            Date());
                    HoaDonChiTiet hoaDonChiTiet = new
                            HoaDonChiTiet(1, hoaDon, monAn, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã món ăn không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonDetailActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getMonAn().getGiaBan();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaMonAn(List<HoaDonChiTiet> lsHD, String maMonAn) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getMonAn().getMaMonAn().equalsIgnoreCase(maMonAn)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if (edMaMonAn.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() ||
                edMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
