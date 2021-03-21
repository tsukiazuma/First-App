package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.R;

public class MonAnDetailActivity extends AppCompatActivity {
    EditText maMonAn, tenMonAn, moTa, chatLuong, giaban, soluong;
    MonAnDAO monAnDAO;
    Spinner spnTheLoaisua;
    String masua, tensua, moTasua, chatLuongsua, giasua, sosua, matheloai, maMonAnk;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT MÓN ĂN");
        setContentView(R.layout.activity_mon_an_detail);
        tenMonAn = findViewById(R.id.edTenSachsua);
        maMonAn = findViewById(R.id.edMaSachsua);
        moTa = findViewById(R.id.edTacGiasua);
        chatLuong = findViewById(R.id.edNXBsua);
        giaban = findViewById(R.id.edGiaBiasua);
        soluong = findViewById(R.id.edSoLuongsua);
        TextView text = findViewById(R.id.text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/svn-hello_sweets.otf");
        text.setTypeface(type);
        monAnDAO = new MonAnDAO(this);
        Intent in = getIntent();
        Bundle k = in.getExtras();

        maMonAnk = k.getString("MAMONAN");
        tensua = k.getString("TENMONAN");
        moTasua = k.getString("MOTA");
        chatLuongsua = k.getString("CHATLUONG");
        giasua = k.getString("GIABIA");
        sosua = k.getString("SOLUONG");

        maMonAn.setText(maMonAnk);
        tenMonAn.setText(tensua);
        moTa.setText(moTasua);
        chatLuong.setText(chatLuongsua);
        giaban.setText(giasua);
        soluong.setText(sosua);
    }*/

    public void capsua(View view) {
        try {
            if (monAnDAO.updateMonAn(maMonAnk, maMonAn.getText().toString(), tenMonAn.getText().toString(), moTa.getText().toString(), chatLuong.getText().toString(), Double.parseDouble(giaban.getText().toString()), Integer.parseInt(soluong.getText().toString())) > 0) {
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Lưu thất bại",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            String s = giaban.getText().toString();
            String t = soluong.getText().toString();
            if (maMonAn.getText().length() == 0 || tenMonAn.getText().length() == 0
                    || moTa.getText().length() == 0 || chatLuong.getText().length() == 0
                    || giaban.getText().length() == 0 || soluong.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            }
            try {
                Double.parseDouble(s);
                Integer.parseInt(t);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Kiểm tra định dạng giá bán và số lượng ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void quaysua(View view) {
        finish();
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

