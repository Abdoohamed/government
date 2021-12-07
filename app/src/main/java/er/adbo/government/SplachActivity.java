package er.adbo.government;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(SplachActivity.this, ReservationActivity.class);
               startActivity(intent);
               finish();
            }
        },3000);
    }
}