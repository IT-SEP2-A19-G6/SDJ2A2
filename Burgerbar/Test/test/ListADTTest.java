package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.persistence.ArrayList;
import server.persistence.ListADT;


import static org.junit.jupiter.api.Assertions.*;

class ListADTTest {

    private ListADT list;

    private final String NAME = "test";


    @BeforeEach
    void setUp() {
        list = new ArrayList();
    }

    @Test
    void add() {

        for (int i = 0; i < 20000; i++) {
            list.add(NAME + i);
        }


    }

    @Test
    void add2() {

        // equivalence partitioning
        list.add(0, 1);
        assertEquals(1, list.get(0));
        list.add(0,2);
        assertEquals(2, list.get(0));

        list = new ArrayList();

        for (int i = 0; i < 51; i++) {
            list.add(0, i);
        }
        assertEquals(50, list.get(0));


        list = new ArrayList();

        for (int i = 0; i < 100; i++) {
            list.add(0, i);
        }
        assertEquals(99, list.get(0));

        list = new ArrayList();

        for (int i = 0; i < 101; i++) {
            list.add(0, i);
        }
        assertEquals(100, list.get(0));

        list = new ArrayList();

        for (int i = 0; i < 102; i++) {
            list.add(0, i);
        }
        assertEquals(101, list.get(0));


        list = new ArrayList();
        for (int i = 0; i < 102; i++) {
            list.add(i, i);
        }

        assertTrue(0 == (Integer) list.get(0));
        assertTrue(1 == (Integer) list.get(1));
        assertTrue(50 == (Integer) list.get(50));
        assertTrue(99 == (Integer) list.get(99));
        assertTrue(100 == (Integer) list.get(100));
        assertTrue(101 == (Integer) list.get(101));


    }


    @Test
    void set() {

        // arrange
        for (int i = 0; i < 100; i++) {
            list.add(NAME + 1);
        }
        // act
        list.set(0, NAME + "SET0");
        list.set(50, NAME + "SET50");
        list.set(99, NAME + "SET99");


        // assert

        assertThrows(IndexOutOfBoundsException.class, ()-> list.set(-99, NAME));
        assertThrows(IndexOutOfBoundsException.class, ()-> list.set(-1, NAME));

        assertTrue((NAME + "SET0").equals(list.get(0)));
        assertTrue((NAME + "SET50").equals(list.get(50)));
        assertTrue((NAME + "SET99").equals(list.get(99)));

        assertThrows(IndexOutOfBoundsException.class, ()-> list.set(100, NAME + "SET100"));
        assertThrows(IndexOutOfBoundsException.class, ()-> list.set(101, NAME + "SET101"));



    }

    @Test
    void get() {

        // boundary testing

        // arrange
        for (int i = 0; i < 1001; i++) {
            list.add(NAME + i);
        }
        // act
        // assert
        assertThrows(IndexOutOfBoundsException.class, ()-> list.get(-99));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
        assertEquals(NAME + 0,list.get(0));
        assertEquals(NAME + 1,list.get(1));
        assertEquals(NAME + 500,list.get(500));
        assertEquals(NAME + 999,list.get(999));
        assertEquals(NAME + 1000,list.get(1000));
        assertThrows(IndexOutOfBoundsException.class, ()-> list.get(1001));
    }

    @Test
    void remove() {

        // remove content

        // low
        // arrange
        // act
        assertThrows(IllegalStateException.class, () -> list.remove("ItemDoesNotExist"));
        // assert

        // norminal
        // arrange
        list = new ArrayList();
        // act
        // assert

        // high
        // arrange
        list = new ArrayList();
        // act
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        // assert
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, list.remove(0));
        }

    }



    @Test
    void indexOf() {
        // arrange
        for (int i = 0; i < 1000; i++) {
            list.add(NAME + i);
        }
        // act
        list.remove(0);
        // assert
        assertEquals(-1, list.indexOf(NAME+0));
        assertTrue(list.indexOf("nonExisting") == -1);
        assertTrue(list.indexOf(NAME+1) == 0);
        assertEquals(1, list.indexOf(NAME+2));
        assertEquals(500, list.indexOf(NAME+501));
        assertEquals(998, list.indexOf(NAME+999));
        assertEquals(-1, list.indexOf(NAME+1000));

    }

    @Test
    void contains() {

        // check that it can find item
        // arrange
        // act
        list.add(NAME);
        // assert
        assertTrue(list.contains(NAME));

        // check that it cant find item
        // arrange
        list = new ArrayList();
        // act
        list.add(NAME);
        // assert
        assertFalse(list.contains("arbitraryName"));

        // add and remove then check
        // arrange
        list = new ArrayList();
        // act
        list.add(NAME);
        list.remove(0);
        // assert
        assertFalse(list.contains(NAME));

    }

    @Test
    void isEmpty() {

        assertTrue(list.isEmpty());


        list.add("1");
        assertTrue(!list.isEmpty());


        list = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 1000; i++) {
            list.remove(0);
        }
        assertTrue(list.isEmpty());


    }

    @Test
    void isFull() {
        // boundary value analysis

        // list expands at 100. so the test will be 0, 1, 99, 100, 101

        // 0
        assertFalse(list.isFull());

        // 1
        list.add(1);
        assertFalse(list.isFull());

        list = new ArrayList();
        //99
        for (int i = 0; i < 99; i++) {
            list.add(i);
        }
        assertFalse(list.isFull());

        // 100
        list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertTrue(!list.isFull());

        // 101
        list = new ArrayList();
        for (int i = 0; i < 101; i++) {
            list.add(i);
        }
        assertTrue(!list.isFull());


    }

    @Test
    void size() {

        assertEquals(0, list.size());

        // arrange

        // act
        list.add(NAME+1);
        list.add(NAME+2);

        // assert
        assertEquals(2, list.size());

        // arrange
        list = new ArrayList();
        // act
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        // assert

        assertEquals(50, list.size());

        // arrange
        list = new ArrayList();
        // act
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        // assert
        assertEquals(100, list.size());



        // arrange
        list = new ArrayList();
        // act
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        // assert
        assertEquals(1000, list.size());
    }


    @Test
    void testToString() {
        list.add(1);
        list.add("2");
        list.add(3);

        assertEquals("{1, 2, 3}", list.toString());
    }




}