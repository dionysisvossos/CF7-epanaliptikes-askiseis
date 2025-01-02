package gr.aueb.cf.rev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CharacterFrequency {
    public static void main(String[] args) {
        File filename = new File("yourfile.txt");  // Αντικαταστήστε με το όνομα του αρχείου σας
        int[][] charArray = new int[128][2];

        // Αρχικοποίηση πίνακα
        for (int i = 0; i < 128; i++) {
            charArray[i][0] = i;
            charArray[i][1] = 0;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int c;
            while ((c = br.read()) != -1) {
                if (Character.isLetter(c)) {
                    charArray[c][1]++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Φιλτράρισμα και ταξινόμηση χαρακτήρων
        int[][] filteredChars = Arrays.stream(charArray)
                .filter(entry -> entry[1] > 0)
                .toArray(int[][]::new);

        // Ταξινόμηση ανά χαρακτήρα
        Arrays.sort(filteredChars, (a, b) -> Character.compare((char) a[0], (char) b[0]));
        System.out.println("Ταξινόμηση ανά χαρακτήρα:");
        for (int[] entry : filteredChars) {
            System.out.printf("Χαρακτήρας: %c, Συχνότητα: %d%n", (char) entry[0], entry[1]);
        }

        // Ταξινόμηση ανά συχνότητα εμφάνισης
        Arrays.sort(filteredChars, (a, b) -> Integer.compare(b[1], a[1]));
        System.out.println("\nΤαξινόμηση ανά συχνότητα εμφάνισης:");
        for (int[] entry : filteredChars) {
            System.out.printf("Χαρακτήρας: %c, Συχνότητα: %d%n", (char) entry[0], entry[1]);
        }
    }
}

