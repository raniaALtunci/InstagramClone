
package com.example.hatim.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
 private Button btnSave;
 private EditText edtName,edtPS,edtPP,edtKS,edtKP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave=findViewById(R.id.btnSave);
        edtName=findViewById(R.id.edtName);
        edtPS=findViewById(R.id.edtPS);
        edtPP=findViewById(R.id.edtPP);
        edtKS=findViewById(R.id.edtKS);
        edtKP=findViewById(R.id.edtKP);

        btnSave.setOnClickListener(SignUp.this);
    }


    @Override
    public void onClick(View v) {
        try{
        final ParseObject kickBoxer=new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punchSpeed",Integer.parseInt(edtPS.getText().toString()));
        kickBoxer.put("punchPower",Integer.parseInt(edtPP.getText().toString()));
        kickBoxer.put("kickSpeed",Integer.parseInt(edtKS.getText().toString()));
        kickBoxer.put("kickPower",Integer.parseInt(edtKP.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                Toast.makeText(SignUp.this,kickBoxer.get("name")+" Save to server"
                        ,Toast.LENGTH_SHORT).show();
            }else{
                    Toast.makeText(SignUp.this,e.getMessage()
                            ,Toast.LENGTH_SHORT).show();
            }}
        });
        }catch (Exception e){
            Toast.makeText(SignUp.this,e.getMessage()
                    ,Toast.LENGTH_LONG).show();
        }
    }
}

