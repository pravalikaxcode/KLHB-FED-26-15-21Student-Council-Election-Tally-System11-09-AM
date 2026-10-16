
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Project5 {

    static Scanner sc = new Scanner(System.in);

    // Candidate class
    static class Candidate {
        String name;
        int votes;

        Candidate(String name, int votes) {
            this.name = name;
            this.votes = votes;
        }
    }

    // Read and validate integer input
    static int readInteger(String message, int minimum) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
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

    // Enter candidate information
    static Candidate[] enterCandidates() {
        int n = readInteger("Enter number of candidates: ", 1);

        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            String name;

            do {
                System.out.print(
                    "Enter candidate " + (i + 1) + " name: "
                );
                name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty!");
                }
            } while (name.isEmpty());

            candidates[i] = new Candidate(name, 0);
        }

        return candidates;
    }

    // Enter vote counts
    static void enterVotes(Candidate[] candidates) {
        System.out.println("\n--- Enter Vote Counts ---");

        for (Candidate c : candidates) {
            c.votes = readInteger(
                "Enter votes for " + c.name + ": ", 0
            );
        }
    }

    // Calculate total votes
    static int calculateTotal(Candidate[] candidates) {
        int total = 0;

        for (Candidate c : candidates) {
            total += c.votes;
        }

        return total;
    }

    // Find highest vote count
    static int findHighestVotes(Candidate[] candidates) {
        int highest = candidates[0].votes;

        for (Candidate c : candidates) {
            if (c.votes > highest) {
                highest = c.votes;
            }
        }

        return highest;
    }

    // Display candidate-wise tally and winner
    static void displayResults(Candidate[] candidates) {
        int total = calculateTotal(candidates);
        int highest = findHighestVotes(candidates);
        int winnerCount = 0;

        System.out.println("\n========== SAMPLE TALLY ==========");
        System.out.printf("%-25s %10s%n", "Candidate", "Votes");
        System.out.println("------------------------------------");

        for (Candidate c : candidates) {
            System.out.printf(
                "%-25s %10d%n", c.name, c.votes
            );
        }

        System.out.println("------------------------------------");
        System.out.printf("%-25s %10d%n", "Total", total);

        for (Candidate c : candidates) {
            if (c.votes == highest) {
                winnerCount++;
            }
        }

        System.out.println("\n========== WINNER RESULT ==========");

        if (highest == 0) {
            System.out.println("No votes recorded. No winner.");
        } else if (winnerCount > 1) {
            System.out.println("Election result: TIE!");

            for (Candidate c : candidates) {
                if (c.votes == highest) {
                    System.out.println(
                        c.name + " - " + c.votes + " votes"
                    );
                }
            }
        } else {
            for (Candidate c : candidates) {
                if (c.votes == highest) {
                    System.out.println("Winner: " + c.name);
                    System.out.println("Votes: " + c.votes);
                }
            }
        }
    }

    // Save election results to a file
    static void saveResults(Candidate[] candidates) {
        int total = calculateTotal(candidates);
        int highest = findHighestVotes(candidates);

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("Project5_Results.txt"))) {

            writer.println("STUDENT COUNCIL ELECTION RESULTS");
            writer.println("================================");

            for (Candidate c : candidates) {
                writer.println(
                    c.name + " : " + c.votes + " votes"
                );
            }

            writer.println("Total votes: " + total);

            if (highest == 0) {
                writer.println("No votes recorded. No winner.");
            } else {
                int winnerCount = 0;

                for (Candidate c : candidates) {
                    if (c.votes == highest) {
                        winnerCount++;
                    }
                }

                if (winnerCount > 1) {
                    writer.println("Election result: TIE");

                    for (Candidate c : candidates) {
                        if (c.votes == highest) {
                            writer.println(
                                "Tied candidate: " + c.name
                            );
                        }
                    }
                } else {
                    for (Candidate c : candidates) {
                        if (c.votes == highest) {
                            writer.println("Winner: " + c.name);
                            writer.println("Winning votes: " + highest);
                        }
                    }
                }
            }

            System.out.println(
                "\nResults saved to Project5_Results.txt"
            );

        } catch (IOException e) {
            System.out.println(
                "Error saving results: " + e.getMessage()
            );
        }
    }

    // Read previously saved results
    static void readResults() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("Project5_Results.txt"))) {

            String line;

            System.out.println("\n--- SAVED ELECTION RECORD ---");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("No saved election record found.");
        }
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println(" STUDENT COUNCIL ELECTION TALLY SYSTEM");
        System.out.println("======================================");

        Candidate[] candidates = enterCandidates();

        enterVotes(candidates);

        displayResults(candidates);

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
