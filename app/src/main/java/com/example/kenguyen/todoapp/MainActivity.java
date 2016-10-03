package com.example.kenguyen.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditWorkingActivity editActivity;
    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PopulateArrayItems();
        lvItems = (ListView)findViewById(R.id.lvItems);

        lvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText)findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick (AdapterView<?> parent, View view, int position, long id){

                String text = lvItems.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this,EditWorkingActivity.class);
                intent.putExtra("EditText", text);
                intent.putExtra("PosEdit", position);
               // startActivity(intent);
                startActivityForResult(intent, 35);

            }

        });
    }
    public void PopulateArrayItems(){
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
    }
    public void onAddItem(View view){

        if(etEditText!=null){

            aTodoAdapter.add(etEditText.getText().toString());
            etEditText.setText("");
            writeItems();
        }

    }

    private  void readItems(){
        File filesDir = getFilesDir();
        File file = new  File(filesDir,"todo.txt");
        try{
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch(IOException e){
            todoItems = new ArrayList<String>();

        }
    }

    private  void writeItems(){
        File filesDir = getFilesDir();
        File file = new  File(filesDir,"todo.txt");
        try{
           FileUtils.writeLines(file,todoItems);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 35 && resultCode == RESULT_OK){
            String mEditTest = data.getStringExtra("SaveText");
            int posEdit = data.getIntExtra("posEdit",0);
            todoItems.set(posEdit,mEditTest);
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
