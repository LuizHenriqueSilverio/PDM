package br.edu.ifsuldeminas.mch.tarefas.model.db;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    public void loadTasks() {
        firestore
                .collection(COLLECTION)
                .orderBy(DT_CHANGED, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> taskSnap) {
                        if (taskSnap.isSuccessful()) {
                            List<Task> tasks = new ArrayList<>();
                            for (QueryDocumentSnapshot doc: taskSnap.getResult()) {
                               String id = doc.getId();
                               String desc = doc.get(DESC, String.class);
                               Boolean active = doc.get(ACTIVE, Boolean.class);

                               Task task = new Task(id, desc);
                               tasks.add(task);
                            }

                            observer.loadOk(tasks);
                        } else {
                            observer.loadError();
                        }
                    }
                });

    }

    public void delete(Task task) {
        firestore
                .collection(COLLECTION)
                .document(task.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        observer.deleteOk();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        observer.deleteError();
                    }
                });
    }

    public void update(Task task) {
        Map<String, Object> taskAsMap = taskToMap(task);

        firestore
                .collection(COLLECTION)
                .document(task.getId())
                .update(taskAsMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        observer.updateOk();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        observer.updateError();
                    }
                });
    }
}
