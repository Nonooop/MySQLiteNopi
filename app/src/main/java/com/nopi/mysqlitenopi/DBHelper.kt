package com.nopi.mysqlitenopi

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    companion object{
        private const val DATABASE_NAME = "dbData.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tbUser"
        public const val ID_COL = "id"
        public const val NAME_COL = "name"
        public const val PASS_COL = "pass"
        public const val AGE_COL = "age"
        public var getPass = "getpass"
        public var getAge = "getage"
        public var IDRow: Long =0
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " +TABLE_NAME+ "(" + ID_COL +
                " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT," +
                PASS_COL + " TEXT," +
                AGE_COL + " TEXT" + ")")
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addData(name: String?, pass:String?, age: String?) : Boolean{
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(PASS_COL, pass)
        values.put(AGE_COL, age)
        val db = this.writableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME+
                " WHERE NAME = '$name'", null)
        if(cursor.moveToFirst()){
            return false
        }
        else{
            db.insert(TABLE_NAME,null, values)
            return true
        }
    }


    @SuppressLint("Range")
    fun getAllData(): Collection<String>{
        val db: SQLiteDatabase = this.readableDatabase
        val arrayList = ArrayList<String>()
        val res: Cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        if (res.moveToFirst()){
            do {
                arrayList.add(res.getString(res.getColumnIndex("name")))
                getPass = res.getString(res.getColumnIndex("pass"))
                getAge = res.getString(res.getColumnIndex("age"))
            }while(res.moveToNext())

        }
        return arrayList
    }
    fun deleteData(Name: String){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE name = '$Name'")
        db.close()
    }
    @SuppressLint("Range")
    fun SearchData(Name:String): Collection<String>{
        val db: SQLiteDatabase = this.readableDatabase
        val arrayList = ArrayList<String>()
        val res: Cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE " + NAME_COL + "= '$Name'" , null)
        if (res.moveToFirst()){
            do {
                getAge = res.getString(res.getColumnIndex("age"))
            }while(res.moveToNext())

        }
        return arrayList
    }

    fun UpdateDatas(name: String?, pass: String?, age: String?): Boolean {
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(PASS_COL, pass)
        values.put(AGE_COL, age)
        val db = this.writableDatabase
        var cursor: Cursor = db.rawQuery("Update " + TABLE_NAME + " Set pass = '$pass', age = '$age' where name = '$name'", null)
            if (cursor.moveToFirst()) {
                return false
            } else {
                IDRow = db.update(TABLE_NAME, values, "name= '$name'",
                    null).toLong()
                return true
            }
    }

    @SuppressLint("Range")
    fun SearchDataByName(Name:String): Collection<String>{
        val db: SQLiteDatabase = this.readableDatabase
        val arrayList = ArrayList<String>()
        val res: Cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE " + NAME_COL + " like '%$Name%'" , null)
        if (res.moveToFirst()){
            do {
                arrayList.add(res.getString(res.getColumnIndex("name")))

            }while(res.moveToNext())

        }
        return arrayList
    }

    fun LoginDatas(name: String?, pass: String?): Boolean {
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(PASS_COL, pass)
        val db = this.writableDatabase
        var cursor: Cursor = db.rawQuery(
            "Select * from " + TABLE_NAME + " where name = '$name' and pass ='$pass'",
            null
        )
        if (cursor.count>0) {
            return true;
        } else {
            return false;
        }
    }
    }