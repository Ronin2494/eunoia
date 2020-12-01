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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextView login;
    TextView username, email,password, confirm;
    Button signUp;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       login = findViewById(R.id.login);
       username = findViewById(R.id.login_email);
       password = findViewById(R.id.password);
       confirm = findViewById(R.id.confirm);
       email = findViewById(R.id.email);
       signUp = findViewById(R.id.signupbutton);

       fAuth = FirebaseAuth.getInstance();

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String email1 = email.getText().toString().trim();
               final String password1 = password.getText().toString().trim();
               final String name = username.getText().toString().trim();
               final String cnfrm = confirm.getText().toString().trim();

               user = fAuth.getCurrentUser();

               //To check if user already existed or not
               if(user != null){
                   startActivity(new Intent(getApplicationContext(), home.class));
                   finish();
               }

               ref = FirebaseDatabase.getInstance().getReference("users_data");


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
                   //authentication with respect to email and password
                   fAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               //to  store user info in real-time database as authentication only
                               // stores email and password and it creates a UID for each user automatically
                               users u1 = new users(name, email1, password1, cnfrm);
                               ref.child(fAuth.getCurrentUser().getUid()).child("user_info").setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful()) {
                                           Toast.makeText(signup.this, "Database Saved successfully", Toast.LENGTH_SHORT).show();
                                       }
                                       else{
                                           Toast.makeText(signup.this, "Database not Saved", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                               //Toast.makeText(signup.this, "User Created", Toast.LENGTH_SHORT).show();
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


       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(signup.this, login.class);
               startActivity(i);

           }
       });

    }
}
