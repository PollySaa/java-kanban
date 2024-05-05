package service;

import components.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private final ArrayList<Task> historyTasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (historyTasks.size() > 10) {
            historyTasks.removeFirst();
        }
        historyTasks.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(historyTasks);
    }
}
