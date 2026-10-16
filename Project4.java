
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Project4 {

    static Scanner sc = new Scanner(System.in);

    // Candidate class
    static class Candidate {
        String name;
        int votes;

        Candidate(String name, int votes) {
            this.name = name;
            this.votes = votes;
        }

        // Method to display candidate details
        void displayDetails() {
            System.out.printf("%-20s %d%n", name, votes);
        }
    }

    // Method to read a valid integer
    static int readInteger(String message, int minimum) {
        int value;

        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                value = sc.nextInt();
                sc.nextLine();

                if (value >= minimum) {
                    return value;
                }
            } else {
                sc.nextLine();
            }

            System.out.println(
                "Invalid input! Enter a number >= " + minimum
            );
        }
    }

    // Method to enter candidate details
    static Candidate[] enterCandidates() {
        int n = readInteger(
            "Enter number of candidates: ", 1
        );

        Candidate[] candidates = new Candidate[n];

        System.out.println("\n--- Candidate Registration ---");

        for (int i = 0; i < n; i++) {
            String name;

            while (true) {
                System.out.print(
                    "Enter candidate " + (i + 1) + " name: "
                );

                name = sc.nextLine().trim();

                if (!name.isEmpty()) {
                    break;
                }

                System.out.println("Name cannot be empty!");
            }

            candidates[i] = new Candidate(name, 0);
        }

        return candidates;
    }

    // Method to enter vote counts
    static void enterVotes(Candidate[] candidates) {
        System.out.println("\n--- Enter Vote Counts ---");

        for (int i = 0; i < candidates.length; i++) {
            candidates[i].votes = readInteger(
                "Enter votes for " + candidates[i].name + ": ", 0
            );
        }
    }

    // Method to calculate total votes
    static int calculateTotal(Candidate[] candidates) {
        int total = 0;

        for (Candidate c : candidates) {
            total += c.votes;
        }

        return total;
    }

    // Method to find maximum votes
    static int findMaximumVotes(Candidate[] candidates) {
        int maxVotes = candidates[0].votes;

        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i].votes > maxVotes) {
                maxVotes = candidates[i].votes;
            }
        }

        return maxVotes;
    }

    // Method to display election results
    static void displayResults(Candidate[] candidates) {
        int total = calculateTotal(candidates);
        int maxVotes = findMaximumVotes(candidates);

        System.out.println("\n========== ELECTION RESULTS ==========");
        System.out.printf("%-20s %s%n", "Candidate", "Votes");
        System.out.println("--------------------------------------");

        for (Candidate c : candidates) {
            c.displayDetails();
        }

        System.out.println("--------------------------------------");
        System.out.println("Total votes: " + total);

        // Winner selection and tie handling
        System.out.println("\n========== WINNER ANNOUNCEMENT ==========");

        if (maxVotes == 0) {
            System.out.println("No votes recorded. No winner.");
            return;
        }

        int winnerCount = 0;

        for (Candidate c : candidates) {
            if (c.votes == maxVotes) {
                winnerCount++;
            }
        }

        if (winnerCount > 1) {
            System.out.println("Election result: TIE!");

            for (Candidate c : candidates) {
                if (c.votes == maxVotes) {
                    System.out.println(
                        c.name + " - " + c.votes + " votes"
                    );
                }
            }
        } else {
            for (Candidate c : candidates) {
                if (c.votes == maxVotes) {
                    System.out.println("Winner: " + c.name);
                    System.out.println(
                        "Highest votes: " + c.votes
                    );
                }
            }
        }
    }

    // Method to save results to a file
    static void saveResults(Candidate[] candidates) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("election_results.txt"))) {

            int maxVotes = findMaximumVotes(candidates);

            writer.println("STUDENT COUNCIL ELECTION RESULTS");
            writer.println("================================");

            for (Candidate c : candidates) {
                writer.println(
                    c.name + " : " + c.votes + " votes"
                );
            }

            writer.println(
                "Total votes: " + calculateTotal(candidates)
            );

            if (maxVotes == 0) {
                writer.println("No votes recorded. No winner.");
            } else {
                int winnerCount = 0;

                for (Candidate c : candidates) {
                    if (c.votes == maxVotes) {
                        winnerCount++;
                    }
                }

                if (winnerCount > 1) {
                    writer.println("Result: TIE");

                    for (Candidate c : candidates) {
                        if (c.votes == maxVotes) {
                            writer.println(
                                "Tied candidate: " + c.name
                            );
                        }
                    }
                } else {
                    for (Candidate c : candidates) {
                        if (c.votes == maxVotes) {
                            writer.println("Winner: " + c.name);
                        }
                    }
                }
            }

            System.out.println(
                "\nResults saved to election_results.txt"
            );

        } catch (IOException e) {
            System.out.println(
                "Error saving results: " + e.getMessage()
            );
        }
    }

    // Method to read saved results
    static void readResults() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("election_results.txt"))) {

            String line;

            System.out.println("\n--- Saved Election Results ---");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(
                "No saved results found. Save results first."
            );
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" STUDENT COUNCIL ELECTION TALLY SYSTEM");
        System.out.println("======================================");

        // Input
        Candidate[] candidates = enterCandidates();
        enterVotes(candidates);

        // Processing and output
        displayResults(candidates);

        // File handling
        saveResults(candidates);

        System.out.print(
            "\nDo you want to view saved results? (yes/no): "
        );

        String choice = sc.nextLine().trim();

        if (choice.equalsIgnoreCase("yes")) {
            readResults();
        }

        System.out.println("\nThank you for using the system!");

        sc.close();
    }
}
