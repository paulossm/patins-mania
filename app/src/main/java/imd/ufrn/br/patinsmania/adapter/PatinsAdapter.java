package imd.ufrn.br.patinsmania.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import imd.ufrn.br.patinsmania.R;
import imd.ufrn.br.patinsmania.data.Patins;

public class PatinsAdapter extends BaseAdapter {
    private Context context;
    private List<Patins> patins = new ArrayList<>();

    public PatinsAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return patins.size();
    }

    @Override
    public Patins getItem(int position) {
        return patins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return patins.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.adapter_listpatins, parent, false);
            holder = new ViewHolder();
            holder.txtModel = view.findViewById(R.id.txt_model);
            holder.txtBrand = view.findViewById(R.id.txt_brand);
            holder.txtSize = view.findViewById(R.id.txt_size);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Patins p = patins.get(position);
        holder.txtBrand.setText(p.getBrand());
        holder.txtModel.setText(p.getModel());
        holder.txtSize.setText(String.valueOf(p.getSize()));

        return view;
    }

    public void setItems(List<Patins> patins) {
        this.patins = patins;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txtModel;
        public TextView txtBrand;
        public TextView txtSize;
    }
}
