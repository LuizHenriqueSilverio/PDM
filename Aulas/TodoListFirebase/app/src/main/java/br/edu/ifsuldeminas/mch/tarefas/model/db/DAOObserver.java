package br.edu.ifsuldeminas.mch.tarefas.model.db;

import java.util.List;

import br.edu.ifsuldeminas.mch.tarefas.model.Task;

public interface DAOObserver {

    default void loadOk (List<Task> tasks) {};
    default void loadError () {};
    default void saveOk () {};
    default void saveError () {};
    default void updateOk () {};
    default void updateError () {};
    default void deleteOk () {};
    default void deleteError () {};
}
