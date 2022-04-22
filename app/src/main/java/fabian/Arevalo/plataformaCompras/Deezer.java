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

public class Deezer extends AppCompatActivity {

    //Declaramos para que sea global
    List<ListElement> elements;

    Bundle id;
    private Cursor fila;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deezer);
        recyclerView=findViewById(R.id.recyclerView);
        id=getIntent().getExtras();

        init();

    }
    public void init(){
        elements = new ArrayList<>();
        elements.add(new ListElement("Deezer (1 Mes)","17000","1 cuenta, Escucha cualquier canción sin anuncios y además, descarga tus canciones favoritas y escúchalos sin conexión"));
        elements.add(new ListElement("DeezerDuo(1 Mes)","30000", "2 cuentas, Escucha cualquier canción sin anuncios y además, descarga tus canciones favoritas y escúchalos sin conexión"));
        elements.add(new ListElement("Deezer (1 Mes)","5000","1 cuenta, Descarga tus Canciones favoritas en alta definición en sonido"));
        elements.add(new ListElement("Familiar(1 Mes)","40000","6 cuentas individuales, todas con los beneficios de Deezer Prémium, incluyendo sonido de alta definición"));
        elements.add(new ListElement("Deezer (Anual) ","80000", "1 cuenta, Escucha cualquier canción sin anuncios y además, descarga tus canciones favoritas y escúchalos sin conexión"));
        elements.add(new ListElement("DeezerDuo(Anual)","150000","3 cuentas, Escucha cualquier canción sin anuncios y además, descarga tus canciones favoritas y escúchalos sin conexión"));

        ListAdapter listAdapter=new ListAdapter(elements, this);
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