package com.delaroystudios.teacherassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Class_Add extends AppCompatActivity {


    Activity activity = this;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);



        Button btn = (Button) findViewById(R.id.buttonSAVE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText)findViewById(R.id.edit_name);
        EditText id = (EditText)findViewById(R.id.id);
        EditText number = (EditText)findViewById(R.id.number);


        if(name.getText().length()<2||id.getText().length()<2||
                number.getText().length()<0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO CLASS VALUES('" +id.getText().toString().toUpperCase()+ "'," +
                "'" + name.getText().toString() + "',"
                + "" + Integer.parseInt(number.getText().toString()) +");";
        Log.d("Class Reg" , qu);
        AppBase.handler.execAction(qu);
        qu = "SELECT * FROM CLASS WHERE clid = '" + id.getText().toString() +  "';";
        Log.d("Class Reg" , qu);
        if(AppBase.handler.execQuery(qu)!=null)
        {
            Toast.makeText(getBaseContext(),"Class Added", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }
}
