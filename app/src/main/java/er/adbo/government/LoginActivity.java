package er.adbo.government;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar lprogressBar;
    private EditText lEmail,lPass;
    private Button llogin,lcreate,lres;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findview
        lEmail = findViewById(R.id.login_email);
        lPass = findViewById(R.id.login_pass);
        lprogressBar = findViewById(R.id.progress);
        //firebase
        fAuth = FirebaseAuth.getInstance();
        //onclickl
        llogin = findViewById(R.id.login_btn);
        lcreate = findViewById(R.id.create_btn);
        lres = findViewById(R.id.Res_btn);
        lcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,CreateActivity.class));
            }
        });
        llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=lEmail.getText().toString().trim();
                String password=lPass.getText().toString().trim();
                if (validationdata(email ,password))
                {
                    register(email,password);
                }



            }
        });
    }
    private boolean  validationdata(String email , String password){

        if(email.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please add ur email", Toast.LENGTH_SHORT).show();
            lEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(LoginActivity.this, "Please add valid email", Toast.LENGTH_SHORT).show();
            lEmail.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please add ur password", Toast.LENGTH_SHORT).show();
            lPass.requestFocus();
            return false;
        }
        if(password.length()<6){
            Toast.makeText(LoginActivity.this, " password should be 6char", Toast.LENGTH_SHORT).show();
            lPass.requestFocus();
            return false;
        }
return  true ;
    }


    private void register(String email, String password) {
        lprogressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()  {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            lprogressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "sing in Successfuly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,UserActivity.class));
                        }
                        //handel error
                        else {
                            lprogressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    public void forget(View view) {
        startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
    }




}







