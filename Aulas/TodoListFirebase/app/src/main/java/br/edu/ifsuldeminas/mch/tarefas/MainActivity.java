package br.edu.ifsuldeminas.mch.tarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsuldeminas.mch.tarefas.model.Task;
import br.edu.ifsuldeminas.mch.tarefas.model.db.TaskDAO;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = findViewById(R.id.todo_list);
        registerForContextMenu(todoList);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForm = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intentForm);
            }
        });

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Task task = (Task) todoList.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("tarefa", task);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTasks();
    }

    private void updateTasks() {
        TaskDAO dao = new TaskDAO(this);
        List<Task> taskList = dao.loadTasks();

        ArrayAdapter<Task> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        todoList.setAdapter(arrayAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem itemDelete = menu.add("Deletar tarefa");
        MenuItem itemFinish = menu.add("Finalizar tarefa");
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Task task = (Task) todoList.getItemAtPosition(itemClicado.position);

                TaskDAO dao = new TaskDAO(MainActivity.this);
                dao.delete(task);
                updateTasks();

                Toast toast = Toast.makeText(MainActivity.this, "Tarefa excluída com sucesso!", Toast.LENGTH_LONG);
                toast.show();

                return true;
            }
        });
    }
}