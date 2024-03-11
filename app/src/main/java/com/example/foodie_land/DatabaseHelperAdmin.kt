package com.example.foodie_land.admin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperAdmin (private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "AdminDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "admin_detail"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ADMIN = "admin"
        private const val COLUMN_PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = (
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_ADMIN TEXT NOT NULL CHECK(length($COLUMN_ADMIN) > 0), " +
                        "$COLUMN_PASS TEXT NOT NULL CHECK(length($COLUMN_PASS) > 0))"
                )
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertAdmin(admin: String, pass: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_ADMIN, admin)
            put(COLUMN_PASS, pass)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME,null, values)
    }

    fun readAdmin(admin: String, pass: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_ADMIN = ? AND $COLUMN_PASS = ?"
        val selectionArgs = arrayOf(admin, pass)
        val cursor = db.query(TABLE_NAME,null, selection, selectionArgs, null,null,null)

        val adminExists = cursor.count  >  0
        cursor.close()
        return adminExists
    }
}