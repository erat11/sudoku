package Test;
import Main.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    @Test
    void completionTest()
    {
        //test if sudoku gets generated & solved
        Sudoku a1 = new Sudoku();
        a1.rootSize = 2;
        a1.size = 4;
        a1.generate();
        assertTrue(a1.finishedCheck( a1.solution ));
        Sudoku a2 = new Sudoku();
        a2.rootSize = 3;
        a2.size = 9;
        a2.generate();
        assertTrue(a1.finishedCheck( a2.solution ));
        Sudoku a3= new Sudoku();
        a3.rootSize = 4;
        a3.size = 16;
        a3.generate();
        assertTrue(a3.finishedCheck( a3.solution ));
    }
}