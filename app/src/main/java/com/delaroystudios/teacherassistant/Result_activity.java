package com.delaroystudios.teacherassistant;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.delaroystudios.teacherassistant.database_constant.tbl_class;


public class Result_activity extends AppCompatActivity {
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        Button loadButton = (Button)findViewById(R.id.loadForEdit);
        assert loadButton != null;
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText adm = (EditText)findViewById(R.id.register_);
                String qu = "SELECT * FROM CLASS WHERE clid = '" + adm.getText().toString().toUpperCase() + "'";
                Cursor cr = AppBase.handler.execQuery(qu);
                if(cr==null||cr.getCount()==0)
                {
                    Toast.makeText(getBaseContext(),"No Such Class",Toast.LENGTH_LONG).show();
                }else
                {
                    cr.moveToFirst();
                    try {
                        EditText name = (EditText) findViewById(R.id.edit_name_);
                        EditText contact = (EditText) findViewById(R.id.contact_);
                        assert name != null;
                        name.setText(cr.getString(1));
                        assert contact != null;
                        contact.setText(cr.getString(2));
                    }catch (Exception e)
                    {}
                }
            }
        });


        Button saveEdit = (Button)findViewById(R.id.buttonSAVEEDITS);
        assert saveEdit != null;
        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.edit_name_);
                EditText contact = (EditText) findViewById(R.id.contact_);
                EditText adm = (EditText)findViewById(R.id.register_);

                /*String qu = "UPDATE CLASS SET nm = '" + name.getText().toString() + "' , " +
                          " na = " + contact.getText().toString() + " " +
                        "WHERE clid = '" + adm.getText().toString() + "'";*/


                String qu = "UPDATE "+ tbl_class.TABLE_NAME + " SET "+tbl_class.COL_NM_NAME+"= '" + name.getText().toString() + "' , " +
                        tbl_class.COL_NA_NAME + " = " + contact.getText().toString() + " " +
                        "WHERE " + tbl_class.COL_CLID_NAME + " = '" + adm.getText().toString().toUpperCase() + "'";


                Log.v("query: ", qu);
                //Log.d("Class_Student", qu);
                if (AppBase.handler.execAction(qu)) {
                    Toast.makeText(getBaseContext(), "Edit Saved", Toast.LENGTH_LONG).show();
                    activity.finish();

                } else
                    Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG).show();
            }
        });
    }

}