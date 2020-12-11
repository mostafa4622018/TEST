package com.example.jsoninandroidstudio;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jsoninandroidstudio.Model.User;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
    private List<User> exampleList;
    private Context context;

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView username;
        private TextView emailAddress;
        private ImageView avatar;


        public UserViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.myAvatar);
            username = itemView.findViewById(R.id.username);
            emailAddress = itemView.findViewById(R.id.emailaddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public UsersAdapter(Context context , List<User> exampleList){
        this.context = context;
        this.exampleList = exampleList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.user_list_item , parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User currentItem = exampleList.get(position);
        GlideToVectorYou.init().with(context).setPlaceHolder(R.drawable.ic_launcher_foreground , R.drawable.ic_launcher_background).load(Uri.parse(currentItem.getAvatar()) , holder.avatar);
        holder.emailAddress.setText(currentItem.getEmail());
        holder.username.setText(currentItem.getUsername());
//        Picasso.with(context).load(imageUrl).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}
