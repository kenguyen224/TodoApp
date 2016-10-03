package com.example.kenguyen.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditWorkingActivity extends AppCompatActivity {
    EditText mEditText;
    int posEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_working);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mEditText = (EditText)findViewById(R.id.etMultiEditText);
        Intent etIntent = getIntent();
        Bundle etbundle = etIntent.getExtras();
        if(etbundle!=null){
            String eS = (String)etbundle.getString("EditText").toString();
            posEdit = (int)etbundle.getInt("PosEdit");
            mEditText.setText(eS);
        }
    }
    public void onSave(View view){
        mEditText = (EditText)findViewById(R.id.etMultiEditText);
        Intent data = new Intent();
        data.putExtra("SaveText",mEditText.getText().toString());
        data.putExtra("posEdit",posEdit);
        setResult(RESULT_OK, data);
        this.finish();
    }

}
