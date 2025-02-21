package Asm02_DBA;

import java.util.*;
import java.io.*;

public class Asm02_DBA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("+------------------ Menu -----------------+");
            System.out.println("|      1. Manual Input                    |");
            System.out.println("|      2. File Input                      |");
            System.out.println("|      3. Bubble Sort                     |");
            System.out.println("|      4. Selection Sort                  |");
            System.out.println("|      5. Insertion Sort                  |");
            System.out.println("|      6. Search > value                  |");
            System.out.println("|      7. Search = value                  |");
            System.out.println("|      0. Exit                            |");
            System.out.println("+-----------------------------------------+");
            System.out.print("Please select an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Manual input
                    Functionality.manualInput();
                    break;
                case 2:
                    // File input
                    Functionality.fileInput();
                    break;
                case 3:
                    // Bubble Sort
                    Functionality.bubbleSort();
                    break;
                case 4:
                    // Selection Sort
                    Functionality.selectionSort();
                    break;
                case 5:
                    // Insertion Sort
                    Functionality.insertionSort();
                    break;
                case 6:
                    // Search > value
                    Functionality.searchGreaterThan();
                    break;
                case 7:
                    // Search = value
                    Functionality.searchEqual();
                    break;
                case 0:
                    System.out.println("Thanks!!!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}

