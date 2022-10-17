# sudoku
This simple program generates a valid sudoku puzzle of variable size and prints displays it in the console.

The program lets the user solve the puzzle by continuously taking user input and filling in the grid.

The user has the option to autosolve the puzzle at any time.

The solver function is implemented naively, therefore grids of 5x5 and larger are not included, because generation and resolution of a such problem may take more time than desired.

For example 1 out of every 20 generation of 4x4 grid may take more than 30 seconds, if the program does not load, forcefully exit the program and launch again.

