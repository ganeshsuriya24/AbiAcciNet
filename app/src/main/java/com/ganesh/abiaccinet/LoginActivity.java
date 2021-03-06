package com.ganesh.abiaccinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signup;
    private EditText username, password;
    private Button login,acc;
    private ProgressDialog prog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.loginn);
        signup = (Button) findViewById(R.id.signup);
        acc = (Button)findViewById(R.id.acc);
        prog = new ProgressDialog(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        acc.setOnClickListener(this);
    }
    private void userlogin() {
        String ema = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(ema) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter email/password", Toast.LENGTH_SHORT).show();
            return;
        }
       /* prog.setMessage("Registering User....");
        prog.show(); */
        auth.signInWithEmailAndPassword(ema, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }
                    }
                });
    }

    public void onClick(View view) {
        if (view == login) {
            userlogin();
        }
        if(view==signup){
            finish();
            Intent i = new Intent(LoginActivity.this, UserRegistrationActivity.class);
            startActivity(i);
        }
        if(view==acc){
            startActivity(new Intent(this,AccidentComplain.class));
        }
    }

}


