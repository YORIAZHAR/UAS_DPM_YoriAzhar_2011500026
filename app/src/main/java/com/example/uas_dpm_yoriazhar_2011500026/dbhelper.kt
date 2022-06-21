package com.example.uas_dpm_yoriazhar_2011500026

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbhelper (context: Context): SQLiteOpenHelper(context, "kampus", null, 1){
    var nidn = ""
    var nmDosen = ""
    var jabatan = ""
    var golonganpangkat =""
    var pendidikan =""
    var keahlian = ""
    var programstudi = ""

    private val tabel = "lecturer"
    private var sql = ""

    override fun onCreate(db: SQLiteDatabase?) {
        sql = """create table $tabel(
            nidn char(10) primary key,
            Nm_Dosen varchar(50) not null,
            Jabatan varchar (15) not null,
            GolonganPangkat varchar (30) not null,
            Pendidikan char (2) not null,
            Keahlian varchar (30) not null,
            ProgramStudi varchar(50) not null
            )
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        sql = "drop table if exists $tabel"
        db?.execSQL(sql)
    }

    fun simpan(): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        with(cv) {
            put("Nidn", nidn)
            put("Nm_Dosen", nmDosen)
            put("Jabatan", jabatan)
            put("GolonganPangkat", golonganpangkat)
            put("Pendidikan", pendidikan)
            put("Keahlian", keahlian)
            put("ProgramStudi", programstudi)
        }
        val cmd = db.insert(tabel, null, cv)
        db.close()
        return cmd != -1L
    }
    fun ubah(kode:String): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        with(cv) {
            put("Nm_Dosen", nmDosen)
            put("Jabatan", jabatan)
            put("GolonganPangkat", golonganpangkat)
            put("Pendidikan", pendidikan)
            put("Keahlian", keahlian)
            put("ProgramStudi", programstudi)
        }
        val cmd = db.update(tabel, cv, "nidn = ?", arrayOf(kode))
        db.close()
        return cmd != -1
    }
    fun hapus(kode:String): Boolean {
        val db = writableDatabase
        val cmd = db.delete(tabel, "nidn = ?", arrayOf(kode))
        return cmd != -1
    }

    fun tampil(): Cursor {
        val db = writableDatabase
        val reader = db.rawQuery("select * from $tabel", null)
        return reader
    }
}