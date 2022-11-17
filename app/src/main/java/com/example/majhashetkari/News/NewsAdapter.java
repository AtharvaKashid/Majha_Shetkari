package com.example.majhashetkari.News;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends FirebaseRecyclerAdapter<NewsModel,NewsAdapter.Holder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NewsAdapter(@NonNull FirebaseRecyclerOptions<NewsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull NewsModel model) {
        holder.header.setText(model.getHeader());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        NewsModel finalModel = model;
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.link.getContext(), NewsWebView.class);
                intent.putExtra("link", finalModel.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.link.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_rows, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder {
        View v;
        ImageView img1;
        TextView header,link;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.image);
            header = itemView.findViewById(R.id.header);
            link = itemView.findViewById(R.id.link);
        }
        public void setHeader(String Header){
            TextView h = v.findViewById(R.id.header);
            h.setText(Header);
        }
        public void setLink(String link){
            TextView Link = v.findViewById(R.id.link);
            Link.setText(link);
        }
        public void setImage(Context ctx, String image){
            img1=  v.findViewById(R.id.image);
            Picasso.with(ctx).load(image).into(img1);
        }

    }
}
