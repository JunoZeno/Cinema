package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int columns = scanner.nextInt();

        String [][] array = new String[rows][columns];
        createCinemaDiagram(array,rows,columns);
        int choice;
        int currentIncome = 0;
        int purchasedTickets = 0;

        do {
            String text = """
                    
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    >\s""";

            System.out.print(text);

            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("%nCinema:");
                    printColumnNumbers(columns);
                    printArray(array);
                    System.out.println();
                }
                case 2 -> {
                    boolean ticketPurchased = false;
                    while(!ticketPurchased) {
                        System.out.println("\nEnter a row number:");
                        System.out.print("> ");
                        int rowNum = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        System.out.print("> ");
                        int seatNum = scanner.nextInt();
                        int ticketPrice = reserveSeat(array, rowNum, seatNum, rows, columns);
                        purchasedTickets += ticketPrice > 0 ? 1 : 0;
                        ticketPurchased = ticketPrice > 0;
                        currentIncome += ticketPrice;
                    }

                }
                case 3 -> {
                    System.out.printf("%nNumber of purchased tickets: %d%n",
                            purchasedTickets);
                    System.out.printf("Percentage: %.2f%%%n",
                            ((double) purchasedTickets / (rows * columns)) * 100);
                    System.out.printf("Current income: $%d%n", currentIncome);
                    System.out.printf("Total income: $%d%n", totalAmount(rows,
                            columns));
                }
            }
        } while (choice != 0);

    }

    public static void printColumnNumbers (int columns) {
        // Loop to make first line
        for(int k = 0; k <= columns; k++){
            if (k == 0){
                System.out.print("  ");
            } else {
                System.out.print(k + " ");
            }
        }
    }

    public static void printArray (String[][] array) {
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void createCinemaDiagram (String[][] array, int rows,
                                       int columns) {

        for (int i = 1; i <= rows; i++) {
//            System.out.print(i + " ");
            for (int j = 1; j <= columns; j++) {
                array[i - 1][j - 1] = "S";
            }
        }
    }

    public static int reserveSeat (String[][] array,
                                   int rowNumSelected,
                                   int seatNumSelected, int rows, int columns) {
        int ticketPrice = ticketPrice(rows, columns, rowNumSelected);
        if (rowNumSelected > rows || seatNumSelected > columns) {
            System.out.println("\nWrong Input!");
            return 0;
        } else if (array[rowNumSelected - 1][seatNumSelected - 1] == "B") {
            System.out.println("\nThat ticket has already been purchased!");
            return 0;
        } else {
            array[rowNumSelected - 1][seatNumSelected - 1] = "B";
            System.out.println("\nTicket Price: $" + ticketPrice);
            return ticketPrice;
        }

    }

    public static int ticketPrice (int rows, int columns, int rowNumSelected) {
        int frontRows = 0;
        if ((rows * columns) <= 60) {
            return 10;
        } else if ((rows * columns) > 60 && rows % 2 != 0) {
            frontRows = (rows - 1) / 2;
        }
        if (rowNumSelected <= frontRows) return 10;
        else return 8;
    }


    public static int totalAmount(int rows, int columns) {
        int total = 0;

        if ((rows * columns) <= 60) {
            return rows * columns * 10;
        } else if ((rows * columns) > 60 && rows % 2 != 0) {
            int frontRows = (rows - 1) / 2;
            int backRows = (rows + 1) / 2;
            return (frontRows * columns * 10) +
                    (backRows * columns * 8);
        } else {
            return ((rows / 2) * columns * 10) +
                    ((rows / 2) * columns * 8);
        }
    }
}