
package com.example.hatim.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {//implements View.OnClickListener{

    private EditText edtEmail, edtUserName, edtPas;
    private Button btnLog, btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
// to change the title of the app
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPas = findViewById(R.id.edtPas);
        edtPas.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                   onClick(btnSign);
                }
                return false;
            }
        });

        btnSign = findViewById(R.id.btnSign);
        btnLog = findViewById(R.id.btnLog);
        btnSign.setOnClickListener(this);
        btnLog.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }
        @Override
        public void onClick (View v){

            switch (v.getId()) {
                case R.id.btnSign:
                    if (edtEmail.getText().toString().equals("") ||
                            edtUserName.getText().toString().equals("")
                            || edtPas.getText().toString().equals("")) {

                        Toast.makeText(SignUp.this,
                                " empty values are not allowad ", Toast.LENGTH_SHORT).show();
                    } else {
                        final ParseUser parseUser = new ParseUser();
                        parseUser.setEmail(edtEmail.getText().toString());
                        parseUser.setUsername(edtUserName.getText().toString());
                        parseUser.setPassword(edtPas.getText().toString());
                        // show waiting image اظهار شريط التحميل
                        final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                        progressDialog.setMessage("signing up " + edtUserName.getText().toString());
                        progressDialog.show();
                        parseUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(SignUp.this, parseUser.get("username") +
                                            " is signed up successfuly", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUp.this, e.getMessage()
                                            , Toast.LENGTH_LONG).show();
                                }
                                //to end showing progress dialog
                                progressDialog.dismiss();
                            }
                        });
                    }
                case R.id.btnLog:

                    ParseUser.logInInBackground(
                            edtUserName.getText().toString(),
                            edtPas.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (e == null && user != null) {
                                        Toast.makeText(SignUp.this, user.get("username")
                                                + " welcome", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUp.this, e.getMessage()
                                                , Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
            }
        }

        public void rootLayoutTapped(View view){
        try {
            // to hide keyboared when user tapped in empty area
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    }




