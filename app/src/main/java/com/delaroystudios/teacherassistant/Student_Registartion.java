package com.delaroystudios.teacherassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Student_Registartion extends AppCompatActivity {
    //Firebase a ;


    Activity activity = this;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registartion);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonSAVE);
        assert btn != null;

        //a = new Firebase("https://teacherassistant-e142e.firebaseio.com/student/");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText)findViewById(R.id.edit_name);
        EditText roll = (EditText)findViewById(R.id.roll);
        EditText register = (EditText)findViewById(R.id.register);
        EditText contact = (EditText)findViewById(R.id.contact);
        String classSelected = spinner.getSelectedItem().toString();

        if(name.getText().length()<2||roll.getText().length()==0||register.getText().length()<2||
                contact.getText().length()<2||classSelected.length()<2)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        /*String id = register.getText().toString();
        String nm = name.getText().toString();
        String cnt = contact.getText().toString();
        String rll = roll.getText().toString();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myref = db.getReference("message");
        myref.setValue("alo");
        Firebase child = a.child(id);
        child.setValue(nm);
        child.setValue(cnt);
        child.setValue(rll);*/



        String qu = "INSERT INTO STUDENT VALUES('" +name.getText().toString()+ "'," +
                "'" + classSelected +"',"+
                "'" + register.getText().toString().toUpperCase() +"',"+
                "'" + contact.getText().toString() +"',"+
                "" + Integer.parseInt(roll.getText().toString()) +");";
        Log.d("Student Reg" , qu);
        AppBase.handler.execAction(qu);
        qu = "SELECT * FROM STUDENT WHERE regno = '" + register.getText().toString() +  "';";
        Log.d("Student Reg" , qu);
        if(AppBase.handler.execQuery(qu)!=null)
        {
            Toast.makeText(getBaseContext(),"Student Added", Toast.LENGTH_LONG).show();
            this.finish();
        }


    }
}
