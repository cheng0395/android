package com.example.maoyan.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maoyan.Bean.ReserveBean;

import java.util.ArrayList;
import java.util.List;

public class ReserveHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reserve.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "reservations";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_MOVIE_NAME = "movieName";
    public static final String COLUMN_TIME = "time";

    public ReserveHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库表
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_MOVIE_NAME + " TEXT, "
                + COLUMN_TIME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级数据库时执行的操作（如果有）
        // 可以根据需要进行数据库表的修改或数据迁移
        // 这里简单地删除旧表并重新创建新表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 添加预订信息到数据库
    public boolean addReservation(String username, String type, String movieName, String time) {
        SQLiteDatabase db = null;
        try {
            // 获取可写的数据库实例
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_TYPE, type);
            values.put(COLUMN_MOVIE_NAME, movieName);
            values.put(COLUMN_TIME, time);

            // 将数据插入到数据库表中
            long result = db.insert(TABLE_NAME, null, values);

            // 返回插入操作是否成功
            return (result != -1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    // 根据电影名和用户名查询是否存在数据
    public boolean checkReservationExistence(String username, String movieName) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            // 获取可读的数据库实例
            db = this.getReadableDatabase();

            // 构建查询语句
            String query = "SELECT * FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_USERNAME + " = ? AND " +
                    COLUMN_MOVIE_NAME + " = ?";

            // 执行查询
            cursor = db.rawQuery(query, new String[]{username, movieName});

            // 如果查询到结果，则返回true，表示存在预订信息
            return cursor != null && cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭数据库连接和游标
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    public List<ReserveBean> getAllReservations() {
        List<ReserveBean> reservations = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            // 获取可读的数据库实例
            db = this.getReadableDatabase();

            // 构建查询语句
            String query = "SELECT * FROM " + TABLE_NAME;

            // 执行查询
            cursor = db.rawQuery(query, null);

            // 遍历结果集并将每条记录添加到列表中
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                    String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                    String movieName = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_NAME));
                    String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));

                    ReserveBean reservation = new ReserveBean(username, type, movieName, time);
                    reservations.add(reservation);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接和游标
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return reservations;
    }

    // 根据用户名和电影名删除预订信息
    public boolean deleteReservation(String username, String movieName) {
        SQLiteDatabase db = null;
        try {
            // 获取可写的数据库实例
            db = this.getWritableDatabase();

            // 构建删除条件
            String whereClause = COLUMN_USERNAME + " = ? AND " + COLUMN_MOVIE_NAME + " = ?";
            String[] whereArgs = {username, movieName};

            // 执行删除操作
            int result = db.delete(TABLE_NAME, whereClause, whereArgs);

            // 返回删除操作是否成功
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


}
