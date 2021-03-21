package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.dao.TheLoaiDAO;
import com.example.myapplication.model.MonAn;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ThemMonAnActivity extends AppCompatActivity {
    MonAnDAO monAnDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    Button btnADDBOOK;
    EditText edMaMonAn, edTenMonAn, edMoTa, edChatLuong, edGiaBan, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM MÓN ĂN");
        setContentView(R.layout.activity_them_mon_an);
        spnTheLoai = findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaMonAn = findViewById(R.id.edMaSach);
        edTenMonAn = findViewById(R.id.edTenSach);
        edMoTa = findViewById(R.id.edNXB);
        edChatLuong = findViewById(R.id.edTacGia);
        edGiaBan = findViewById(R.id.edGiaBia);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnADDBOOK = findViewById(R.id.btnAddBook);
        //
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheloai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaMonAn.setText(b.getString("MAMONAN"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenMonAn.setText(b.getString("TENMONAN"));
            edMoTa.setText(b.getString("MOTA"));
            edChatLuong.setText(b.getString("CHATLUONG"));
            edGiaBan.setText(b.getString("GIABAN"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }

    }

    public void showSpinner(View view) {
        monAnDAO = new MonAnDAO(ThemMonAnActivity.this);
        monAnDAO.getAllMonAn();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(ThemMonAnActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        try {
            monAnDAO = new MonAnDAO(ThemMonAnActivity.this);
            MonAn monAn = new MonAn(edMaMonAn.getText().toString(), maTheLoai, edTenMonAn.getText().toString(), edChatLuong.getText().toString(), edMoTa.getText().toString(), parseDouble(edGiaBan.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
            if (monAnDAO.inserMonAn(monAn) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            String s = edGiaBan.getText().toString();
            String t = edSoLuong.getText().toString();
            if (edMaMonAn.getText().length() == 0 || edMaMonAn.getText().length() == 0
                    || edChatLuong.getText().length() == 0 || edMoTa.getText().length() == 0
                    || edGiaBan.getText().length() == 0 || edSoLuong.getText().length() == 0) {
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

    public void showBook(View view) {
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheloai())) {
                return i;
            }
        }
        return 0;
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
