import java.util.Scanner;

public class MineSweeper {
    private int rows;
    private int columns;
    private int totalMines;
    private char[][] gameBoard;
    private char[][] mineBoard;

    public MineSweeper(int rows, int columns, int totalMines) {
        this.rows = rows;
        this.columns = columns;
        this.totalMines = totalMines;
        this.gameBoard = new char[rows][columns];
        this.mineBoard = new char[rows][columns];
        initializeGameBoard();
        placeMines();
        calculateNeighboringMines();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;
        printMineBoard();

        while (true) {
            printGameBoard();

            System.out.print("Enter row and column (example: 1 2): ");
            int selectedRow = scanner.nextInt();
            int selectedColumn = scanner.nextInt();

            if (isValidMove(selectedRow, selectedColumn)) {
                if (mineBoard[selectedRow][selectedColumn] == '*') {
                    System.out.println("You stepped on a mine! You lose.");
                    scanner.close();
                    break;
                } else {
                    uncoverCell(selectedRow, selectedColumn);
                    if (isGameWon()) {
                        printGameBoard();
                        System.out.println("Congratulations! You won.");
                        scanner.close();
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void initializeGameBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int randomRow = (int) (Math.random() * rows);
            int randomColumn = (int) (Math.random() * columns);
            if (mineBoard[randomRow][randomColumn] != '*') {
                mineBoard[randomRow][randomColumn] = '*';
                minesPlaced++;
            }
        }
    }
    private void printMineBoard() {
        System.out.println("Location of mines");
        System.out.print("  ");
        for (int i = 0; i < columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < columns; j++) {
                if(mineBoard[i][j]!='*'){
                    System.out.print("-"+" ");
                }
                else System.out.print(mineBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void calculateNeighboringMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mineBoard[i][j] != '*') {
                    int count = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (i + k >= 0 && i + k < rows && j + l >= 0 && j + l < columns && mineBoard[i + k][j + l] == '*') {
                                count++;
                            }
                        }
                    }
                    mineBoard[i][j] = (char) (count + '0');
                }
            }
        }
    }

    private boolean isValidMove(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns && gameBoard[row][column] == '-';
    }

    private void uncoverCell(int row, int column) {
        if (mineBoard[row][column] == '0') {
            gameBoard[row][column] = ' ';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (row + i >= 0 && row + i < rows && column + j >= 0 && column + j < columns && gameBoard[row + i][column + j] == '-') {
                        uncoverCell(row + i, column + j);
                    }
                }
            }
        } else {
            gameBoard[row][column] = mineBoard[row][column];
        }
    }

    private boolean isGameWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j] == '-' && mineBoard[i][j] != '*') {
                    return false;
                }
            }
        }
        return true;
    }

    private void printGameBoard() {
        System.out.println("Welcome to the MineSweeper Game");
        System.out.print("  ");
        for (int i = 0; i < columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < columns; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}