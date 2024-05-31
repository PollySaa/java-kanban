package service;

import components.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private final TaskManager manager = Managers.getDefault();
    private Task task1;
    private Task task2;

    @BeforeEach
    public void beforeEach() {
        task1 = new Task("Задача 1", "Пусто");
        manager.addTask(task1);

        task2 = new Task("Задача 2", "Пусто");
        manager.addTask(task2);
    }

    @Test
    void testAdd_WhenTaskNotExists() {
        assertFalse(historyManager.getHistory().contains(task1));
        historyManager.add(task1);
        assertTrue(historyManager.getHistory().contains(task1));
    }

    @Test
    void testAdd_WhenTaskExists() {
        historyManager.add(task1);
        assertTrue(historyManager.getHistory().contains(task1));
        historyManager.add(task1);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        assertEquals(tasks, historyManager.getHistory());
    }

    @Test
    void testRemove_WhenTaskExists() {
        historyManager.add(task1);
        assertTrue(historyManager.getHistory().contains(task1));
        historyManager.remove(task1.getId());
        assertFalse(historyManager.getHistory().contains(task1));
    }

    @Test
    void testRemove_WhenTaskNotExists() {
        assertFalse(historyManager.getHistory().contains(task1));
        historyManager.remove(task1.getId());
        assertFalse(historyManager.getHistory().contains(task1));
    }

    @Test
    void testGetHistory_WhenEmpty() {
        assertTrue(historyManager.getHistory().isEmpty());
    }

    @Test
    void testGetHistory_WhenNotEmpty() {
        historyManager.add(task1);
        assertFalse(historyManager.getHistory().isEmpty());
    }

    @Test
    void testLinkLast_WhenEmpty() {
        historyManager.linkLast(task1);
        assertEquals(task1, historyManager.getHistory().getFirst());
    }

    @Test
    void testLinkLast_WhenNotEmpty() {
        historyManager.linkLast(task1);
        historyManager.linkLast(task2);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        assertEquals(tasks, historyManager.getHistory());
    }


}