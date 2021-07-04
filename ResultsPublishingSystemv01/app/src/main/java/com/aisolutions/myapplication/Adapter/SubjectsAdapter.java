package com.aisolutions.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aisolutions.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.MyViewHolder> {
    Context context;
    String[] codeList, nameList, gpaList;

    public SubjectsAdapter(Context context, String[] codeList, String[] nameList, String[] gpaList) {
        this.context = context;
        this.codeList = codeList;
        this.nameList = nameList;
        this.gpaList = gpaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cutom_row, parent, false);
        return new SubjectsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.code.setText(codeList[position]);
        holder.name.setText(nameList[position]);
        holder.gpa.setText(gpaList[position]);
        holder.img.setImageResource(R.drawable.ic_sub_view);
    }

    @Override
    public int getItemCount() {
        return codeList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView code, name, gpa;
        View myView;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.sub_code);
            name = itemView.findViewById(R.id.sub_name);
            gpa = itemView.findViewById(R.id.sub_result);
            img = itemView.findViewById(R.id.img_card);
            myView = itemView;
        }
    }
}
