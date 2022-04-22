package fabian.Arevalo.plataformaCompras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table  userbd(id_user integer primary key autoincrement not null, nombre text not null, correo text not null, clave text not null,saldo text not null,fecha text )");
        sqLiteDatabase.execSQL("create table histouser(id_his integer primary key autoincrement not null, nombre text, correo text, planes text, precioplan text, fecha text, cedula text,pin text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("create table userbd(id_user integer primary key autoincrement not null, nombre text not null, correo text not null, clave text not null, saldo text not null,fecha text)");
        sqLiteDatabase.execSQL("create table histouser(id_his integer primary key autoincrement not null, nombre text, correo text, planes text, precioplan text, fecha text,cedula text,pin text)");


    }
}
