package com.example.maoyan.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maoyan.Bean.SeatBean;

import java.util.ArrayList;
import java.util.List;

public class SeatHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SeatHelperDB";
    private static final String TABLE_SEATS = "seats";

    private static final String KEY_ID = "id";
    private static final String KEY_IMG = "img";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_SEAT = "seat";
    private static final String KEY_SELECTED = "selected";
    private static final String KEY_TOTAL_PRICE = "total_price";

    public SeatHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEATS_TABLE = "CREATE TABLE " + TABLE_SEATS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMG + " TEXT,"
                + KEY_MOVIE_NAME + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_SEAT + " INTEGER,"
                + KEY_SELECTED + " INTEGER,"
                + KEY_TOTAL_PRICE + " TEXT"
                + ")";
        db.execSQL(CREATE_SEATS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEATS);
        onCreate(db);
    }

    public boolean addSeat(String img, String movieName, String username, int seat, boolean selected, String totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMG, img);
        values.put(KEY_MOVIE_NAME, movieName);
        values.put(KEY_USERNAME, username);
        values.put(KEY_SEAT, seat);
        values.put(KEY_SELECTED, selected);
        values.put(KEY_TOTAL_PRICE, totalPrice);

        long result = db.insert(TABLE_SEATS, null, values);
        db.close();

        return result != -1;
    }


    public boolean checkSeatExistence(String movieName, int seat) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_ID};
        String selection = KEY_MOVIE_NAME + " = ? AND " + KEY_SEAT + " = ?";
        String[] selectionArgs = {movieName, String.valueOf(seat)};
        Cursor cursor = db.query(TABLE_SEATS, columns, selection, selectionArgs, null, null, null);

        boolean seatExists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return seatExists;
    }

    public List<SeatBean> getAllSeatsByUsername(String username) {
        List<SeatBean> seatsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_IMG, KEY_MOVIE_NAME, KEY_USERNAME, KEY_SEAT, KEY_SELECTED, KEY_TOTAL_PRICE};
        String selection = KEY_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_SEATS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                SeatBean seatBean = new SeatBean();
                seatBean.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMG)));
                seatBean.setMovieName(cursor.getString(cursor.getColumnIndex(KEY_MOVIE_NAME)));
                seatBean.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                seatBean.setSeatNumber(cursor.getInt(cursor.getColumnIndex(KEY_SEAT)));
                seatBean.setSelected(cursor.getInt(cursor.getColumnIndex(KEY_SELECTED)) == 1);
                seatBean.setTotalPrice(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_PRICE)));
                seatsList.add(seatBean);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return seatsList;
    }



    public List<SeatBean> getAllSeatsByMovie(String movieName) {
        List<SeatBean> seatsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_IMG, KEY_MOVIE_NAME, KEY_USERNAME, KEY_SEAT, KEY_SELECTED, KEY_TOTAL_PRICE};
        String selection = KEY_MOVIE_NAME + " = ?";
        String[] selectionArgs = {movieName};
        Cursor cursor = db.query(TABLE_SEATS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                SeatBean seatBean = new SeatBean();
                seatBean.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMG)));
                seatBean.setMovieName(cursor.getString(cursor.getColumnIndex(KEY_MOVIE_NAME)));
                seatBean.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                seatBean.setSeatNumber(cursor.getInt(cursor.getColumnIndex(KEY_SEAT)));
                seatBean.setSelected(cursor.getInt(cursor.getColumnIndex(KEY_SELECTED)) == 1);
                seatBean.setTotalPrice(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_PRICE)));
                seatsList.add(seatBean);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return seatsList;
    }



    public boolean deleteDataByUsernameAndSeat(String username, int seat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = KEY_USERNAME + " = ? AND " + KEY_SEAT + " = ?";
        String[] whereArgs = {username, String.valueOf(seat)};
        int rowsDeleted = db.delete(TABLE_SEATS, whereClause, whereArgs);
        db.close();

        return rowsDeleted > 0;
    }


    public boolean updateUsernameBySeat(String newUsername, int seat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, newUsername);

        String whereClause = KEY_SEAT + " = ?";
        String[] whereArgs = {String.valueOf(seat)};

        int rowsUpdated = db.update(TABLE_SEATS, values, whereClause, whereArgs);
        db.close();

        return rowsUpdated > 0;
    }


}