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

public class Xbox extends AppCompatActivity {
    //Declaramos para que sea global
    List<ListElement> elements;
    Bundle id;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xbox);
        id=getIntent().getExtras();
        init();
    }
    public void init(){
        elements= new ArrayList<>();
        elements.add(new ListElement("Game pass (1 Mes)", "27900", " Juega a más de 100 juegos de alta calidad con amigos en consola, PC"));
        elements.add(new ListElement("Game pass (2 Mes)", "40000", "Juega a más de 100 juegos de alta calidad con amigos en consola, PC"));
        elements.add(new ListElement("Game pass (3 Mes)", "60000", "Juega a más de 100 juegos de alta calidad con amigos en consola, PC"));
        elements.add(new ListElement("Ultimate (1 Mes)", "50000", "Incluye Xbox Live Gold y acceso ilimitado a más de 100 juegos de alta calidad para consola y PC"));
        elements.add(new ListElement("Game pass (6 Mes)", "90000", "Incluye Xbox Live y 100 juegos"));
        elements.add(new ListElement("Ultimate (1 año)", "50000", "Incluye xbox live gold y acceso ilimitado a mas de 100 juegos de alta calidad para consola y pc"));

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
