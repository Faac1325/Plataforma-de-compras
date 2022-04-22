package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Incio extends AppCompatActivity {

    Bundle bundle,id;
    Button historialCompras;
    TextView nUsuario, saldoUsu,salir;
    ImageButton btnamazon,btnetflix,btndeezer,btnvimeo,btnspotify,btntiktok,btnxbox,btnyoutube;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incio);


        nUsuario=findViewById(R.id.usuario);
        saldoUsu= findViewById(R.id.saldo);
        historialCompras=findViewById(R.id.historial);
        btnamazon=findViewById(R.id.btnamazon);
        btnetflix=findViewById(R.id.btnetflix);
        btndeezer=findViewById(R.id.btndeezer);
        btnvimeo=findViewById(R.id.btnvimeo);
        btnspotify=findViewById(R.id.btnspotify);
        btntiktok=findViewById(R.id.btntiktok);
        btnxbox=findViewById(R.id.btnxbox);
        btnyoutube=findViewById(R.id.btnyoutube);
        salir=findViewById(R.id.tvsalir);
        cargarPreferencias();

        //funcion lo que hace es llamar el nombre de usuario,saldo por medio de bundle y lo muestra en el inicio
        bundle=getIntent().getExtras();
        id = getIntent().getExtras();



        mostrarSaldo();
        //llamo al metodo salir
        tvsalir();



        }

        public void cargarPreferencias(){
            SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String user= preferences.getString("nombre_usuario","No existe información");
            nUsuario.setText(user);
        }

    private void mostrarSaldo() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idus = id.getString("id");
        fila = db.rawQuery("select nombre, saldo from userbd where id_user='" + idus + "'", null);
        if (fila.moveToFirst()) {
            String saldo = fila.getString(1);
            saldoUsu.setText(saldo);
        }
    }


    public void btnamazon(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Amazon.class);
            i.putExtra("id", idi);
            startActivity(i);
        }


    }

    public void btnetflix(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Netflix.class);
            i.putExtra("id", idi);
            startActivity(i);
        }
    }

    public void btndeezer(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Deezer.class);
            i.putExtra("id", idi);
            startActivity(i);
        }

    }

    public void btnspotify(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Spotify.class);
            i.putExtra("id", idi);
            startActivity(i);
        }
    }

    public void btnvimeo(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Vimeo.class);
            i.putExtra("id", idi);
            startActivity(i);
        }
    }

    public void btnyoutube(View view) {
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getApplicationContext(),"registros", null,1);
         SQLiteDatabase db= admin.getWritableDatabase();
         String idusuario= id.getString("id");
         fila= db.rawQuery("select id_user from userbd where id_user='"+idusuario+ "'",null);
         if(fila.moveToFirst()){
             String idi=fila.getString(0);
             Intent i=new Intent(getApplicationContext(),Youtube.class);
             i.putExtra("id",idi);
             startActivity(i);
         }
    }

    public void btntiktok(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Tiktok.class);
            i.putExtra("id", idi);
            startActivity(i);
        }
    }

    public void btnxbox(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String idusuario = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idusuario + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Xbox.class);
            i.putExtra("id", idi);
            startActivity(i);
        }
    }


    public void historial(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idus = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idus + "'", null);
        if (fila.moveToFirst()) {
            String idi = fila.getString(0);
            Intent i = new Intent(getApplicationContext(), Historial.class);
            i.putExtra("id", idi);
            startActivity(i);

        }
    }
    //Metodo salir
    public void tvsalir(){
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Incio.this);
                alerta.setMessage("Desea salir")
                        .setCancelable(false)

                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //onBackPressed();
                                Intent i1=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i1);

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle("Desea Salir");
                titulo.show();


            }
        });
    }

    //Bloquear atras
    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }




}
