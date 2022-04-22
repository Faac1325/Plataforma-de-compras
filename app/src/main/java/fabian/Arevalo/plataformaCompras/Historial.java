package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historial extends AppCompatActivity {
    List<ListHistorial> elehistorial;
    Bundle id;
    private Cursor fila;
    private String saldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        id = getIntent().getExtras();
        init();

    }

    public void init() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idus = id.getString("id");
        fila = db.rawQuery("select correo from userbd where id_user='" + idus + "'", null);
        if (fila.moveToFirst()) {
            String correo1 = fila.getString(0);



            fila = db.rawQuery("select nombre, correo, planes, precioplan, fecha, cedula,pin from  histouser where correo ='" + correo1 + "'", null);

            elehistorial = new ArrayList<>();

            if (fila.moveToFirst()) {
                while (!fila.isAfterLast()) {
                    elehistorial.add(new ListHistorial(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5), fila.getString(6)));
                    fila.moveToNext();
                }
            }
            ListAdapterHistorial listadapHistorial = new ListAdapterHistorial(elehistorial, this);
            RecyclerView recyclerView = findViewById(R.id.recyclerView2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listadapHistorial);
            Collections.reverse(elehistorial);
        }

    }

    
    public void atras(View view) {
            onBackPressed();        
            }
}
