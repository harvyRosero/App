package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<List_element> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ImageButton btn_agregar, btn_image;
    private TextView txt_titulo_d;


    public ListAdapter(List<List_element> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));

        final List_element item = mData.get(position);

        txt_titulo_d = holder.itemView.findViewById(R.id.name_textview);

        btn_agregar = holder.itemView.findViewById(R.id.btn_home_agregar);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Se agrego a fovoritos", Toast.LENGTH_SHORT).show();

            }
        });

        btn_image = holder.itemView.findViewById(R.id.btn_image_lugar);
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Detalles.class);
                i.putExtra("itemDetalle", holder.name.getText().toString());
                holder.itemView.getContext().startActivity(i);

            }
        });


    }

    public void setItem(List<List_element> items){
        mData = items;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, city, status;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_textview);
            city = itemView.findViewById(R.id.city_textview);
            status = itemView.findViewById(R.id.status_textview);

        }

        public void bindData(final List_element item) {
            name.setText(item.getName());
            city.setText(item.getCity());
            status.setText(item.getStatus());
        }
    }
}
