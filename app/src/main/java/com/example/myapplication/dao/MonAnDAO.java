package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.MonAn;

import java.util.ArrayList;
import java.util.List;


public class MonAnDAO {
    public static final String TABLE_NAME = "MonAn";
    public static final String SQL_MON_AN = "CREATE TABLE MonAn (maMonAn text primary key, maTheLoai text, tenMonAn text," + "moTa text, chatLuong text, giaBan double, soLuong number);";
    public static final String TAG = "MonAnDAO";

    //SQLite database
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public MonAnDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Firebase
//    private DatabaseReference mDatabase;
//
//    NonUI nonUI;
//    Context context;
//    String sachId;
//
//
//    public SachDAO(Context context) {
//
//        this.mDatabase = FirebaseDatabase.getInstance().getReference("Sach");
//        this.context = context;
//        this.nonUI = new NonUI(context);
//
//    }
    //insert
    public int inserMonAn(MonAn s) {
        ContentValues values = new ContentValues();
        values.put("maMonAn", s.getMaMonAn());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenMonAn", s.getTenMonAn());
        values.put("moTa", s.getMoTa());
        values.put("chatLuong", s.getChatLuong());
        values.put("giaBan", s.getGiaBan());
        values.put("soLuong", s.getSoLuong());
        if (checkPrimaryKey(s.getMaMonAn())) {
            int result = db.update(TABLE_NAME, values, "maMonAn=?", new
                    String[]{s.getMaMonAn()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<MonAn> getAllMonAn() {
        List<MonAn> dsMonAn = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            MonAn s = new MonAn();
            s.setMaMonAn(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenMonAn(c.getString(2));
            s.setMoTa(c.getString(3));
            s.setChatLuong(c.getString(4));
            s.setGiaBan(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsMonAn.add(s);
            Log.d("//=====", s.toString());
            c.moveToNext();
        }
        c.close();
        return dsMonAn;
    }

    //update
    public int updateMonAn(String maMonAn, String a, String b, String c, String d, double e, int f) {
        ContentValues values = new ContentValues();
        values.put("maMonAn", a);
        values.put("tenMonAn", b);
        values.put("moTa", c);
        values.put("chatLuong", d);
        values.put("giaBan", e);
        values.put("soLuong", f);
        int result = db.update(TABLE_NAME, values, "maMonAn=?", new
                String[]{maMonAn});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteMonAnByID(String maMonAn) {
        int result = db.delete(TABLE_NAME, "maMonAn=?", new String[]{maMonAn});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"maMonAn"};
        //WHERE clause
        String selection = "maMonAn=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //check
    public MonAn checkBook(String strPrimaryKey) {
        MonAn s = new MonAn();
        //SELECT
        String[] columns = {"maMonAn"};
        //WHERE clause
        String selection = "maMonAn=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                s.setMaMonAn(c.getString(0));
                s.setMaTheLoai(c.getString(1));
                s.setTenMonAn(c.getString(2));
                s.setMoTa(c.getString(3));
                s.setChatLuong(c.getString(4));
                s.setGiaBan(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//=====", s.toString());
                break;
            }
            c.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //getAll
    public MonAn getMonAnByID(String maMonAn) {
        MonAn s = null;
        //WHERE clause
        String selection = "maMonAn=?";
        //WHERE clause arguments
        String[] selectionArgs = {maMonAn};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getMonAnByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            s = new MonAn();
            s.setMaMonAn(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenMonAn(c.getString(2));
            s.setMoTa(c.getString(3));
            s.setChatLuong(c.getString(4));
            s.setGiaBan(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }

    //getAll
    public List<MonAn> getMonAnTop10(String month) {
        List<MonAn> dsMonAn = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sSQL = "SELECT maMonAn, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " + "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '" + month + "' " +
                "GROUP BY maMonAn ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Log.d("//=====", c.getString(0));
            MonAn s = new MonAn();
            s.setMaMonAn(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBan(0);
            s.setMaTheLoai("");
            s.setTenMonAn("");
            s.setMoTa("");
            s.setChatLuong("");
            dsMonAn.add(s);
            c.moveToNext();
        }
        c.close();
        return dsMonAn;
    }
}


