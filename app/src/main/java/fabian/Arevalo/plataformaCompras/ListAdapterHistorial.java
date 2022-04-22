package fabian.Arevalo.plataformaCompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListAdapterHistorial extends RecyclerView.Adapter<ListAdapterHistorial.ViewHolder> {

    private List<ListHistorial> date;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapterHistorial(List<ListHistorial> itemlist,Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.date = itemlist;
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    @Override
    public ListAdapterHistorial.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_historial, null);
        return new ListAdapterHistorial.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterHistorial.ViewHolder holder, final int position) {
        holder.bindData(date.get(position));

    }

    public void setItem(List<ListHistorial> item) {
        date = item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texnombre, texcorreo,texplan,texprecio,texfecha,cedula,pin;

        ViewHolder(View itemView) {
            super(itemView);
            texnombre = itemView.findViewById(R.id.texnombre);
            texcorreo = itemView.findViewById(R.id.texcorreo);
            texplan = itemView.findViewById(R.id.texplan);
            texprecio = itemView.findViewById(R.id.texprecio);
            texfecha = itemView.findViewById(R.id.texfecha);
            cedula = itemView.findViewById(R.id.cedula);
            pin=itemView.findViewById(R.id.pin);
        }


        void bindData(final ListHistorial item) {
            texnombre.setText(item.getTexnombre());
            texcorreo.setText(item.getTexcorreo());
            texplan.setText(item.getTexplan());
            texprecio.setText(item.getTexprecio());
            texfecha.setText(item.getTexfecha());
            cedula.setText(item.getCedula());
            pin.setText(item.getPin());
        }
    }
}
