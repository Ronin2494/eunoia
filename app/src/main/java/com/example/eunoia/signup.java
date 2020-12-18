package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    TextView login;
    TextView username, email,password, confirm;
    Button signUp;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    private FirebaseUser user;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

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

               if(TextUtils.isEmpty(email1) && Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                   email.setError("Enter a valid email address");
                   return;
               }

               if(!validatePassword()){
                   //Toast.makeText(signup.this, "Please enter a valid password with minimum 8 characters", Toast.LENGTH_SHORT).show();
                   //password.setError("Please enter a valid password with minimum 8 characters");
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
                                           Toast.makeText(signup.this, "Data Saved successfully", Toast.LENGTH_SHORT).show();
                                       }
                                       else{
                                           Toast.makeText(signup.this, "Data not Saved", Toast.LENGTH_SHORT).show();
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

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


}
