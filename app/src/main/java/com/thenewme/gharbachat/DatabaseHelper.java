package com.thenewme.gharbachat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "gharbachat";
    static int version = 1;

    String sqlCreateUserTable = "CREATE TABLE if not exists`register` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`confirmpass`\tTEXT,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`gender`\tTEXT\n" +
            ")";
    String sqlCreateExpenseTable = "CREATE TABLE if not exists `expense` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`date`\tTEXT,\n" +
            "\t`amount`\tINTEGER,\n" +
            "\t`category`\tTEXT,\n" +
            "\t`description`\tTEXT,\n" +
            "\t`cashcredit`\tTEXT\n" +
            ")";
    String sqlCreateIncomeTable = "CREATE TABLE  if not exists`income` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`date`\tTEXT,\n"+
            "\t`category`\tTEXT,\n" +
            "\t`amount`\tINTEGER,\n" +
            "\t`description`\tTEXT\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, name,null, version);

        getWritableDatabase().execSQL(sqlCreateUserTable);

        getWritableDatabase().execSQL(sqlCreateExpenseTable);

        getWritableDatabase().execSQL(sqlCreateIncomeTable);
    }

    public void insertUser (ContentValues contentValues)
    {
        getWritableDatabase().insert("register","",contentValues);
    }

    public  void updateuser(String id,ContentValues contentValues)
    {
        getWritableDatabase().update("register",contentValues,"id="+id,null);
    }


    public ArrayList<userinfo> getuserlist()
    {
        String sql= "Select * from register";
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        ArrayList<userinfo> list=new ArrayList<>();
        while(c.moveToNext())
        {
            userinfo info= new userinfo();
            info.id = c.getString(c.getColumnIndex("id"));
            info.username = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            info.confirmpass = c.getString(c.getColumnIndex("confirmpass"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.gender = c.getString(c.getColumnIndex("gender"));
            list.add(info);
        }
        c.close();
        return list;
    }
    public userinfo getuserinfo(String id)
    {
        String sql = "Select * from register where id="+id;Cursor c = getReadableDatabase().rawQuery(sql,null);
        userinfo info= new userinfo();
        while(c.moveToNext())
        {
            info.id = c.getString(c.getColumnIndex("id"));
            info.username = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.confirmpass = c.getString(c.getColumnIndex("confirmpass"));
            info.gender = c.getString(c.getColumnIndex("gender"));
        }
        c.close();
        return info;
    }

    public boolean isloginsuccessful(String username,String password)
    {
        String sql= "Select count (*) from register where username='"+username+"' and password='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();
        if(l==1)
        {
            return true;
        }
        return false;
    }
    public long getUserId(String username,String password)
    {
        String sql= "Select id from register where username='"+username+"' and password='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();
        return l;
    }

    public void insertexpense (ContentValues contentValues)
    {
        getWritableDatabase().insert("expense","",contentValues);
    }

    public void deleteexpense (int id){

        getWritableDatabase().delete("expense","id =" +id,null);

    }

    public void updateexpense(Integer id,ContentValues contentValues){
        getWritableDatabase().update("expense",contentValues,"id="+id,null);
    }

    public ArrayList<ExpenseInfo> getexpenselist()
    {
        String sql= "Select * from expense ";
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        ArrayList<ExpenseInfo> list=new ArrayList<>();
        while(c.moveToNext())
        {
            ExpenseInfo info= new ExpenseInfo();
            info.id = c.getInt(c.getColumnIndex("id"));
            info.date = c.getString(c.getColumnIndex("date"));
            info.amount = c.getInt(c.getColumnIndex("amount"));
            info.category = c.getString(c.getColumnIndex("category"));
            info.description = c.getString(c.getColumnIndex("description"));
            info.cashcredit = c.getString(c.getColumnIndex("cashcredit"));
            list.add(info);
        }
        c.close();
        return list;
    }
    public ExpenseInfo getexpenseinfo(int id){
        String sql= "Select * from expense where id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        ExpenseInfo info= new ExpenseInfo();
        while(c.moveToNext())
        {
            info.id = c.getInt(c.getColumnIndex("id"));
            info.date = c.getString(c.getColumnIndex("date"));
            info.amount = c.getInt(c.getColumnIndex("amount"));
            info.category = c.getString(c.getColumnIndex("category"));
            info.description = c.getString(c.getColumnIndex("description"));
            info.cashcredit = c.getString(c.getColumnIndex("cashcredit"));
        }
        c.close();
        return info;
    }

    public void insertincome (ContentValues contentValues)
    {
        getWritableDatabase().insert("income","",contentValues);
    }

    public void deleteincome (int id){

        getWritableDatabase().delete("income","id =" +id,null);

    }

    public void updateincome(Integer id,ContentValues contentValues){
        getWritableDatabase().update("income",contentValues,"id="+id,null);
    }

    public ArrayList<ExpenseInfo> getincomelist()
    {
        String sql= "Select * from income ";
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        ArrayList<ExpenseInfo> list=new ArrayList<>();
        while(c.moveToNext())
        {
            ExpenseInfo info= new ExpenseInfo();
            info.id = c.getInt(c.getColumnIndex("id"));
            info.date = c.getString(c.getColumnIndex("date"));
            info.amount = c.getInt(c.getColumnIndex("amount"));
            info.category = c.getString(c.getColumnIndex("category"));
            info.description = c.getString(c.getColumnIndex("description"));
            list.add(info);
        }
        c.close();
        return list;
    }

    public ExpenseInfo getincomeinfo(int id){
        String sql= "Select * from income where id="+id;
        Cursor c=getReadableDatabase().rawQuery(sql,null);
        ExpenseInfo info= new ExpenseInfo();
        while(c.moveToNext())
        {
            info.id = c.getInt(c.getColumnIndex("id"));
            info.date = c.getString(c.getColumnIndex("date"));
            info.amount = c.getInt(c.getColumnIndex("amount"));
            info.category = c.getString(c.getColumnIndex("category"));
            info.description = c.getString(c.getColumnIndex("description"));
        }
        c.close();
        return info;
    }
    /*public long getbalance(){
        String sql = "Select SUM (amount) as Total from income";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();
        return l;

    }*/

    public int getbalance(){
        int l=0;
        l = getcashtotal()+getcredittotal();
        return l;
    }

    public int getcashtotal(){
        String sql = "Select * from income where category = 'Cash'";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        int sum = 0,total = 0;
        while (c.moveToNext()){
            sum = c.getInt(c.getColumnIndex("amount"));
            total = total + sum;
        }
        c.close();
        String sql1 = "Select * from expense where cashcredit = 'Cash'";
        Cursor cursor = getReadableDatabase().rawQuery(sql1,null);
        int sum1=0,total1=0;
        while (cursor.moveToNext()){
            sum1 = cursor.getInt(cursor.getColumnIndex("amount"));
            total1 = total1 + sum1;
        }
        cursor.close();
        total = total-total1;
        return total;
    }

    public int getcredittotal(){
        String sql = "Select * from income where category = 'Credit Card'";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        int sum = 0,total = 0;
        while (c.moveToNext()){
            sum = c.getInt(c.getColumnIndex("amount"));
            total = total + sum;
        }
        c.close();
        String sql1 = "Select * from expense where cashcredit = 'Credit Card'";
        Cursor cursor = getReadableDatabase().rawQuery(sql1,null);
        int sum1=0,total1=0;
        while (cursor.moveToNext()){
            sum1 = cursor.getInt(cursor.getColumnIndex("amount"));
            total1 = total1 + sum1;
        }
        cursor.close();
        total = total-total1;
        return total;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


