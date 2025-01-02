package gr.aueb.cf.rev;

public class MaximumSumSubarray {

//    Ο αλγόριθμος του Kadane είναι ένας γραμμικός αλγόριθμος που βρίσκει το μέγιστο άθροισμα ενός συνεχόμενου υποπίνακα.
//    Η βασική ιδέα είναι να διατρέξουμε τον πίνακα μία φορά και να διατηρούμε δύο μεταβλητές:
//            - `currentMax`: το μέγιστο άθροισμα του υποπίνακα που τελειώνει στη θέση `i`.
//            - `globalMax`: το μέγιστο άθροισμα που έχουμε βρει μέχρι στιγμής.
//
//    Για κάθε στοιχείο του πίνακα, ενημερώνουμε το `currentMax` ως το μέγιστο μεταξύ του τρέχοντος στοιχείου και του αθροίσματος του `currentMax` με το τρέχον στοιχείο.
//    Στη συνέχεια, ενημερώνουμε το `globalMax` αν το `currentMax` είναι μεγαλύτερο από το `globalMax`.
//
//    Αρχικός πίνακας: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
//
//    Βήματα:
//            1. currentMax = -2, globalMax = -2
//            2. currentMax = max(1, -2 + 1) = 1, globalMax = max(-2, 1) = 1
//            3. currentMax = max(-3, 1 + -3) = -2, globalMax = max(1, -2) = 1
//            4. currentMax = max(4, -2 + 4) = 4, globalMax = max(1, 4) = 4
//            5. currentMax = max(-1, 4 + -1) = 3, globalMax = max(4, 3) = 4
//            6. currentMax = max(2, 3 + 2) = 5, globalMax = max(4, 5) = 5
//            7. currentMax = max(1, 5 + 1) = 6, globalMax = max(5, 6) = 6
//            8. currentMax = max(-5, 6 + -5) = 1, globalMax = max(6, 1) = 6
//            9. currentMax = max(4, 1 + 4) = 5, globalMax = max(6, 5) = 6
//
//    Τελικό αποτέλεσμα: 6

    public static int maxSubArraySum(int[] nums) {
        int currentMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            if (currentMax > globalMax) {
                globalMax = currentMax;
            }
        }

        return globalMax;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum sum sub-array: " + maxSubArraySum(nums));
    }
}
//
//
//        ### (γ) Απόδειξη Πολυπλοκότητας Χρόνου
//
//    Ο αλγόριθμος διατρέχει τον πίνακα μία φορά, εκτελώντας σταθερό αριθμό πράξεων για κάθε στοιχείο.
//    Επομένως, η πολυπλοκότητα χρόνου είναι O(n), όπου n είναι το πλήθος των στοιχείων του πίνακα.

