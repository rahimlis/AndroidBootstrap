package com.bakudynamics.android.bootstrap.adapters;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bakudynamics.android.bootstrap.views.binders.ViewBinder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public abstract class BaseAdapter<M, Holder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<Holder> implements ViewBinder {

    protected ArrayList<M> models;
    protected int resourceId;
    protected Context context;

    public BaseAdapter(ArrayList<M> models, int resourceId, Context context) {
        this.models = models;
        this.resourceId = resourceId;
        this.context = context;
    }

    protected void loadImageIntoView(String path, ImageView view) {
        if (path == null || path.isEmpty()) Picasso.get()
                .load(getErrorImage())
                .error(getErrorImage())
                .into(view);

        else Picasso.get()
                .load(path)
                .error(getErrorImage())
                .into(view);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateView(parent, viewType);
        return getViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (models == null)
            return;

        M model = models.get(position);

        if (model == null)
            return;

        bindData(holder, model);
    }

    public void addAll(ArrayList<M> secondaryData) {
        if (models == null || secondaryData == null)
            return;
        int startPosition = models.size();
        int range = secondaryData.size();
        models.addAll(secondaryData);
        notifyItemRangeInserted(startPosition, range);
    }

    private void showPopup(@MenuRes int menu, PopupMenu.OnMenuItemClickListener l, View anchor) {
        if (context == null || anchor == null)
            return;

        PopupMenu popup = new PopupMenu(context, anchor);
        popup.inflate(menu);
        popup.setOnMenuItemClickListener(l);
        popup.show();
    }

    public void delete(M m) {
        if (m == null || models == null)
            return;
        int index = models.indexOf(m);

        if (index < 0)
            return;

        models.remove(index);

        notifyItemRemoved(index);
    }

    public void update(M m) {
        if (m == null || models == null)
            return;
        int index = models.indexOf(m);

        if (index < 0)
            return;

        models.set(index, m);

        notifyItemChanged(index);
    }

    public void add(M m) {
        if (m == null || models == null)
            return;

        models.add(m);

        notifyItemInserted(models.size() - 1);

    }

    protected abstract Holder getViewHolder(View view, int type);

    protected abstract void bindData(Holder holder, M model);


    @Override
    public int getItemCount() {

        if (models == null)
            return 0;

        return models.size();
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<M> getModels() {
        return models;
    }

    @DrawableRes
    protected abstract int getErrorImage();


    protected View inflateView(@NonNull ViewGroup parent, int viewType) {
        return inflateView(parent, resourceId, false);
    }

    protected View inflateView(@NonNull ViewGroup parent, int resourceId, boolean attachToRoot) {
        return getInflater(parent).inflate(resourceId, parent, attachToRoot);
    }

    protected LayoutInflater getInflater(@NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

}
