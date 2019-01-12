package com.example.hatim.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLogInActivity extends AppCompatActivity {

    EditText usernameS, usernameL, pasS, pasL;
    Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        usernameS = findViewById(R.id.edtUserNameSign);
        usernameL = findViewById(R.id.edtUserNameLog);
        pasS = findViewById(R.id.edtPasSign);
        pasL = findViewById(R.id.edtPasLog);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// when click on the button a new user will create
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(usernameS.getText().toString());
                appUser.setPassword(pasS.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(SignUpLogInActivity.this,
                                    appUser.get("username") + " is signed Up",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpLogInActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(SignUpLogInActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(usernameL.getText().toString(),
                        pasL.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    Toast.makeText(SignUpLogInActivity.this,
                                            user.get("username") + " is Log in ",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUpLogInActivity.this, WelcomeActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(SignUpLogInActivity.this,
                                            e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
