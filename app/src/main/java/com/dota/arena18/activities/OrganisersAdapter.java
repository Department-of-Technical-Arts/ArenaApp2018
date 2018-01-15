package com.dota.arena18.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.arena18.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 12/29/2017.
 */

public class OrganisersAdapter extends RecyclerView.Adapter<OrganisersAdapter.MyViewHolder> {

    ArrayList<Contacts> arrayList;
    Context context;
    LayoutInflater inflater;
    AdapterView.OnItemClickListener clickListener;

    public OrganisersAdapter (Context context, AdapterView.OnItemClickListener clickListener, ArrayList<Contacts> data){
        inflater=LayoutInflater.from(context);
        arrayList = new ArrayList<>();
        arrayList = data;
        this.context=context;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.contactus_card,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_contact_show);
        myViewHolder.mView.startAnimation(animation);

        AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.1f);
        aa1.setDuration(400);
        myViewHolder.imageView.startAnimation(aa1);

        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        aa.setDuration(400);

        myViewHolder.name.setText(arrayList.get(i).getName());
        myViewHolder.designation.setText((arrayList.get(i).getDesignation()));
        myViewHolder.imageView.setImageResource(arrayList.get(i).getImage());
        myViewHolder.cardToolbar.getMenu().clear();
        myViewHolder.cardToolbar.inflateMenu(R.menu.options_menu);
//        myViewHolder.mobile.setText(arrayList.get(i).getMobile());
        myViewHolder.cardToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + arrayList.get(i).getMobile());
                        myViewHolder.itemView.getContext().startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                   /* case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{arrayList.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        myViewHolder.itemView.getContext().startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;*/
                }
                return false;
            }
        });
    }

    public void setArrayList(ArrayList<Contacts> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView name,designation,numberTV,mobile;
        Toolbar cardToolbar;
        private View mView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            imageView = (ImageView) itemView.findViewById(R.id.contact_image);
            name=(TextView) itemView.findViewById(R.id.contact_name);
            designation=(TextView)itemView.findViewById(R.id.contact_designation);
//          mobile= (TextView) itemView.findViewById(R.id.phoneno);
//          numberTV=(TextView) itemView.findViewById(R.id.phoneno);
            cardToolbar = (Toolbar) itemView.findViewById(R.id.contact_card_toolbar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(null,view,getAdapterPosition(),view.getId());
        }

        public void clearAnimation()
        {
            itemView.clearAnimation();
        }
    }
}
