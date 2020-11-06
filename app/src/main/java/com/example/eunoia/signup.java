package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    TextView signin;
    TextView username, email,password, confirm;
    Button signUp;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       signin = findViewById(R.id.signin);
       username = findViewById(R.id.login_email);
       password = findViewById(R.id.password);
       confirm = findViewById(R.id.confirm);
       email = findViewById(R.id.email);
       signUp = findViewById(R.id.signupbutton);

       fAuth = FirebaseAuth.getInstance();

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email1 = email.getText().toString().trim();
               String password1 = password.getText().toString().trim();
               String name = username.getText().toString().trim();
               String cnfrm = confirm.getText().toString().trim();

               //To check if user already existed or not
               if(fAuth.getCurrentUser() != null){
                   startActivity(new Intent(getApplicationContext(), home.class));
                   finish();
               }


               if(TextUtils.isEmpty(name)){
                   username.setError("Username is required");
                   return;
               }

               if(TextUtils.isEmpty(email1)){
                   email.setError("Email is required");
                   return;
               }

               if(TextUtils.isEmpty(password1)){
                   password.setError("Password is required");
                   return;
               }

               if(password1.length() < 6){
                   password.setError("password must be greater than 6 characters");
                   return;
               }

               if(TextUtils.isEmpty(cnfrm)){
                   confirm.setError("Confirm password is required");
                   return;
               }

               if (password1.equals(cnfrm)) {
                   fAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(signup.this, "User Created", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(), home.class));

                           } else {
                               Toast.makeText(signup.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }else{
                   confirm.setError("password does not match");
               }
           }



       });





       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(signup.this, login.class);
               startActivity(i);

           }
       });

    }
}
