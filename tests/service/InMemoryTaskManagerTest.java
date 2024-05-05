package service;

import components.Epic;
import components.Status;
import components.Subtask;
import components.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private final TaskManager manager = Managers.getDefault();

    @Test
    public void getId() {
        int id = manager.getId();
        assertEquals(1, id);
    }

    @Test
    public void getAllTask() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        Task task2 = new Task("Задача2", "Пусто");
        manager.addTask(task2);

        assertEquals(2, manager.getAllTask().size());
        assertEquals(task1, manager.getAllTask().get(0));
        assertEquals(task2, manager.getAllTask().get(1));
    }

    @Test
    public void getAllSubtask() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        assertEquals(2, manager.getAllSubtask().size());
        assertEquals(subtask1, manager.getAllSubtask().get(0));
        assertEquals(subtask2, manager.getAllSubtask().get(1));
    }

    @Test
    public void getAllEpic() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Epic epic2 = new Epic("Эпик2", "Пусто");
        manager.addEpic(epic2);

        assertEquals(2, manager.getAllEpic().size());
        assertEquals(epic1, manager.getAllEpic().get(0));
        assertEquals(epic2, manager.getAllEpic().get(1));
    }

    @Test
    public void removeAllTasks() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        Task task2 = new Task("Задача2", "Пусто");
        manager.addTask(task2);

        manager.removeAllTasks();

        assertEquals(0, manager.getAllTask().size());
    }

    @Test
    public void removeAllSubtasks() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        manager.removeAllSubtasks();

        assertEquals(0, manager.getAllSubtask().size());
    }

    @Test
    public void removeAllEpics() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        manager.removeAllEpics();

        assertEquals(0, manager.getAllEpic().size());
    }

    @Test
    public void getAllTasksById() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        Task task2 = new Task("Задача2", "Пусто");
        manager.addTask(task2);

        assertEquals(task1, manager.getAllTasksById(1));
        assertEquals(task2, manager.getAllTasksById(2));
    }

    @Test
    public void getAllSubtasksById() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        assertEquals(subtask1, manager.getAllSubtasksById(2));
        assertEquals(subtask2, manager.getAllSubtasksById(3));
    }

    @Test
    public void getAllEpicsById() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        assertEquals(epic1, manager.getAllEpicsById(1));
    }

    @Test
    public void addTask() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        assertEquals(1, manager.getAllTask().size());
        assertEquals(task1, manager.getAllTask().getFirst());
    }

    @Test
    public void addSubtask() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        assertEquals(2, manager.getAllSubtask().size());
        assertEquals(subtask1, manager.getAllSubtask().get(0));
        assertEquals(subtask2, manager.getAllSubtask().get(1));
        assertEquals(epic1.getId(), subtask1.getIdEpic());
        assertEquals(epic1.getId(), subtask2.getIdEpic());
    }

    @Test
    public void addEpic() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        assertEquals(1, manager.getAllEpic().size());
        assertEquals(epic1, manager.getAllEpic().getFirst());
    }

    @Test
    public void updateTask() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        task1.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task1);

        assertEquals(1, manager.getAllTask().size());
        assertEquals(task1, manager.getAllTask().getFirst());
    }

    @Test
    public void updateSubtask() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        subtask1.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask1);

        assertEquals(1, manager.getAllSubtask().size());
        assertEquals(subtask1, manager.getAllSubtask().getFirst());
    }

    @Test
    public void updateEpic() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        assertEquals(Status.NEW, epic1.getStatus());

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        assertEquals(Status.NEW, epic1.getStatus());

        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);
        manager.updateEpic(epic1);

        assertEquals(Status.DONE, epic1.getStatus());

        subtask1.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask1);
        manager.updateEpic(epic1);

        assertEquals(Status.IN_PROGRESS, epic1.getStatus());
    }

    @Test
    public void removeTaskById() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);

        Task task2 = new Task("Задача2", "Пусто");
        manager.addTask(task2);

        assertEquals(2, manager.getAllTask().size());

        manager.removeTaskById(task1.getId());

        assertEquals(1, manager.getAllTask().size());
        assertEquals(task2, manager.getAllTask().getFirst());
    }

    @Test
    public void removeSubtaskById() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        assertEquals(2, manager.getAllSubtask().size());

        manager.removeSubtaskById(subtask1.getId());

        assertEquals(1, manager.getAllSubtask().size());
        assertEquals(subtask2, manager.getAllSubtask().getFirst());
    }

    @Test
    public void removeEpicById() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        assertEquals(subtask1, epic1.getSubtasks().getFirst());
        assertEquals(1, manager.getAllEpic().size());

        manager.removeEpicById(epic1.getId());

        assertEquals(0, manager.getAllEpic().size());
    }

    @Test
    public void getSubtasksByIdEpic() {
        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача2", "Пусто", epic1.getId());
        manager.addSubtask(subtask2);

        ArrayList<Subtask> subtasks = manager.getSubtasksByIdEpic(epic1);
        assertEquals(epic1.getSubtasks(), subtasks);
    }

    @Test
    public void getHistoryTasks() {
        Task task1 = new Task("Задача1", "Пусто");
        manager.addTask(task1);
        manager.getAllTasksById(1);

        Epic epic1 = new Epic("Эпик1", "Пусто");
        manager.addEpic(epic1);
        manager.getAllEpicsById(2);

        Subtask subtask1 = new Subtask("Подзадача1", "Пусто", epic1.getId());
        manager.addSubtask(subtask1);
        manager.getAllSubtasksById(3);

        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(task1);
        tasks1.add(epic1);
        tasks1.add(subtask1);

        ArrayList<Task> tasks2 = manager.getHistoryTasks();

        assertEquals(tasks2, tasks1);

    }

}