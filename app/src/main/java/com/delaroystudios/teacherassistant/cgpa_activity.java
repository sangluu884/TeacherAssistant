package com.delaroystudios.teacherassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class cgpa_activity extends AppCompatActivity{

    databaseHandler handler = AppBase.handler;
    Activity cgpaActivity = this;
    ListView listView;
    profile_adapter adapter;
    ArrayList<String> Class;
    ArrayList<String> Student;

    Activity activity =this;
    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);
        Class = new ArrayList<>();
        Student = new ArrayList<>();
        /*dates = new ArrayList<>();
        datesALONE = new ArrayList<>();
        hourALONE = new ArrayList<>();
        atts = new ArrayList<>();*/

        listView = (ListView) findViewById(R.id.classView_list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = new Intent(cgpaActivity,Class_Add.class);
                startActivity(launchIntent);
            }
        });

        TextView textView = (TextView)findViewById(R.id.profileContentView);
        assert textView != null;
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                alert.setTitle("Delete Class");
                alert.setMessage("Are you sure ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) findViewById(R.id.editText);
                        String regno = editText.getText().toString();
                        String qu = "DELETE FROM CLASS WHERE clid = '" + regno.toUpperCase() + "'";
                        AppBase.handler.execAction(qu);

                    }
                });
                alert.setNegativeButton("No", null);
                alert.show();
                return true;
            }
        });


        Button findButton = (Button)findViewById(R.id.buttonFind);
        assert findButton != null;
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(v);
            }
        });
    }

    public void find(View view) {

        EditText editText = (EditText)findViewById(R.id.editText);
        TextView textView = (TextView)findViewById(R.id.profileContentView);
        String reg = editText.getText().toString();
        String qu = "SELECT * FROM CLASS WHERE clid = '" + reg.toUpperCase() + "'";
        //String qc = "SELECT * FROM ATTENDANCE WHERE register = '" + reg.toUpperCase() + "';";
        //String qd = "SELECT * FROM ATTENDANCE WHERE register = '" + reg.toUpperCase() + "' AND isPresent = 1";
        Cursor cursor = handler.execQuery(qu);
        if(cursor==null||cursor.getCount()==0)
        {
            assert textView != null;
            textView.setText("No Data Available");
        }
        else {
            cursor.moveToFirst();
            String buffer = "";
            buffer += "Class ID: " + cursor.getString(0) + "\n";
            buffer += "Class Name:" + cursor.getString(1) + "\n";
            buffer += "Attendance Number: " + cursor.getInt(2) + "\n";
            textView.setText(buffer);
        }

        //Start Count Here

        /*float att = 0f;
        Cursor cur = handler.execQuery(qc);
        Cursor cur1 = handler.execQuery(qd);
        if(cur==null)
        {
            Log.d("profile","cur null");
        }
        if(cur1==null)
        {
            Log.d("profile","cur1 null");
        }
        if(cur!=null&&cur1!=null)
        {
            cur.moveToFirst();
            cur1.moveToFirst();
            try{
                att = ((float) cur1.getCount()/cur.getCount())*100;
                if(att<=0)
                    att = 0f;
                Log.d("profile_activity","Total = " + cur.getCount() + " avail = "+cur1.getCount() + " per " + att);
            }catch (Exception e){att = -1;}
        }


        if(cursor==null||cursor.getCount()==0)
        {
            assert textView != null;
            textView.setText("No Data Available");
        }else
        {
            String attendance = "";
            if(att<0)
            {
                attendance = "Attendance Not Available";
            }else
                attendance = " Attendance " + att + " %";
            cursor.moveToFirst();
            String buffer = "";
            buffer += " " + cursor.getString(0) + "\n";
            buffer += " " + cursor.getString(1)+ "\n";
            buffer += " " + cursor.getString(2)+ "\n";
            buffer += " " + cursor.getString(3)+ "\n";
            buffer += " " + cursor.getInt(4)+ "\n";
            buffer += " " + attendance+ "\n";
            textView.setText(buffer);

            String q = "SELECT * FROM ATTENDANCE WHERE register = '" + editText.getText().toString().toUpperCase() + "'";
            Cursor cursorx = handler.execQuery(q);
            if(cursorx==null||cursorx.getCount()==0)
            {
                Toast.makeText(getBaseContext(),"No Attendance Info Available",Toast.LENGTH_LONG).show();
            }else
            {
                cursorx.moveToFirst();
                while(!cursorx.isAfterLast())
                {
                    datesALONE.add(cursorx.getString(0));
                    hourALONE.add(cursorx.getInt(1));
                    dates.add(cursorx.getString(0) + ":" + cursorx.getInt(1) + "th Hour");
                    if(cursorx.getInt(3)==1)
                        atts.add(true);
                    else {
                        Log.d("profile",cursorx.getString(0) + " -> " + cursorx.getInt(3));
                        atts.add(false);
                    }
                    cursorx.moveToNext();
                }*/
                //adapter = new profile_adapter(cgpaActivity,editText.getText().toString().toUpperCase());
                //listView.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_class_menu:
                Intent t = new Intent(cgpaActivity,Result_activity.class);
                startActivity(t);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
