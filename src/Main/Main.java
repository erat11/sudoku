package Main;
import java.util.Scanner;
/*
 * This program generates a valid sudoku puzzle of variable size
 * The program lets the user solve the puzzle by continuously taking
 * user input and filling in the grid
 * The user has the option to autosolve the puzzle at any time
 * The solver function is implemented naively, therefore grids of 5x5 and larger are not included,
 * because generation and resolution of a such problem may take more time than desired
 * For Example ~1 out of every 20 generation of 4x4 grid takes more than 30 seconds
 */
public class Main {
    public static void main(String[] args)
    {

        Sudoku sudo = new Sudoku();
        System.out.print("Input desired size ( 2 - 4 )\n");
        Scanner scn = new Scanner (System.in);
        if ( !scn.hasNextInt() )
        {
            System.out.println("Invalid input, terminating...\n");
            return;
        }
        sudo.rootSize = scn.nextInt(); // size of grid
        if ( sudo.rootSize == 4 )
            System.out.println("\tWARNING!\noption 4 may take up to a minute to create and solve!\n");
        sudo.size = sudo.rootSize* sudo.rootSize; /* length of row and column*/
        if ( sudo.rootSize > 4 || sudo.rootSize < 2 )
        {
            System.out.println("Invalid input, terminating...\n");
            return;
        }
        sudo.generate();
        int guess = 0; /* number of user guesses*/
        while ( true ) /* guess loop */
        {
            if ( sudo.finishedCheck( sudo.puzzle ) ) // if puzzle is completed
            {
                System.out.println("\n\tCongrats! YOU SOLVED IT!!!\n");
                sudo.display ();
                break;
            }
            System.out.print("Puzzle:\n");
            sudo.display ();
            System.out.print("Enter 0 to auto-solve, otherwise enter x(column) y(row) z(guess):  ");
            if ( !scn.hasNextInt() )
            {
                System.out.println("Invalid input, terminating...\n");
                return;
            }
            int i = scn.nextInt(), j, g;
            if ( i == 0 )
            {
                sudo.puzzle = sudo.solution; // solution calculated in generate() and solver()
                System.out.print("\n\tSolution:\n");
                sudo.display ();
                System.out.print("You guessed " + guess + " time(s).\n");
                break;
            }
            else if ( i >= 1 && i <= sudo.size )
            {
                guess++;
                if ( !scn.hasNextInt() )
                {
                    System.out.println("Invalid input...\n");
                    continue;
                }
                j = scn.nextInt();
                if ( !scn.hasNextInt() )
                {
                    System.out.println("Invalid input...\n");
                    continue;
                }
                g = scn.nextInt();
                if ( j < 1 || j > sudo.size || g < 1 || g > sudo.size )
                {
                    System.out.println("Invalid input...\n");
                    continue;
                }
                if ( sudo.puzzle[i-1][j-1] != 0  )
                {
                    System.out.print("\n\tsquare already filled with :" + sudo.puzzle[i-1][j-1] + "\n");
                    continue;
                }
                if ( sudo.puzzle[i-1][j-1] == 0 )
                {
                    if ( sudo.solution[i-1][j-1] == g  )
                    {
                        sudo.puzzle[i-1][j-1] = g; // fill the correct guess in grid
                        if ( sudo.finishedCheck( sudo.puzzle ) )
                        {
                            System.out.println("\n\tCongrats! YOU SOLVED IT!!!");
                            sudo.display ();
                            System.out.print("You guessed " + guess + " time(s).\n");
                            break;
                        }
                    }
                }
                else
                    System.out.println("\n\tInvalid guess");
            }
            else
                System.out.println("\nPlease try again");

        }
    }
}