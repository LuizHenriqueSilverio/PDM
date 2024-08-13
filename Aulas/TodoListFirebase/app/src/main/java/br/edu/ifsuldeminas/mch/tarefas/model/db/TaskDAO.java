package br.edu.ifsuldeminas.mch.tarefas.model.db;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.tarefas.model.Task;

public class TaskDAO {

    public boolean save(Task task){
        return true;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<Task>();
        return tasks;
    }

    public void delete(Task task) {

    }

    public void update(Task task) {

    }
}
