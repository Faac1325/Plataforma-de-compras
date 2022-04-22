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

public class Netflix extends AppCompatActivity {
    //Declaramos para que sea global
    List<ListElement> elements;
    Bundle id;
    private Cursor fila;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix);

        id=getIntent().getExtras();
        init();
    }
    public void init(){
        elements= new ArrayList<>();
        elements.add(new ListElement("Plan Básico (1 Mes)", "10000", " 1 pantalla, Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 480"));
        elements.add(new ListElement("Plan Basico (1 Mes)", "20000", "2 pantalla,Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 480"));
        elements.add(new ListElement("Plan Estandar (3 Mes)", "20000", "1 pantalla,Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 480"));
        elements.add(new ListElement("Plan Estandar (1 Mes)", "40000", "4 pantalla,Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 1080"));
        elements.add(new ListElement("Plan Prémium (1 Año)", "100000", "1 pantalla,Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 4K+HDR"));
        elements.add(new ListElement("Netflix (6 Mes)", "50000", "4 pantalla,Mira tus series y películas en la mejor plataforma de Streaming, Con Resolución 4K+HDR"));

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
