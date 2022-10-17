package Main;

import java.util.Random;
public class Sudoku
{
    public int size;	  // size of a row & column
    public int rootSize; // size of a "square"
    int [][] puzzle;
    public int [][] solution;
    public boolean finishedCheck ( int [][] b )
    {
        //check for 0's
        for ( int i = 0; i < size; i++ )
            for ( int j = 0; j < size; j++ )
                if ( b[i][j] == 0 )
                    return false;
        return true;

    }
    public void generate ( )
    {
        /*
         * generate random first row. use solver() to fill up a solution
         * randomly display a ~1/2 of numbers
         */
        puzzle = new int [size][size];
        solution = new int [size][size];
        Random ran = new Random();
        for ( int i = 0; i < size; i++ )
            for ( int j = 0; j < size; j++ )
                solution[i][j] = 0;

        int j = 0;
        while ( j != size )
        {
            int s;
            s = ran.nextInt(size+1);
            if ( check (solution, 0, j, s) )
            {
                solution[0][j] = s;
                j++;
            }
        }
        solver( solution );
        for ( int i = 0; i < size; i++ )
            for ( int k = 0; k < size; k++ )
                if ( ran.nextInt(size+1) % 2 == 0 )
                    puzzle[i][k] = solution[i][k];

    }

    public boolean check ( int[][] b, int row, int column, int guess )
    {
        //check the validity of a guess, column, row, square uniqueness
        if ( guess > size )
            return false;
        for ( int i = 0; i < size; i++ )
            if ( b[row][i] == guess )
                return false;
        for ( int i = 0; i < size ; i++ )
            if ( b[i][column] == guess )
                return false;
        int rowIndex = row - ( row % rootSize );
        int colIndex = column - ( column % rootSize );
        for ( int i = rowIndex; i < rowIndex + rootSize ; ++i )
            for ( int j = colIndex; j < colIndex + rootSize; ++j )
                if ( b[i][j] == guess )
                    return false;
        return true;

    }

    public boolean solver( int[][] b ) /* seperate class */
    {

        int row = -1;
        int column = -1;
        boolean empty = false;

        //find unfilled spot
        for ( int i = 0; i < size; i++ )
        {
            for ( int j = 0; j < size; j++ )
            {
                if ( b[i][j] == 0 )
                {
                    row = i;
                    column = j;
                    empty = true;
                    break;
                }
            }
            if ( empty )
                break;
        }

        // solved, stop recursion
        if ( !empty )
            return true;

        //recursion
        for ( int i = 1; i <= size; i++ )
        {
            if ( check ( b, row, column, i ) )
            {
                b[row][column] = i;
                if ( solver ( b ) )
                    return true;
                else
                    b[row][column] = 0;

            }
        }
        //return false if a given sudoku is unsolvable
        //shouldn't happen too often
        return false;
    }
    public void display ( )
    {
        //prints a nice formatted sudoku array in terminal
        System.out.print("     ");
        int u = 0, v = 0;
        boolean tru = false;
        if ( rootSize == 2 )
            v = 1;
        else if ( rootSize == 4 )
            v = -1;
        for ( int i = 0; i < size*3 - 1; i++)
            if ( i % ( rootSize + v) == 0 )
            {
                System.out.print(++u);
                if ( u >= 10 )
                    tru = true;
            }
            else if ( tru )
                tru = false;
            else
                System.out.print(" ");
        System.out.println(" <-- ROWS");
        for ( int i = 0; i < size; i++)
        {
            if ( i % rootSize == 0 )
            {
                System.out.print("   ");
                for ( int a = 0; a <= size*3 ; ++a )
                    System.out.print("=");
                System.out.println();
            }
            System.out.print(i+1);
            if ( i+1 >= 10 )
                System.out.print(" ");
            else
                System.out.print("  ");
            for (int j = 0; j < size; j++)
            {
                if ( j % rootSize == 0 )
                    System.out.print("|");
                else
                    System.out.print(" ");
                if ( puzzle[i][j] == 0 )
                    System.out.print(" *");
                else
                {
                    if ( puzzle[i][j] < 10 )
                        System.out.print(" ");
                    System.out.print(puzzle[i][j]) ;
                }
            }
            System.out.print("|\n");
        }
        System.out.print("   ");
        for ( int a = 0; a <= size *3 ; ++a )
            System.out.print("=");
        System.out.println("\nA\n|\n|\nCOLUMNS\n");
    }
}
