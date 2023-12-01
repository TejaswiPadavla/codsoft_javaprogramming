import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Arrays to store marks obtained and total marks for each subject
        int[] marks = new int[numSubjects];
        int[] totalMarksArray = new int[numSubjects];

        // Input marks obtained and total marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();

            System.out.print("Enter total marks for subject " + (i + 1) + ": ");
            totalMarksArray[i] = scanner.nextInt();
        }

        // Calculate total marks and total obtained marks
        int totalObtainedMarks = 0;
        int totalMaxMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            totalObtainedMarks += marks[i];
            totalMaxMarks += totalMarksArray[i];
        }

        // Calculate average percentage
        double averagePercentage = ((double) totalObtainedMarks / totalMaxMarks) * 100;

        // Display total obtained marks, total max marks, average percentage
        System.out.println("Total Obtained Marks: " + totalObtainedMarks);
        System.out.println("Total Maximum Marks: " + totalMaxMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");

        // Grade Calculation
        char grade = calculateGrade(averagePercentage);

        // Display the corresponding grade
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    // Function to calculate grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}