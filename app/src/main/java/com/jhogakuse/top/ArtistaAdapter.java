package com.jhogakuse.top;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ViewHolder> {

    private List<Artista> artistas;
    private Context context;
    private OnItemClickListener listener;

    public ArtistaAdapter(List<Artista> artistas, OnItemClickListener listener) {
        this.artistas = artistas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artist, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Artista artista = artistas.get(i);
        viewHolder.setListener(artista, listener);
        viewHolder.txtViewNombre.setText(artista.getNombreCompleto());
        viewHolder.txtViewOrden.setText(String.valueOf(artista.getOrden()));
    }

    @Override
    public int getItemCount() {
        return this.artistas.size();
    }

    public void add(Artista artista){
        if (!artistas.contains(artista)) {
            artistas.add(artista);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPhoto)
        AppCompatImageView imgPhoto;
        @BindView(R.id.txtViewNombre)
        AppCompatTextView txtViewNombre;
        @BindView(R.id.txtViewOrden)
        AppCompatTextView txtViewOrden;
        /*@BindView(R.id.containerMain);*/
        RelativeLayout containerMain;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void setListener(final Artista artista, final OnItemClickListener listener){
            containerMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(artista);
                }
            });
            containerMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnLongItemClick(artista);
                    return true;
                }
            });
        };
    }
}
