package service;

import components.Epic;
import components.Subtask;
import components.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();
    TaskManager manager = Managers.getDefault();

    @Test
    public void addTask() {
        Task task1 = new Task("name1", "description1");
        manager.addTask(task1);
        historyManager.add(task1);
        Task task2 = new Task("name2", "description2");
        manager.addTask(task2);
        historyManager.add(task2);

        assertEquals(historyManager.getHistory().size(), 2);
        assertEquals(historyManager.getHistory().get(0), task1);
        assertEquals(historyManager.getHistory().get(1), task2);
    }

    @Test
    public void addEpic() {
        Epic epic = new Epic("name1", "description1");
        manager.addEpic(epic);
        historyManager.add(epic);

        assertEquals(historyManager.getHistory().size(), 1);
        assertEquals(historyManager.getHistory().getFirst(), epic);
    }

    @Test
    public void addSubtask() {
        Subtask subtask1 = new Subtask("name1", "description1", 1);
        manager.addSubtask(subtask1);
        historyManager.add(subtask1);

        Subtask subtask2 = new Subtask("name2", "description2", 1);
        manager.addSubtask(subtask2);
        historyManager.add(subtask2);

        assertEquals(historyManager.getHistory().size(), 2);
        assertEquals(historyManager.getHistory().getFirst(), subtask1);
        assertEquals(historyManager.getHistory().getLast(), subtask2);
    }

    @Test
    public void getHistory() {
        Task task1 = new Task("name1", "description1");
        manager.addTask(task1);
        historyManager.add(task1);

        Task task2 = new Task("name2", "description2");
        manager.addTask(task2);
        historyManager.add(task2);

        Epic epic = new Epic("name3", "description3");
        manager.addEpic(epic);
        historyManager.add(epic);

        Subtask subtask1 = new Subtask("name1", "description1", 1);
        manager.addSubtask(subtask1);
        historyManager.add(subtask1);

        assertEquals(historyManager.getHistory().size(), 4);
        assertEquals(historyManager.getHistory().get(0), task1);
        assertEquals(historyManager.getHistory().get(1), task2);
        assertEquals(historyManager.getHistory().get(2), epic);
        assertEquals(historyManager.getHistory().getLast(), subtask1);
    }
}