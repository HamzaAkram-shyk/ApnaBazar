package com.example.hamzaakram.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

import Data.DatabaseHandler;
import Model.Contact;

public class data_base extends AppCompatActivity {
   Button show;
   EditText ID;
    EditText NAME;
    EditText PHONE;
    Button search;
    DatabaseHandler db;
    String log=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        show=(Button) findViewById(R.id.show);
        ID= (EditText) findViewById(R.id.ID);
        NAME=(EditText) findViewById(R.id.NAME);
        PHONE=(EditText) findViewById(R.id.PHONE);
        search=(Button)findViewById(R.id.search);
         db= new DatabaseHandler(this);
           viewAll();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getAllContacts();
                if(res.getCount()==0){
                    Message("Nothing found");
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Pone:"+res.getString(2)+"\n");
                    }
                    showMessage(buffer.toString());
            }
        });
    }

    public void viewAll(){
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Contact contact= new Contact(NAME.getText().toString(),PHONE.getText().toString());
                String Name=NAME.getText().toString();
                String Phone=PHONE.getText().toString();
                boolean isinserted=db.addContact(Name,Phone);
                if(isinserted==true){
                    Message("Data Added.....");
                } else{
                    Message("Not Found");
                }

                }
        });
    }



    public  void showMessage(String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Data");
        builder.setMessage(Message);
        builder.show();
    }
    public void Message(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
