package com.example.myapplication3.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.Perfil;
import com.example.myapplication3.R;
import com.example.myapplication3.pojo.UserComentarios;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.ViewHolderComentarios> {

    List<UserComentarios> lista_coments;

    public AdapterComentario(List<UserComentarios> lista_coments) {
        this.lista_coments = lista_coments;
    }

    @NonNull
    @Override
    public ViewHolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comentarios, parent, false);
        AdapterComentario.ViewHolderComentarios holder = new AdapterComentario.ViewHolderComentarios(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentario.ViewHolderComentarios holder, int position) {
        UserComentarios cm = lista_coments.get(position);

        Picasso.get()
                .load(cm.getImagen())
                .transform(new Perfil.CircleTransform())
                .error(R.mipmap.foto_perfil)
                .into(holder.image_perfil);

        holder.nombre.setText(cm.getNombre());
        holder.comentario.setText(cm.getComentario());


    }

    @Override
    public int getItemCount() {
        return lista_coments.size();
    }

    public class ViewHolderComentarios extends RecyclerView.ViewHolder {

        private ImageView image_perfil;
        private TextView nombre, comentario;

        public ViewHolderComentarios(@NonNull View itemView) {
            super(itemView);
            image_perfil = itemView.findViewById(R.id.iv_perfil_comentarios);
            nombre = itemView.findViewById(R.id.tv_nombre_comentarios);
            comentario = itemView.findViewById(R.id.tv_comentario_comentarios);


        }
    }
}
