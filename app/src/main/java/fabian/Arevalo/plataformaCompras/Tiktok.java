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

public class Tiktok extends AppCompatActivity {
    //Declaramos para que sea global
    List<ListElement> elements;
    Bundle id;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiktok);
        id=getIntent().getExtras();
        init();
    }
    public void init(){
        elements= new ArrayList<>();
        elements.add(new ListElement("TikTOK (1 mes)", "10000", " 1 Cuenta, Video en HD con pago mensual de 2500"));
        elements.add(new ListElement("TikTOK (2 mes)", "20000", "1 Cuenta, Video en HD con pago mensual de 5000"));
        elements.add(new ListElement("TikTOK (3 Mes)", "20000", "1 Cuenta, Video en HD con pago mensual de 10000"));
        elements.add(new ListElement("TikTOK (1 Mes)", "50000", "1 Cuenta, Video en HD con pago mensual de 100000"));
        elements.add(new ListElement("TikTOK (1 Año)", "100000", "1 Cuenta, Video en HD con pago mensual de 30000"));
        elements.add(new ListElement("TikTOK (6 Mes)", "50000", "1 Cuenta, Video en HD con pago mensual de 10000"));

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
