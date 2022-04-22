package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Spotify extends AppCompatActivity {
    //Declaramos para que sea global
    List<ListElement> elements;
    Bundle id;
    private Cursor fila;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);


        id = getIntent().getExtras();
        init();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idus = id.getString("id");
        fila = db.rawQuery("select id_user from userbd where id_user='" + idus + "'", null);
    }
    public void init(){
        elements = new ArrayList<>();
        elements.add(new ListElement("Plan Individual","10000","1 cuenta, Escucha música sin anuncios, Reproduce tus canciones en cualquier lugar, incluso sin conexión"));
        elements.add(new ListElement("Plan Duo","20000", "2 cuentas, Escucha música sin anuncios, Reproduce tus canciones en cualquier lugar, incluso sin conexión"));
        elements.add(new ListElement("Plan Estudiantil","5000","1 cuenta,Escucha música sin anuncios"));
        elements.add(new ListElement("Plan Familiar","270000","6 cuentas, Reproducción de música sin anuncios, sin conexión y on-demand, Bloqueo de música explicita"));
        elements.add(new ListElement("Plan Individual Anual ","40000", "1 cuenta, Escucha música sin anuncios, Reproduce tus canciones en cualquier lugar, incluso sin conexión"));
        elements.add(new ListElement("Plan Duo Anual","220000","6 cuentas, Reproducción de música sin anuncios, sin conexión y on-demand, Bloqueo de música explicitas"));

        ListAdapter listAdapter = new ListAdapter(elements,this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fila.moveToFirst()) {
                    String idnet = fila.getString(0);
                    String plan = elements.get(recyclerView.getChildAdapterPosition(view)).getPlanes();
                    String precio = elements.get(recyclerView.getChildAdapterPosition(view)).getPrecios();
                    String detalle = elements.get(recyclerView.getChildAdapterPosition(view)).getDetalles();
                    Intent i = new Intent(getApplicationContext(), Comprar.class);
                    i.putExtra("planes", plan);
                    i.putExtra("precios", precio);
                    i.putExtra("detalle", detalle);
                    i.putExtra("id",idnet);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "seleccion " + elements.get(recyclerView.getChildAdapterPosition(view)).getPlanes(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        recyclerView.setAdapter(listAdapter);
    }
    public void atras(View v) {
        onBackPressed();
    }
}