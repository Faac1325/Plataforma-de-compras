package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro extends AppCompatActivity {
    EditText etnombre, etreemail, etpassword, etconfirpass;
    Button btnregistrar,btnatras;
    private Boolean numberName=false;
    private Boolean lengthName=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = findViewById(R.id.etnombre);
        etreemail = findViewById(R.id.etregmail);
        etpassword = findViewById(R.id.etpassword);
        etconfirpass = findViewById(R.id.etconfipass);
        btnregistrar = findViewById(R.id.btnregistrar);
        btnatras=findViewById(R.id.btnatras);

    }

    public void registrar(View view) {

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getApplicationContext(),"registros", null, 1);
        SQLiteDatabase db= admin.getWritableDatabase();


        String nombre = etnombre.getText().toString();
        String email = etreemail.getText().toString();
        String pass = etpassword.getText().toString();
        String confipass = etconfirpass.getText().toString();

        if(nombre.trim().isEmpty()){
            Toast.makeText(this, "El campo nombre está vacio", Toast.LENGTH_SHORT).show();
        }else{
            if(!nombre.trim().isEmpty()){

                if(nombre.length()>20){
                    lengthName=true;
                }else{
                    //F4BIAN length 6
                    for (int i = 0; i< nombre.length(); i++){
                        char n = nombre.charAt(i);
                        if (!((n >= 'a' && n <= 'z') || (n >= 'A' && n <= 'Z'))){
                            numberName=true;
                            break;
                        }

                    }
                }


            }

            if(lengthName){
                Toast.makeText(this, "El campo nombre excede caracteres", Toast.LENGTH_SHORT).show();
                lengthName=false;
            }else{
                if(numberName){
                    numberName=false;
                    Toast.makeText(this, "El nombre solo debe contener letras", Toast.LENGTH_SHORT).show();
                }else{
                    if (isEmailValid(etreemail.getText().toString()) == false) {
                        Toast.makeText(this, "El campo email invalido", Toast.LENGTH_SHORT).show();
                    } else{
                        if(!validarcorreo()){
                            Toast.makeText(this, "Ingrese otro email", Toast.LENGTH_SHORT).show();
                        }else{
                            //El método trim( ) elimina los espacios en blanco en ambos extremos del string.
                            if (email.trim().isEmpty()) {
                                Toast.makeText(this, "El campo email está vacio", Toast.LENGTH_SHORT).show();

                            }else{
                                if (pass.trim().isEmpty()) {
                                    Toast.makeText(this, "El campo contraseña está vacio", Toast.LENGTH_SHORT).show();

                                }else{
                                    if (confipass.trim().isEmpty()) {
                                        Toast.makeText(this, "El campo contraseña está vacio", Toast.LENGTH_SHORT).show();

                                    }else{
                                        if (!pass.equals(confipass)) {

                                            Toast.makeText(this, "las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                                        }else{
                                            if (pass.length()<6 && confipass.length()<6){
                                                Toast.makeText(this, "las contraseñas deben tener mas de 6 caracteres", Toast.LENGTH_SHORT).show();
                                            }else{

                                                Date date = new Date();
                                                SimpleDateFormat fechaRegistro = new SimpleDateFormat("d'/'M'/'yyyy");
                                                String fechaActual = fechaRegistro.format(date);
                                                //creamos un objeto de la clase contentValues con el nombre registro y mediante el put inicializamos todos los campo a cargar
                                                ContentValues registro = new ContentValues();
                                                registro.put("nombre", nombre);
                                                registro.put("correo", email);
                                                registro.put("clave", pass);
                                                registro.put("saldo", "1000000");
                                                registro.put("fecha",fechaActual);
                                                //Llamos al metodo insert de la clase SQLITEDATABASE y le pasamos en nombre de la tabla "userbd"
                                                //segundo parametro le pasamos null y por ultimo el objeto de la clase ContentValue que es "registro"
                                                //este metodo permite insertar una nueva fila en la tabla articulos en base de dato llamada administracion.
                                                db.insert("userbd", null, registro);
                                                db.close();
                                                //Borramos los EditText
                                                etnombre.setText("");
                                                etreemail.setText("");
                                                etpassword.setText("");
                                                etpassword.setText("");
                                                etconfirpass.setText("");

                                                Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();
                                                Intent in=new Intent(Registro.this,MainActivity.class);
                                                startActivity(in);

                                            }

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }


        }



    }


    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean validarcorreo() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String email = etreemail.getText().toString();

        Cursor fila = db.rawQuery("select correo from userbd where correo='" + email + "'", null);
        try {
            if (fila.moveToFirst()) {
                String correo = fila.getString(0);
                if (correo.equals(email)) {
                    Toast.makeText(this, "El correo ya esta registrado", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }
    public void atras(View view) {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

        /*vuelve a la Actividad o Fragmento anterior al que te encuentras en el momento
        onBackPressed();*/

    }
    //Bloquear atras
    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }


}