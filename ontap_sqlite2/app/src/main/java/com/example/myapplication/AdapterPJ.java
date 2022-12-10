package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterPJ extends ArrayAdapter<Project> {
    private Context context;
    private int resource;
    private ArrayList<Project> projects;
    public AdapterPJ( Context context, int resource, ArrayList<Project> projects) {
        super(context, resource, projects);
        this.context = context;
        this.resource = resource;
        this.projects = projects;
    }

    class ViewHolder {
        TextView tvPJN, tvDL, tvNAME;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder.tvPJN = convertView.findViewById(R.id.tvPJN);
            viewHolder.tvDL = convertView.findViewById(R.id.tvDL);
            viewHolder.tvNAME = convertView.findViewById(R.id.tvNAME);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Project project = projects.get(position);
        viewHolder.tvPJN.setText(project.getProjectname());
        viewHolder.tvNAME.setText(project.getName());
        viewHolder.tvDL.setText(project.getDeadline());
        return convertView;
    }
}
