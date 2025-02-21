package Asm02_DBA;

import java.util.*;
import java.io.*;

public class Functionality {
    static List<Float> a = new ArrayList<>();
    static List<Float> b = new ArrayList<>();

    // 1. Manual Input
    public static void manualInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter input number of elements: ");
        int n = sc.nextInt();
        try (PrintWriter writer = new PrintWriter(new File("INPUT.TXT"))) {
            System.out.println("Please enter input elements:");
            for (int i = 0; i < n; i++) {
                float value = sc.nextFloat();
                a.add(value);
                writer.print(value + " ");
            }
            System.out.println("Data has been saved to INPUT.TXT.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving data to file.");
        }
    }

    // 2. File Input
    public static void fileInput() {
        a.clear();
        try (Scanner fileScanner = new Scanner(new File("INPUT.TXT"))) {
            while (fileScanner.hasNextFloat()) {
                a.add(fileScanner.nextFloat());
            }
            System.out.println("Data from file:");
            for (float num : a) {
                System.out.print((int) num + " ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }




    // 3. Bubble Sort
    public static void bubbleSort() {
        b = new ArrayList<>(a);
        boolean swapped;
        try (PrintWriter writer = new PrintWriter(new File("BubbleSortSteps.txt"))) {
            for (int i = 0; i < b.size() - 1; i++) {
                swapped = false;
                for (int j = 0; j < b.size() - i - 1; j++) {
                    if (b.get(j) > b.get(j + 1)) {
                        Collections.swap(b, j, j + 1);
                        swapped = true;
                    }
                }
                System.out.println("Step " + (i + 1) + ": " + b);
                writer.println("Step " + (i + 1) + ": " + b);
                if (!swapped) break;
            }
            System.out.println("Bubble Sort completed.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving Bubble Sort steps.");
        }
    }

    // 4. Selection Sort
    public static void selectionSort() {
        b = new ArrayList<>(a);
        try (PrintWriter writer = new PrintWriter(new File("SelectionSortSteps.txt"))) {
            for (int i = 0; i < b.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < b.size(); j++) {
                    if (b.get(j) < b.get(minIndex)) {
                        minIndex = j;
                    }
                }
                Collections.swap(b, i, minIndex);
                System.out.println("Step " + (i + 1) + ": " + b);
                writer.println("Step " + (i + 1) + ": " + b);
            }
            System.out.println("Selection Sort completed.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving Selection Sort steps.");
        }
    }

    // 5. Insertion Sort
    public static void insertionSort() {
        b = new ArrayList<>(a);
        try (PrintWriter writer = new PrintWriter(new File("InsertionSortSteps.txt"))) {
            for (int i = 1; i < b.size(); i++) {
                float key = b.get(i);
                int j = i - 1;
                while (j >= 0 && b.get(j) > key) {
                    b.set(j + 1, b.get(j));
                    j--;
                }
                b.set(j + 1, key);
                System.out.println("Step " + i + ": " + b);
                writer.println("Step " + i + ": " + b);
            }
            System.out.println("Insertion Sort completed.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving Insertion Sort steps.");
        }
    }

    // 6. Search > value
    public static void searchGreaterThan() {
        Scanner sc = new Scanner(System.in);
        b = new ArrayList<>(a);
        System.out.print("Enter value to search greater than: ");
        float value = sc.nextFloat();
        List<Integer> positions = new ArrayList<>();  // Lưu trữ vị trí các phần tử
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) > value) {
                positions.add(i);  // Lưu vị trí phần tử lớn hơn value
            }
        }
        if (!positions.isEmpty()) {
            System.out.println("Positions of values greater than " + value + ": " + positions);
            try (PrintWriter writer = new PrintWriter(new File("SearchGreaterThanResult.txt"))) {
                writer.println("Positions of values greater than " + value + ": " + positions);
            } catch (FileNotFoundException e) {
                System.out.println("Error saving search results.");
            }
        } else {
            System.out.println("There is no value that satisfies the condition.");
        }
    }


    // 7. Search = value (Binary Search)
    public static void searchEqual() {
        Scanner sc = new Scanner(System.in);
        b = new ArrayList<>(a);
        Collections.sort(b); // Sort before binary search
        System.out.print("Enter value to search: ");
        float value = sc.nextFloat();
        int result = binarySearch(b, value);
        if (result != -1) {
            System.out.println("Found value " + value + " at index " + result);
            try (PrintWriter writer = new PrintWriter(new File("SearchEqualResult.txt"))) {
                writer.println("Found value " + value + " at index " + result);
            } catch (FileNotFoundException e) {
                System.out.println("Error saving search result.");
            }
        } else {
            System.out.println("The value does not exist!");
        }
    }


    // Hàm tìm kiếm nhị phân
    private static int binarySearch(List<Float> list, float value) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == value) {
                return mid;
            }
            if (list.get(mid) < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
