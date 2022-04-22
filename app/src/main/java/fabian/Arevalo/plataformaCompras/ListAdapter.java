package fabian.Arevalo.plataformaCompras;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  implements View.OnClickListener {

    private List<ListElement> date;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public ListAdapter(List<ListElement> itemlist, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.date = itemlist;
    }
    //metodo nos da el tamaño de la lista
    @Override
    public int getItemCount() {
        return date.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_list_element, null);
        view.setOnClickListener(this);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(date.get(position));
    }
    //crear una lista nueva
    public void setItem(List<ListElement> item) {
        date = item;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivshopping;
        TextView planes, precios;

        ViewHolder(View itemView) {
            super(itemView);
            ivshopping=itemView.findViewById(R.id.ivshopping);
            planes = itemView.findViewById(R.id.planes);
            precios = itemView.findViewById(R.id.precios);

        }


        void bindData(final ListElement item) {

            planes.setText(item.getPlanes());
            precios.setText(item.getPrecios());

        }
    }


}