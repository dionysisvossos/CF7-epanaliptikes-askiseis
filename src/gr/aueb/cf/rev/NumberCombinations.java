package gr.aueb.cf.rev;


import java.io.*;
import java.util.*;

public class NumberCombinations {

    public static void main(String[] args) throws FileNotFoundException{
        File inputFileName = new File("C:/Users/Dennis/Documents/Coding Factory 7/Java/numbers.txt");
        File outputFileName = new File("C:/Users/Dennis/Documents/Coding Factory 7/Java/filtered_combinations.txt");
        ArrayList<Integer> numbers = new ArrayList<>();

        // Διαβάζουμε τους αριθμούς από το αρχείο
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                if (number >= 1 && number <= 49) {
                    numbers.add(number);
                } else {
                    System.out.println("Ο αριθμός " + number + " είναι εκτός των επιτρεπόμενων ορίων (1-49).");
                }
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
        }

        // Έλεγχος αν ο αριθμός των αριθμών είναι εντός των επιτρεπόμενων ορίων
        if (numbers.size() <= 6 || numbers.size() > 49) {
            System.out.println("Ο αριθμός των αριθμών στο αρχείο δεν πληροί τις προϋποθέσεις (περισσότεροι από 6 και το πολύ 49 αριθμοί).");
            return;
        }

        // Ταξινόμηση των αριθμών
        Collections.sort(numbers);

        // Δημιουργία όλων των δυνατών εξάδων
        List<int[]> combinations = new ArrayList<>();
        generateCombinations(numbers, new int[6], 0, 0, combinations);

        // Φιλτράρισμα των εξάδων
        List<int[]> filteredCombinations = new ArrayList<>();
        for (int[] combination : combinations) {
            if (isValidCombination(combination)) {
                filteredCombinations.add(combination);
            }
        }

        // Εγγραφή των τελικών εξάδων σε αρχείο
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (int[] combination : filteredCombinations) {
                bw.write(Arrays.toString(combination));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την εγγραφή του αρχείου: " + e.getMessage());
        }

        System.out.println("Οι τελικές εξάδες έχουν γραφτεί στο αρχείο " + outputFileName);
    }

    // Μέθοδος για τη δημιουργία όλων των δυνατών εξάδων
    private static void generateCombinations(List<Integer> numbers, int[] combination, int start, int index, List<int[]> combinations) {
        if (index == 6) {
            combinations.add(combination.clone());
            return;
        }
        for (int i = start; i < numbers.size(); i++) {
            combination[index] = numbers.get(i);
            generateCombinations(numbers, combination, i + 1, index + 1, combinations);
        }
    }

    // Μέθοδος για τον έλεγχο αν μια εξάδα πληροί τα κριτήρια
    private static boolean isValidCombination(int[] combination) {
        int evenCount = 0, oddCount = 0, consecutiveCount = 1, sameEndingCount = 0, sameDecadeCount = 0;
        int[] endings = new int[10];
        int[] decades = new int[5];

        for (int i = 0; i < combination.length; i++) {
            int num = combination[i];
            if (num % 2 == 0) evenCount++;
            else oddCount++;

            if (i > 0 && combination[i] == combination[i - 1] + 1) {
                consecutiveCount++;
                if (consecutiveCount > 2) return false; // More than 2 consecutive numbers
            } else {
                consecutiveCount = 1; // Reset consecutive count
            }

            endings[num % 10]++;
            decades[num / 10]++;
        }

        for (int count : endings) if (count > 3) sameEndingCount++;
        for (int count : decades) if (count > 3) return false; // More than 3 numbers in the same decade

        return evenCount <= 4 && oddCount <= 4 && sameEndingCount <= 3;
    }

    private static void writeCombinationsToFile(List<int[]> combinations, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int[] combination : combinations) {
                bw.write(Arrays.toString(combination));
                bw.newLine();
            }
        }
    }
}


