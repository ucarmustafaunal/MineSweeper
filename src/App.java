import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Satır sayısını girin: ");
        int rows = scanner.nextInt();
        System.out.print("Sütun sayısını girin: ");
        int columns = scanner.nextInt();
        int totalMines = rows * columns / 4; // Mayın sayısı, elemanSayisi / 4
        MineSweeper game = new MineSweeper(rows, columns, totalMines);
        game.play();
        scanner.close();
    }
}