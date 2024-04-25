package service;

import components.Epic;
import components.Subtask;
import components.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TaskManager {
    private Integer id = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public Integer getId() {
        return ++id;
    }

    public ArrayList<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.removeAllSubtasks();
            epic.updateStatus();
        }
        subtasks.clear();
    }

    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public Task getAllTasksById(Integer id) {
        return tasks.get(id);
    }

    public Subtask getAllSubtasksById(Integer id) {
        return subtasks.get(id);
    }

    public Epic getAllEpicsById(Integer id) {
        return epics.get(id);
    }

    public void addTask(Task task) {
        task.setId(getId());
        tasks.put(task.getId(), task);
    }

    public void addSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getIdEpic())) {
            subtask.setId(getId());
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getIdEpic());
            epic.addSubtasks(subtask);
        }
    }

    public void addEpic(Epic epic) {
        epic.setId(getId());
        epics.put(epic.getId(), epic);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId()) && epics.containsKey(subtask.getIdEpic())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getIdEpic());
            epic.updateSubtasks(subtask);
            epic.updateStatus();
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            epic.updateStatus();
        }
    }

    public void removeTaskById(Integer id) {
        tasks.remove(id);
    }

    public void removeSubtaskById(Integer id) {
        if (subtasks.containsKey(id)) {
            Epic epic = getAllEpicsById(subtasks.get(id).getIdEpic());
            epic.removeSubtasks(id);
            epic.updateStatus();
            subtasks.remove(id);

        }
    }

    public void removeEpicById(Integer id) {
        if (epics.containsKey(id)) {
            for (Subtask subtask : epics.get(id).getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }

    }

    public ArrayList<Subtask> getSubtasksByIdEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            return new ArrayList<>(epic.getSubtasks());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManager that = (TaskManager) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

