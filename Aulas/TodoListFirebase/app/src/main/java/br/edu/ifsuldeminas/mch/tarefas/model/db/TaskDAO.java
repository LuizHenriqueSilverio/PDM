package br.edu.ifsuldeminas.mch.tarefas.model.db;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsuldeminas.mch.tarefas.model.Task;

public class TaskDAO {

    private static final String COLLECTION = "tasks";
    private static final String DESC = "description";
    private static final String ACTIVE = "active";
    private static final String DT_CHANGED = "date_changed";

    // Referência do banco remoto
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private DAOObserver observer;

    public TaskDAO(DAOObserver observer) {
        this.observer = observer;
    }

    private Map<String, Object> taskToMap(Task task) {
        Map<String, Object> map = new HashMap<>();

        map.put(DESC, task.getDescription());
        map.put(ACTIVE, task.isActive());
        map.put(DT_CHANGED, task.getDateChanged());

        return map;
    }

    public boolean save(Task task){

        Map<String, Object> taskAsMap = taskToMap(task);

        firestore
                .collection(COLLECTION)
                .add(taskAsMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        observer.saveOk();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        observer.saveError();
                    }
                });

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
