package com.example.project.chickenlist;

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

public class ChickenAdapter extends RecyclerView.Adapter<ChickenAdapter.MyViewHolder> {

    String data1[], data2[], data3[];
    int images[];
    String url[] = {"https://www.rankingdak.com/product/view?productCd=923"
            , "https://www.rankingdak.com/product/view?productCd=11879"
            , "https://www.rankingdak.com/product/view?productCd=24931"
            , "https://www.rankingdak.com/product/view?productCd=9477"
            , "https://www.rankingdak.com/product/view?productCd=1077"
            , "https://www.rankingdak.com/product/view?productCd=14149"
            , "https://www.rankingdak.com/product/view?productCd=797"
            , "https://www.rankingdak.com/product/view?productCd=10739"
            , "https://www.rankingdak.com/product/view?productCd=23560"
            , "https://www.rankingdak.com/product/view?productCd=24533"};
    Context context;

    public ChickenAdapter(Context ct, String s1[], String s2[], String s3[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chicken_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.textView1.setText(data1[position]);
        holder.textView2.setText(data2[position]);
        holder.textView3.setText(data3[position]);
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

        TextView textView1, textView2, textView3;
        ImageView imageView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
