package sg.edu.rp.c346.id19046836.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText editTextAdd;
Button buttonAdd;
Button buttonClear;
Button buttonDelete;
Spinner spinnerTask;
ListView lvTask;


ArrayList<String> taskList;
ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAdd = findViewById(R.id.editTextAdd);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.lvTask);
        buttonDelete = findViewById(R.id.buttonDelete);
        spinnerTask = findViewById(R.id.spinner);

        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1,taskList);
        lvTask.setAdapter((adapter));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAdd.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this,"You don't have a task to add",Toast.LENGTH_SHORT).show();
                    editTextAdd.setText(null);
                }
                else{
                    String task = editTextAdd.getText().toString();
                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    editTextAdd.setText(null);
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskList.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have task to delete",Toast.LENGTH_SHORT).show();
                    editTextAdd.setText(null);

                }
                else{
                    taskList.clear();
                    adapter.notifyDataSetChanged();
                    editTextAdd.setText(null);
                }


            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAdd.getText().toString().equalsIgnoreCase("") ||
                        taskList.size()- 1 < Integer.parseInt(editTextAdd.getText().toString()) ){
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                    editTextAdd.setText(null);
                }
                else{
                    int index = Integer.parseInt(editTextAdd.getText().toString());
                    taskList.remove(index);
                    adapter.notifyDataSetChanged();
                    editTextAdd.setText(null);
                }

            }
        });

        spinnerTask.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0:
                        buttonAdd.setEnabled(true);
                        buttonDelete.setEnabled(false);
                        editTextAdd.setHint("Add a new task");
                        editTextAdd.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        buttonAdd.setEnabled(false);
                        buttonDelete.setEnabled(true);
                        editTextAdd.setHint("Type in the index of the task to be removed");
                        editTextAdd.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });




    }
}
