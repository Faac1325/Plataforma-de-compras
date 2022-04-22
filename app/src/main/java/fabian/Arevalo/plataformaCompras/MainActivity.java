package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tvinscribete;
    EditText etemail,etpass;
    Button btnlogin;
    //el curso recorre la tabla
    private Cursor fila;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etemail = findViewById(R.id.etmail);
        etpass = findViewById(R.id.etpass);
        btnlogin = findViewById(R.id.btnlogin);
        tvinscribete = findViewById(R.id.tvinscribete);

        //Este nos envia de MainActivity a Registro
        tvinscribete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
                onBackPressed();
            }
        });

    }

    public void login(View view) {
        //Creamos un objeto de la clase y le pasamos el constructor this haciendo referecia a la activity actual
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        //creamos un objeto clase SQLiteDatabase y llamo al metodo getWritableDatabase() lo que hace el metodo
        //es abrir la base de datos en modo lectura y escritura
        SQLiteDatabase db = admin.getWritableDatabase();
        //obtenemos los datos de editText
        String email = etemail.getText().toString();
        String pass2 = etpass.getText().toString();

        Date date = new Date();
        SimpleDateFormat fechaLogin = new SimpleDateFormat("d'/'M'/'yyyy");
        String fecha2 = fechaLogin.format(date);
        
        if (validarcampos(email,pass2)) {
            //el curso recorre la tabla
            fila = db.rawQuery("select correo, clave,nombre , saldo, id_user, fecha from userbd where correo='" + email + "' and clave='" + pass2 + "'", null);
            try {
                //busca por registro
                if (fila.moveToFirst()) {
                    String correo = fila.getString(0);
                    String clave = fila.getString(1);
                    String nombre = fila.getString(2);
                    String saldo = fila.getString(3);
                    String id = fila.getString(4);
                    String fecha= fila.getString(5);


                    if (email.equals(correo) && pass2.equals(clave)) {
                        Intent i = new Intent(getApplicationContext(), Incio.class);

                        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("nombre_usuario",nombre);
                        //guarda
                        editor.commit();

                        if(!fecha.equals(fecha2)){
                            db.execSQL("update userbd set saldo  = '" + "1000000" + "' where id_user = '" + id + "'");
                        }
                        //manda los datos al activity inicio
                        i.putExtra("nombre", nombre);
                        i.putExtra("dinero", saldo);
                        i.putExtra("id", id);
                        startActivity(i);
                        etemail.setText("");
                        etpass.setText("");
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }


    }
    private boolean validarcampos(String campo1, String campo2) {

        boolean camposllenos = true;
        if (campo1.trim().isEmpty()) {
            camposllenos = false;
            Toast.makeText(getApplicationContext(), "Campo email vacio", Toast.LENGTH_LONG).show();

        }
        if (campo2.trim().isEmpty()) {
            camposllenos = false;
            Toast.makeText(getApplicationContext(), "Campo de contraseña vacio", Toast.LENGTH_LONG).show();
        }
        return camposllenos;
    }
    //Bloquear atras
    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }



}