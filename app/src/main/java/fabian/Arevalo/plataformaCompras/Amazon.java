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

public class Amazon extends AppCompatActivity{
    //Declaramos para que sea global
    List<ListElement> elements;
    Bundle id;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amazon);

        id=getIntent().getExtras();
        init();

    }

    public void init(){
        elements= new ArrayList<>();
        elements.add(new ListElement("PrimeVideo(1 Mes)", "20000", "Prime Video, Prime Video, Streaming Música sin anuncios"));
        elements.add(new ListElement("Plan  Duo(1 Mes)", "30000", "Prime Video, Streaming Música sin anuncios"));
        elements.add(new ListElement("Plan  Video(2 Mes)", "60000", "Prime Video, Streaming, Música sin anuncios, almacenamiento fotos"));
        elements.add(new ListElement("Prime pro(1 Mes)", "25000", "Prime Video, Lectura ilimitada"));
        elements.add(new ListElement("Prime (2 Mes)", "20000", "1 pantalla, películas y programas ilimitados, Envios gratis"));
        elements.add(new ListElement("Prime Plus(1 Año)", "455000", "Prime Video, Streaming, Música sin anuncios, almacenamiento fotos, Lectura ilimitada"));

        ListAdapter listAdapter=new ListAdapter(elements, this);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                String idus = id.getString("id");
                fila = db.rawQuery("select id_user from userbd where id_user='" + idus + "'", null);
                if (fila.moveToFirst()) {
                    String idnet = fila.getString(0);
                    String plan = elements.get(recyclerView.getChildAdapterPosition(view)).getPlanes();
                    String precio = elements.get(recyclerView.getChildAdapterPosition(view)).getPrecios();
                    String detalle = elements.get(recyclerView.getChildAdapterPosition(view)).getDetalles();
                    Intent i = new Intent(getApplicationContext(), Comprar.class);
                    i.putExtra("planes", plan);
                    i.putExtra("precios", precio);
                    i.putExtra("detalle", detalle);
                    i.putExtra("id", idnet);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "seleccion " + elements.get(recyclerView.getChildAdapterPosition(view)).getPlanes(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        recyclerView.setAdapter(listAdapter);
    }

    public void atras(View view) {
        onBackPressed();

    }
}