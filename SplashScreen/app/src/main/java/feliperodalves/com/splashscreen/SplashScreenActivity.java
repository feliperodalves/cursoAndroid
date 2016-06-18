package feliperodalves.com.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    protected void onCreate(Bundle save){
        super.onCreate(save);
        setContentView(R.layout.splash_screen);

        new Thread(new Runnable() {
            @Override
            public void run(){

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}
