package com.example.project.hometraininglist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

public class HomeTrainingAdapter extends RecyclerView.Adapter<HomeTrainingAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
    String url[] = {"https://www.youtube.com/watch?v=-CEvMUWv750"
            , "https://www.youtube.com/watch?v=INSX8a0wTxo"
            , "https://www.youtube.com/watch?v=cbosK3M2k7o"
            , "https://www.youtube.com/watch?v=lGfWLyuS0og"
            , "https://www.youtube.com/watch?v=wfwLWniOJc4"
            , "https://www.youtube.com/watch?v=0T6XcIdavVQ"
            , "https://www.youtube.com/watch?v=vaxmAqNvWxM"
            , "https://www.youtube.com/watch?v=tKY-3k5CMQk"
            , "https://www.youtube.com/watch?v=JIdes3BqtMo"
            , "https://www.youtube.com/watch?v=9WTpgyYEz3M"};
    Context context;

    public HomeTrainingAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hometraining_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.textView1.setText(data1[position]);
        holder.textView2.setText(data2[position]);
        holder.imageView.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView1, textView2;
        ImageView imageView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
