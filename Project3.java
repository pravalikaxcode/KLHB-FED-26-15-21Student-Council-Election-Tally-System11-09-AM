
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Project3 {

    static Scanner sc = new Scanner(System.in);

    // 1. Input Module: Enter candidate details
    static void enterCandidateDetails(String[] names) {

        System.out.println("\n--- Enter Candidate Details ---");

        for (int i = 0; i < names.length; i++) {
            System.out.print("Enter candidate " + (i + 1) + " name: ");
            names[i] = sc.nextLine().trim();

            while (names[i].isEmpty()) {
                System.out.print("Name cannot be empty. Enter again: ");
                names[i] = sc.nextLine().trim();
            }
        }
    }

    // 2. Input Module: Enter and validate votes
    static void enterVotes(String[] names, int[] votes) {

        System.out.println("\n--- Enter Voting Information ---");

        for (int i = 0; i < names.length; i++) {

            while (true) {
                System.out.print("Enter votes for " + names[i] + ": ");

                if (sc.hasNextInt()) {
                    int count = sc.nextInt();

                    if (count >= 0) {
                        votes[i] = count;
                        break;
                    } else {
                        System.out.println(
                            "Invalid input! Votes cannot be negative."
                        );
                    }
                } else {
                    System.out.println(
                        "Invalid input! Enter a whole number."
                    );
                    sc.next();
                }
            }
        }

        sc.nextLine();
    }

    // 3. Processing Module: Calculate total votes
    static int calculateTotalVotes(int[] votes) {

        int total = 0;

        for (int i = 0; i < votes.length; i++) {
            total += votes[i];
        }

        return total;
    }

    // 4. Processing Module: Find the highest vote count
    static int findMaximumVotes(int[] votes) {

        int maxVotes = votes[0];

        for (int i = 1; i < votes.length; i++) {
            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
            }
        }

        return maxVotes;
    }

    // 5. Output Module: Display results and announce winner
    static void displayResults(String[] names, int[] votes) {

        int totalVotes = calculateTotalVotes(votes);
        int maxVotes = findMaximumVotes(votes);
        int winnerCount = 0;

        System.out.println("\n========== ELECTION RESULTS ==========");
        System.out.printf("%-20s %-10s%n", "Candidate", "Votes");
        System.out.println("--------------------------------------");

        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-20s %-10d%n",
                    names[i], votes[i]);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total votes: " + totalVotes);

        for (int i = 0; i < votes.length; i++) {
            if (votes[i] == maxVotes) {
                winnerCount++;
            }
        }

        System.out.println("\n========== FINAL RESULT ==========");

        if (maxVotes == 0) {
            System.out.println("No votes recorded. No winner.");
        } else if (winnerCount > 1) {

            System.out.println("Election result: TIE!");

            for (int i = 0; i < names.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println(names[i] + " - "
                            + votes[i] + " votes");
                }
            }

        } else {

            for (int i = 0; i < names.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println("Winner: " + names[i]);
                    System.out.println(
                            "Highest vote count: " + maxVotes
                    );
                }
            }
        }
    }

    // 6. Data Storage Module: Save results to a file
    static void saveResults(String[] names, int[] votes) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("election_results.txt"))) {

            int totalVotes = calculateTotalVotes(votes);
            int maxVotes = findMaximumVotes(votes);

            writer.println("STUDENT COUNCIL ELECTION RESULTS");
            writer.println("================================");

            for (int i = 0; i < names.length; i++) {
                writer.println(names[i] + " : "
                        + votes[i] + " votes");
            }

            writer.println("Total votes: " + totalVotes);

            if (maxVotes == 0) {
                writer.println("No votes recorded. No winner.");
            } else {
                int winnerCount = 0;

                for (int vote : votes) {
                    if (vote == maxVotes) {
                        winnerCount++;
                    }
                }

                if (winnerCount > 1) {
                    writer.println("Result: TIE");

                    for (int i = 0; i < names.length; i++) {
                        if (votes[i] == maxVotes) {
                            writer.println("Tied candidate: "
                                    + names[i]);
                        }
                    }
                } else {
                    for (int i = 0; i < names.length; i++) {
                        if (votes[i] == maxVotes) {
                            writer.println("Winner: " + names[i]);
                        }
                    }
                }
            }

            System.out.println(
                    "\nResults saved to election_results.txt"
            );

        } catch (IOException e) {
            System.out.println("Error saving results: "
                    + e.getMessage());
        }
    }

    // 7. Read previously saved results
    static void readResults() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("election_results.txt"))) {

            String line;

            System.out.println("\n--- Saved Election Results ---");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("No saved results found.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" STUDENT COUNCIL ELECTION TALLY SYSTEM");
        System.out.println("======================================");

        int n;

        System.out.print("Enter number of candidates: ");

        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();

                if (n > 0) {
                    sc.nextLine();
                    break;
                }
            } else {
                sc.next();
            }

            System.out.print(
                    "Invalid number! Enter a positive integer: "
            );
        }

        String[] names = new String[n];
        int[] votes = new int[n];

        // Input
        enterCandidateDetails(names);
        enterVotes(names, votes);

        // Processing and Output
        displayResults(names, votes);

        // File Handling
        saveResults(names, votes);

        // Option to read saved results
        System.out.print(
                "\nDo you want to view saved results? (yes/no): "
        );

        String choice = sc.nextLine().trim();

        if (choice.equalsIgnoreCase("yes")) {
            readResults();
        }

        System.out.println("\nProgram completed successfully!");

        sc.close();
    }
}
