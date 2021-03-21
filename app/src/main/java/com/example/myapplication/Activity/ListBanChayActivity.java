package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.MonAnAdapter;
import com.example.myapplication.dao.MonAnDAO;
import com.example.myapplication.model.MonAn;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListBanChayActivity extends AppCompatActivity {
    public static List<MonAn> dsMonAn = new ArrayList<>();
    ListView lvFood;
    MonAnAdapter adapter = null;
    MonAnDAO monAnDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("BÁN CHẠY");
        setContentView(R.layout.activity_list_ban_chay);
        lvFood = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView text = findViewById(R.id.text);
    }

    public void VIEW_MON_AN_TOP_10(View view) {
        try {
            if (Integer.parseInt(edThang.getText().toString()) > 13 ||
                    Integer.parseInt(edThang.getText().toString()) < 0) {
                Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
            } else {
                monAnDAO = new MonAnDAO(ListBanChayActivity.this);
                dsMonAn = monAnDAO.getMonAnTop10(edThang.getText().toString());
                adapter = new MonAnAdapter(this, dsMonAn);
                lvFood.setAdapter(adapter);
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Lỗi nhập không đúng kí tự", Toast.LENGTH_SHORT).show();
        }
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
