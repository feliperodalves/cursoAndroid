package feliperodalves.com.ss_cursoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView tv_email = (TextView) findViewById(R.id.tv_email);

        Bundle extra = getIntent().getExtras();
        String email = extra.getString("EMAIL");
        tv_email.setText(email);
    }
}
