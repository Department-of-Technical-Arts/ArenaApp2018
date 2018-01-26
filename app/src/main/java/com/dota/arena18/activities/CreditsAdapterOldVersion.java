package com.dota.arena18.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dota.arena18.R;
import com.jaouan.compoundlayout.CircleGradientRadioLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 1/26/2018.
 */

public class CreditsAdapterOldVersion extends RecyclerView.Adapter<CreditsAdapterOldVersion.CreditsViewHolder> {

    private static final String TAG = CreditsAdapter.class.getSimpleName();

    private ArrayList<DeveloperLayoutDetails> data;
    private Context mContext;

    public CreditsAdapterOldVersion(Context context, ArrayList<DeveloperLayoutDetails> data) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public CreditsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CreditsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credits, parent, false));
    }

    @Override
    public void onBindViewHolder(final CreditsViewHolder holder, int position) {
        DeveloperLayoutDetails layoutDetails = data.get(position);
        final ArrayList<DeveloperDetails> devDetails = layoutDetails.getDevs();

        holder.mTitle.setText(layoutDetails.getTitle());

        if (devDetails.size() < 3) {
            holder.mProfiles[2].setVisibility(View.GONE);
        } else {
            holder.mProfiles[2].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < devDetails.size(); i++) {
            final DeveloperDetails dev = devDetails.get(i);
            final String[] devLinks = dev.getLinks();

            // set image
            final CircleImageView circLayout = holder.mProfiles[i];
            circLayout.setBackgroundResource(dev.getProfileImg());

            // set onClickListener
            circLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Animation fadeOutAnimation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_out);
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // set name
                            holder.mDevName.setText(dev.getName());

                            // set links
                            for (int j = 0; j < 4; j++) {
                                Button currButton = holder.mSocialButtons[j];
                                final String currLink = devLinks[j];
                                if (currLink != null) {
                                    currButton.setVisibility(View.VISIBLE);
                                    currButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //Log.i(TAG, "reached socialButton onClick");
                                            Uri uri = Uri.parse(currLink);
                                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                            mContext.startActivity(intent);
                                        }
                                    });
                                } else {
                                    currButton.setVisibility(View.GONE);
                                }
                            }

                            // set gradients

                            // start fade in animation
                            holder.mDescriptionLayout.setVisibility(View.VISIBLE);
                            holder.mDescriptionLayout.startAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    holder.mDescriptionLayout.setVisibility(View.VISIBLE);
                    holder.mDescriptionLayout.startAnimation(fadeOutAnimation);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return (data!= null) ? data.size() : 0;
    }

    public class CreditsViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        CircleImageView[] mProfiles = new CircleImageView[3];

        LinearLayout mDescriptionLayout;
        TextView mDevName;

        Button[] mSocialButtons = new Button[4];


        public CreditsViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.credits_title);

            mProfiles[0] = itemView.findViewById(R.id.profile_1);
            mProfiles[1]= itemView.findViewById(R.id.profile_2);
            mProfiles[2] = itemView.findViewById(R.id.profile_3);

            mDescriptionLayout = itemView.findViewById(R.id.description_layout);
            mDevName = itemView.findViewById(R.id.credits_devname);

            mSocialButtons[0] = itemView.findViewById(R.id.credit_button1);
            mSocialButtons[1] = itemView.findViewById(R.id.credit_button2);
            mSocialButtons[2] = itemView.findViewById(R.id.credit_button3);
            mSocialButtons[3] = itemView.findViewById(R.id.credit_button4);
        }
    }

}
