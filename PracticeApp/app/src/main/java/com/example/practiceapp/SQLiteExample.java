package com.example.practiceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteExample extends AppCompatActivity {

    EditText id, name, contact, dob;
    Button insert, view, update, delete;
    DBConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example);

        id = findViewById(R.id.empNo);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.insertbtn);
        view = findViewById(R.id.viewbtn);
        update = findViewById(R.id.updatebtn);
        delete = findViewById(R.id.deletebtn);

        db=new DBConnect(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTxt = id.getText().toString();
                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkInsertData = db.insertUserData(idTxt, nameTxt, contactTxt, dobTxt);
                if(checkInsertData == true)
                    Toast.makeText(SQLiteExample.this, "New Entry Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SQLiteExample.this, "No Entry Inserted", Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTxt = id.getText().toString();
                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkUpdateData = db.updateUserData(idTxt, nameTxt, contactTxt, dobTxt);
                if(checkUpdateData == true)
                    Toast.makeText(SQLiteExample.this, "Existing Entry Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SQLiteExample.this, "No Entry Updated", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTxt = id.getText().toString();
                Boolean checkDeleteData = db.deleteUserData(idTxt);
                if(checkDeleteData == true)
                    Toast.makeText(SQLiteExample.this, "Employee Removed", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SQLiteExample.this, "No Employee Removed", Toast.LENGTH_LONG).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = db.getData();
                if(result.getCount()==0){
                    Toast.makeText(SQLiteExample.this, "No Entry Exists", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("ID : "+ result.getString(0)+"\n");
                    buffer.append("Name : "+ result.getString(1)+"\n");
                    buffer.append("Contact : "+ result.getString(2)+"\n");
                    buffer.append("DoB : "+ result.getString(3)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SQLiteExample.this);
                builder.setCancelable(true);
                builder.setTitle("Employees Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}