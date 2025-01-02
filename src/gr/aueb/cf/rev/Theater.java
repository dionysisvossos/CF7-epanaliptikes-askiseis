package gr.aueb.cf.rev;


import java.util.Scanner;

public class Theater {
    private boolean[][] seats;

    public Theater() {
        seats = new boolean[30][12];
    }

    public void book(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;

        if (rowIndex >= 0 && rowIndex < 30 && colIndex >= 0 && colIndex < 12) {
            if (!seats[rowIndex][colIndex]) {
                seats[rowIndex][colIndex] = true;
                System.out.println("Η θέση " + column + row + " κρατήθηκε επιτυχώς.");

            } else {
                seats[rowIndex][colIndex] = true;
                System.out.println("Η θέση " + column + row + " είναι ήδη κρατημένη.");

            }
        }
    }

    public void cancel(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;
        if (!seats[rowIndex][colIndex]) {
            System.out.println("Η θέση " + column + row + " δεν είναι κρατημένη.");
        } else {
            seats[rowIndex][colIndex] = false;
            System.out.println("Η κράτηση της θέσης " + column + row + " ακυρώθηκε επιτυχώς.");
        }
    }

    public void displayAvailableSeats() {
        System.out.println("Available seats (O = available, X = booked):" + " ");
        System.out.print("  ");
        for (char col = 'A'; col <= 'L'; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < 30; row++) {
            System.out.print((row + 1) + (row + 1 < 10 ? " " : ""));
            for (int col = 0; col < 12; col++) {
                System.out.print((seats[row][col] ? "X " : "O "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Εισάγετε εντολή (κράτηση/ακύρωση/προβολή/έξοδος):");
            command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("έξοδος")) {
                break;
            }

            switch (command) {
                case "κράτηση":
                    System.out.println("Εισάγετε θέση προς κράτηση: ");
                    String seatToBook = scanner.nextLine().trim().toUpperCase();
                    char bookColumn = seatToBook.charAt(0);
                    int bookRow = Integer.parseInt(seatToBook.substring(1));
                    theater.book(bookColumn, bookRow);
                    break;
                case "ακύρωση":
                    System.out.println("Εισάγετε κράτηση προς ακύρωση: ");
                    String seatToCancel = scanner.nextLine().trim().toUpperCase();
                    char cancelColumn = seatToCancel.charAt(0);
                    int cancelRow = Integer.parseInt(seatToCancel.substring(1));
                    theater.cancel(cancelColumn, cancelRow);
                    break;
                case "προβολή":
                    theater.displayAvailableSeats();
                    break;
                default:
                    System.out.println("Λάθος εντολή.");
            }
        }
        scanner.close();
    }
}

