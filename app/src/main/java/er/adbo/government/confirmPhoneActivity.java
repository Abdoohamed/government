package er.adbo.government;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class confirmPhoneActivity extends AppCompatActivity {
    private Button cre_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_phone);


        cre_btn = findViewById(R.id.create_btn);


    }


    private void Cre_btn() {

    }

    public void goConfirmPhoneActivity() {
        cre_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirmPhoneActivity.this, serviceActivity.class));
            }
        });


    }
}
