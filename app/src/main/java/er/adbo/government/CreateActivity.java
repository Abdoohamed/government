package er.adbo.government;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class CreateActivity extends AppCompatActivity {
    Context context =this;
    private ProgressBar CreateProgress;
    private FirebaseAuth firebaseAuth;
    private EditText CreateName;
    private EditText CreateEmail;
    private EditText createPhone;
    private EditText CreatePass;
    private Button BCreate;
    private FirebaseFirestore firestore;
    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        //findview
        CreateName = findViewById(R.id.CreateName);
        createPhone =findViewById(R.id.CreatePhone);
        CreateEmail = findViewById(R.id.CreateEmail);
        CreatePass = findViewById(R.id.CreatePass);
        CreateProgress = findViewById(R.id.CreateProgress);
        spinner = findViewById(R.id.spinnr);

        // firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore  = FirebaseFirestore.getInstance();


         this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // OnClick
        BCreate = findViewById(R.id.BCreate);



         BCreate.setOnClickListener(v -> {
             validationDate();

         });



    }
    private void validationDate(){
        String name = CreateName.getText().toString().trim();
        String email = CreateEmail.getText().toString().trim();
        String phone = createPhone.getText().toString().trim();
        String pass = CreatePass.getText().toString().trim();


        if (name.isEmpty()) {
            Toast.makeText(CreateActivity.this, R.string.error_name_create, Toast.LENGTH_LONG).show();
            CreateName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please add ur email", Toast.LENGTH_LONG).show();
            CreateEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(CreateActivity.this, "Please add valid email", Toast.LENGTH_LONG).show();
            CreateEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please add ur phone", Toast.LENGTH_LONG).show();
            createPhone.requestFocus();
            return;
        }

        if (phone.length() < 11) {
            Toast.makeText(CreateActivity.this, "phone should be 11 char", Toast.LENGTH_LONG).show();
            createPhone.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please add ur password", Toast.LENGTH_LONG).show();
            CreatePass.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            Toast.makeText(CreateActivity.this, "password should be 11 char", Toast.LENGTH_LONG).show();
            CreatePass.requestFocus();
            return;

    }


        register(name ,email ,pass, phone);
    }


    private void register(String name, String email, String password ,String phone) {

        CreateProgress.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        CreateProgress.setVisibility(View.GONE);
                        startActivity(new Intent(CreateActivity.this,UserActivity.class));
                        Toast.makeText(CreateActivity.this, "User created successfully", Toast.LENGTH_LONG).show();
                        sendData(email,password,name,phone);

                    } else {
                        // handle error
                        CreateProgress.setVisibility(View.GONE);
                        Toast.makeText(CreateActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                });

    }
    private void sendData(String email, String password, String name,String phone) {
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());


        // Create a new user
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("phone",phone);
        map.put("email",email);
        map.put("password",password);
        map.put("service", spinner.getSelectedItem());
        map.put("timestamp",format);
// Add a new document with a generated ID
        firestore.collection("User")
                .document("1")
                .set(map)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        CreateProgress.setVisibility(View.GONE);
                        Toast.makeText(CreateActivity.this, "on Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public void login(View view) {
        startActivity(new Intent(CreateActivity.this, LoginActivity.class));
    }
}


