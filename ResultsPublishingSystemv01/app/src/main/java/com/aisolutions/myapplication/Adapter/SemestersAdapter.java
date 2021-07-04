package com.aisolutions.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aisolutions.myapplication.Model.ResultsSemester;
import com.aisolutions.myapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SemestersAdapter extends RecyclerView.Adapter<SemestersAdapter.MyViewHolder> {
    Context context;
    private ArrayList semName, semPass, semRepeat, semNotAttend, semLessAttend;

    public SemestersAdapter(Context context, ArrayList semName, ArrayList semPass, ArrayList semRepeat, ArrayList semNotAttend, ArrayList semLessAttend) {
        this.context = context;
        this.semName = semName;
        this.semPass = semPass;
        this.semRepeat = semRepeat;
        this.semNotAttend = semNotAttend;
        this.semLessAttend = semLessAttend;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.semester_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name_txt.setText(String.valueOf(semName.get(position)));
        holder.pass_txt.setText(String.valueOf(semPass.get(position)));
        holder.repeat_txt.setText(String.valueOf(semRepeat.get(position)));
        holder.notAttend_txt.setText(String.valueOf(semNotAttend.get(position)));
        holder.lessAttend_txt.setText(String.valueOf(semLessAttend.get(position)));

        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_sem = String.valueOf(semName.get(position));
                if (name_sem.equals("Year I Semester I")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year I Semester I");
                    context.startActivity(intent);
                } else if (name_sem.equals("Year I Semester II")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year I Semester II");
                    context.startActivity(intent);
                } else if (name_sem.equals("Year II Semester I")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year II Semester I");
                    context.startActivity(intent);
                } else if (name_sem.equals("Year II Semester II")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year II Semester II");
                    context.startActivity(intent);
                } else if (name_sem.equals("Year III Semester I")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year III Semester I");
                    context.startActivity(intent);
                } else if (name_sem.equals("Year III Semester II")){
                    Intent intent = new Intent(context, ResultsSemester.class);
                    intent.putExtra("semester","Year III Semester II");
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return semName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_txt, pass_txt, repeat_txt, notAttend_txt, lessAttend_txt;
        View myView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.semRecycler_semName);
            pass_txt = itemView.findViewById(R.id.semRecycler_semPass);
            repeat_txt = itemView.findViewById(R.id.semRecycler_semRepeat);
            notAttend_txt = itemView.findViewById(R.id.semRecycler_semNot);
            lessAttend_txt = itemView.findViewById(R.id.semRecycler_semLess);
            myView = itemView;
        }
    }

}
