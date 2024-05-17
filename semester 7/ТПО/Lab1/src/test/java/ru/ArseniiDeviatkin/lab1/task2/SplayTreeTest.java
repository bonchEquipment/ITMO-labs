package ru.ArseniiDeviatkin.lab1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


class SplayTreeTest {

    private SplayTree tree;

    @BeforeEach
    void setUp() {
        tree = new SplayTree();
    }

    @Test
    void testInsertAndSearch() {
        tree.insert(15);
        assertTrue(tree.search(15));
    }

    @Test
    void testSearchNonExistentElement() {
        assertFalse(tree.search(100));
    }

    @Test
    void testDeleteAndSearch() {
        tree.insert(25);
        tree.delete(25);
        assertFalse(tree.search(25));
    }

    @Test
    void testInOrderTraversal() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        tree.insert(40);


        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(20);
        expected.add(30);
        expected.add(40);

        assertIterableEquals(expected, tree.inOrder());
    }

    @Test
    void testSplayOperation() {
        tree.insert(50);
        tree.search(50);
        assertEquals(50, tree.getRootKey());
    }

    @Test
    void testDeleteRoot() {
        tree.insert(50);
        tree.delete(50);
        assertNull(tree.getRoot());
    }

    @Test
    void testDeleteFromEmptyTree() {
        assertDoesNotThrow(() -> tree.delete(10));
    }

    @Test
    void testInsertDuplicateElement() {
        tree.insert(30);
        tree.insert(30);
        assertEquals(1, tree.getCount(30));
    }

    @Test
    void testInsertionWithTimeout() {
        assertTimeout(Duration.ofSeconds(4), () -> {
            for (int i = 1; i <= 1000000; i++) {
                tree.insert(i);
            }
        });
    }

    @Test
    void testInsertionWithTag() {
        assertDoesNotThrow(() -> {
            for (int i = 1; i <= 10000; i++) {
                tree.insert(i);
            }
        });
    }
}
