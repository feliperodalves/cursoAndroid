package com.feliperodalves.animacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_fadein = (TextView) findViewById(R.id.tv_fadein);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        tv_fadein.setAnimation(anim1);

        TextView tv_moveup = (TextView) findViewById(R.id.tv_moveup);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.moveup);
        tv_moveup.setAnimation(anim2);

        TextView tv_zoomin = (TextView) findViewById(R.id.tv_zoomin);
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        tv_zoomin.setAnimation(anim3);

        TextView tv_rotate = (TextView) findViewById(R.id.tv_rotate);
        Animation anim4 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        tv_rotate.setAnimation(anim4);
    }
}
