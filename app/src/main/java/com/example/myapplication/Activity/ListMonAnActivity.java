package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.adapter.MonAnAdapter;
import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.model.MonAn;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListMonAnActivity extends AppCompatActivity {
    public static List<MonAn> dsMonAn = new ArrayList<>();
    ListView lvFood;
    MonAnAdapter adapter = null;
    MonAnDAO monAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MÓN ĂN");
        setContentView(R.layout.activity_list_mon_an);
        lvFood = findViewById(R.id.lvFood);
        monAnDAO = new MonAnDAO(ListMonAnActivity.this);
        dsMonAn = monAnDAO.getAllMonAn();
        adapter = new MonAnAdapter(this, dsMonAn);
        lvFood.setAdapter(adapter);
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn monAn = (MonAn) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListMonAnActivity.this, MonAnDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("MAMONAN", monAn.getMaMonAn());
                b.putString("MATHELOAI", monAn.getMaTheLoai());
                b.putString("TENMONAN", monAn.getTenMonAn());
                b.putString("MOTA", monAn.getMoTa());
                b.putString("CHATLUONG", monAn.getChatLuong());
                b.putString("GIABAN", String.valueOf(monAn.getGiaBan()));
                b.putString("SOLUONG", String.valueOf(monAn.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvFood.setTextFilterEnabled(true);
        EditText edSeach = findViewById(R.id.edSearchFood);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mon_an, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them:
                Intent intent = new Intent(ListMonAnActivity.this, ThemMonAnActivity.class);
                startActivity(intent);
                return (true);
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
