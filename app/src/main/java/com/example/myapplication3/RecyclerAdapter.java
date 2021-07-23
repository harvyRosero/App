package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumerosViewHolder> {

    private int numerosItems;

    final private list_item_click mOnClickListener;

    public RecyclerAdapter(int numeroDeItems, list_item_click listener) {
        numerosItems = numeroDeItems;
        mOnClickListener = listener;
    }

    public interface list_item_click{
        void onListItemClick(int clickedItem);
    }

    @Override
    public NumerosViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        int LayoutIdParaListItem = R.layout.lista_numeros_row;
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        boolean attachToParentRapido = false;

        View view = inflater.inflate(LayoutIdParaListItem, parent, attachToParentRapido);

        NumerosViewHolder viewHolder = new NumerosViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.NumerosViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numerosItems;
    }

    class NumerosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TVListaNumerosView;


        public NumerosViewHolder( View itemView) {
            super(itemView);

            TVListaNumerosView = itemView.findViewById(R.id.lista_numeros);
            itemView.setOnClickListener(this);

        }

        void bind(int listaIndex){
            TVListaNumerosView.setText(String.valueOf(listaIndex));

        }

        @Override
        public void onClick(View v) {

            int clickedItem = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedItem);
        }
    }


}
