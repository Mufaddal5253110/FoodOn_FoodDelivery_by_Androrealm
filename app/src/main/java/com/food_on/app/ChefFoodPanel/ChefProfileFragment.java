package com.food_on.app.ChefFoodPanel;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.food_on.app.R;
public class ChefProfileFragment extends Fragment {

    Button post;
    ConstraintLayout bgimage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_profile, container, false);
        getActivity().setTitle("Post Dish");
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bghome2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic3), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic5), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic6), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bggg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic9), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic10), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic11), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic12), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic13), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic14), 3000);



        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);
        bgimage = v.findViewById(R.id.back1);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        post = (Button) v.findViewById(R.id.post_dish);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Chef_PostDish.class));
            }
        });

        return v;
    }
}