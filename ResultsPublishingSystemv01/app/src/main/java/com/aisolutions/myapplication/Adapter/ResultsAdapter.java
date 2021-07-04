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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {
    Context context;
    private ArrayList reCode, reName, reStatus, reResult;

    public ResultsAdapter(Context context, ArrayList reCode, ArrayList reName, ArrayList reStatus, ArrayList reResult) {
        this.context = context;
        this.reCode = reCode;
        this.reName = reName;
        this.reStatus = reStatus;
        this.reResult = reResult;
    }

    @NonNull
    @Override
    public ResultsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cutom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.MyViewHolder holder, int position) {
        holder.code.setText(String.valueOf(reCode.get(position)));
        holder.name.setText(String.valueOf(reName.get(position)));
        String status = String.valueOf(reStatus.get(position));

        if (status.equals("PASS")){
            holder.status.setImageResource(R.drawable.ic_pass);
        } else if(status.equals("REPEAT")){
            holder.status.setImageResource(R.drawable.ic_repeat);
        } else if (status.equals("NOT_ATTEND")){
            holder.status.setImageResource(R.drawable.ic_not);
        } else if (status.equals("LESS_ATTENDS")){
            holder.status.setImageResource(R.drawable.ic_less);
        } else {
            holder.status.setImageResource(R.drawable.ic_null);
        }
        holder.result.setText(String.valueOf(reResult.get(position)));

    }

    @Override
    public int getItemCount() {
        return reCode.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView code,name,result;
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
