package er.adbo.government;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PhoneActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText pho;
    private Button next;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        pho = findViewById(R.id.pho);
        next = findViewById(R.id.next);

           goConfirmPhoneActivity();
           next();

    }

    private void next() {

    }

    public void goConfirmPhoneActivity() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneActivity.this, confirmPhoneActivity.class));
            }
        });
    }
}