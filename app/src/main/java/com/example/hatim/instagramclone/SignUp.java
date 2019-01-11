
package com.example.hatim.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
 private Button btnSave , button , btnSwitch ;
 private EditText edtName,edtPS,edtPP,edtKS,edtKP;
 private TextView txtGetData;
 private String allKickBoxer;

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
        txtGetData=findViewById(R.id.txtGetData);
        button=findViewById(R.id.button);
        btnSwitch=findViewById(R.id.btnSwitch);

        btnSave.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("JnjIm8gCjU", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e == null){
                            txtGetData.setText(object.get("name")+ " - " + " kickSpeed : "
                                    + object.get("kickSpeed"));
                        }
                    }
                });
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxer="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
            //we read it as where punchPower Greater than 100
                queryAll.whereGreaterThan("punchPower",100);
                //setLimit = choose the number of objects that we want to show
                queryAll.setLimit(3);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e == null ){
                            if(objects.size() > 0){

                                for(ParseObject kickBoxer : objects){
                                    allKickBoxer = allKickBoxer + kickBoxer.get("name")+ " "
                                            + kickBoxer.get("kickSpeed") + "\n";
                                }
                                Toast.makeText(SignUp.this,allKickBoxer
                                        ,Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this,e.getMessage()
                                        ,Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });
btnSwitch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
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

