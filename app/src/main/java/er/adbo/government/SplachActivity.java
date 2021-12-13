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
        ModelSaveData modaldata=new ModelSaveData(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(modaldata.isUserLogin())
                {
                    startActivity(new Intent(SplachActivity.this, UserActivity.class));



                }
                else

                {
                    startActivity(new Intent(SplachActivity.this, LoginActivity.class));
                    finish();

                }

            }
        },3000);
    }
}