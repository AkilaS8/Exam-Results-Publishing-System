package com.aisolutions.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aisolutions.myapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategorizeAdapter extends RecyclerView.Adapter<CategorizeAdapter.MyViewHolder> {
    Context context;
    private ArrayList reCode, reName, reResult;
    private String category;

    public CategorizeAdapter(Context context, ArrayList reCode, ArrayList reName, ArrayList reResult, String category) {
        this.context = context;
        this.reCode = reCode;
        this.reName = reName;
        this.reResult = reResult;
        this.category = category;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cutom_row, parent, false);
        return new CategorizeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.code.setText(String.valueOf(reCode.get(position)));
        holder.name.setText(String.valueOf(reName.get(position)));
        holder.result.setText(String.valueOf(reResult.get(position)));
         if (category.equals("Pass Subjects")){
             holder.status.setImageResource(R.drawable.ic_pass);
         } else if (category.equals("Repeat Subjects")){
             holder.status.setImageResource(R.drawable.ic_repeat);
         } else if (category.equals("Not Attended Subjects")){
             holder.status.setImageResource(R.drawable.ic_not);
         }
    }

    @Override
    public int getItemCount() {
        return reCode.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView code, name, result;
        ImageView status;
        View myView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.sub_code);
            name = itemView.findViewById(R.id.sub_name);
            result = itemView.findViewById(R.id.sub_result);
            status = itemView.findViewById(R.id.img_card);
            myView = itemView;

        }
    }
}
