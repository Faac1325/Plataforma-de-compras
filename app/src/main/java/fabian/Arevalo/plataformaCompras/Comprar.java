package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Comprar extends AppCompatActivity {
    TextView plan, precio, detallesdelplan, pin,fecha;
    EditText cedula;
    Button comprar,atras;
    Bundle pre, pla, deta, id;
    private Cursor fila;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        cedula = findViewById(R.id.edcedula);
        comprar = findViewById(R.id.comprar);
        precio = findViewById(R.id.precio);
        detallesdelplan = findViewById(R.id.detallesdelplan);
        pin=findViewById(R.id.pin);
        fecha=findViewById(R.id.fechacompra);
        plan = findViewById(R.id.plan);
        atras=findViewById(R.id.flecha);

        //creamos bundle para recibir informacion
        pre = getIntent().getExtras();
        pla = getIntent().getExtras();
        deta = getIntent().getExtras();
        id = getIntent().getExtras();

        //recibimos informacion
        String obtnerprecio = pre.getString("precios");
        String obtenerplanes = pla.getString("planes");
        String obtenerdetalle = deta.getString("detalle");

        //pintamos la informacion
        plan.setText(obtenerplanes);
        precio.setText(obtnerprecio);
        detallesdelplan.setText(obtenerdetalle);


        Date datefecha = Calendar.getInstance().getTime();
        String formatdate = DateFormat.getInstance().format(datefecha);

        pin.setText(" "+generarPin());
        fecha.setText("FECHA: "+formatdate);
       atras.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Comprar.this);
                alerta.setMessage("Se le descontará " + obtnerprecio + " a su saldo actual")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(cedula.length()==7 || cedula.length()==8 || cedula.length()==10){

                                    enviar();
                                    guardarhistorial();
                                } else{
                                    Toast.makeText(getApplicationContext(), "longitud de cedula invalida ", Toast.LENGTH_SHORT).show();
                                }



                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Desea comprar el " + obtenerplanes + "?");
                titulo.show();
            }
        });
    }


    public String generarPin(){
        String pin="";
        String minLetras="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        String randomChar = "";

        for(int i=0;i<6;i++) {
            int randomInt = random.nextInt(minLetras.length());
            randomChar = randomChar+ minLetras.charAt(randomInt);
        }
        String minNumeros="1234567890";
        String randomChar2 = "";

        for(int i=0;i<6;i++) {
            int randomInt = random.nextInt(minNumeros.length());
            randomChar2 = randomChar2+ minNumeros.charAt(randomInt);
        }

        String numLetras=randomChar+randomChar2 ,resultado="";
        int zz,azar;
        for (zz=numLetras.length();zz>=2;zz--){
            azar = (int)(Math.random()* zz+1) ;
            resultado = resultado + numLetras.substring(azar-1,azar);
            numLetras =  numLetras.substring(0,azar-1)+numLetras.substring(azar,zz);
        }
        pin=resultado+numLetras;


        return pin;

    }



    public boolean enviar() {

        String obid = id.getString("id");
        String obpre = pre.getString("precios");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        fila = db.rawQuery("select id_user,saldo from userbd where id_user='" + obid + "'", null);

        if (fila.moveToFirst()) {
            String idcomp = fila.getString(0);
            String saldo = fila.getString(1);

            int saldoi = Integer.parseInt(saldo);
            int precioplan = Integer.parseInt(obpre);

            if (cedula.getText().toString().isEmpty()){
                Toast.makeText(this, "Debe ingresar una cedula", Toast.LENGTH_SHORT).show();
            }else{



                    if (saldoi >= precioplan) {
                        int saldot = saldoi - precioplan;
                        String stotal = String.valueOf(saldot);

                        db.execSQL("update userbd set saldo  = '" + stotal + "' where id_user = '" + idcomp + "'");
                        Intent i = new Intent(getApplicationContext(), Incio.class);
                        i.putExtra("id", idcomp);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                    }



            }
        }
        return true;
    }
    public boolean guardarhistorial() {

        String obpre = pre.getString("precios");
        String obpla = pla.getString("planes");
        String obid = id.getString("id");
        //obtener
        String obpin=String.valueOf(pin.getText());

        String campoCedula = cedula.getText().toString();

        Date datefecha = Calendar.getInstance().getTime();
        String formatdate = DateFormat.getInstance().format(datefecha);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        fila = db.rawQuery("select correo, nombre from userbd where id_user='" + obid + "'", null);
        if (fila.moveToFirst()) {
            String correo = fila.getString(0);
            String nombre = fila.getString(1);

            if (!campoCedula.isEmpty()) {

                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("correo", correo);
                values.put("planes", obpla);
                values.put("precioplan", obpre);
                values.put("fecha", formatdate);
                values.put("cedula", campoCedula);
                values.put("pin", obpin);

                db.insert("histouser", null, values);
                db.close();
            }
        }
        return true;
    }

        //Bloquear atras
        @Override
    public void onBackPressed() {
        finish();
    }


}