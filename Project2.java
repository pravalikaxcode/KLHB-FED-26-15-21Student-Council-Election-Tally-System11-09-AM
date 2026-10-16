
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Project2 {

    static Scanner sc = new Scanner(System.in);

    // Method 1: Enter candidate details
    static void enterCandidates(String[] names, int[] votes) {
        for (int i = 0; i < names.length; i++) {
            System.out.print("Enter candidate " + (i + 1) + " name: ");
            names[i] = sc.nextLine().trim();

            while (names[i].isEmpty()) {
                System.out.print("Name cannot be empty. Enter again: ");
                names[i] = sc.nextLine().trim();
            }

            votes[i] = 0;
        }
    }

    // Method 2: Enter and validate vote counts
    static void enterVotes(int[] votes, String[] names) {
        System.out.println("\nEnter votes for each candidate:");

        for (int i = 0; i < votes.length; i++) {
            while (true) {
                System.out.print(names[i] + ": ");

                if (sc.hasNextInt()) {
                    int count = sc.nextInt();

                    if (count >= 0) {
                        votes[i] = count;
                        break;
                    } else {
                        System.out.println("Votes cannot be negative.");
                    }
                } else {
                    System.out.println("Enter a valid whole number.");
                    sc.next();
                }
            }
        }
        sc.nextLine();
    }

    // Method 3: Display results
    static void displayResults(String[] names, int[] votes) {
        int maxVotes = votes[0];
        int totalVotes = 0;
        int winnerCount = 0;

        for (int i = 0; i < votes.length; i++) {
            totalVotes += votes[i];

            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
            }
        }

        System.out.println("\n========== ELECTION RESULTS ==========");
        System.out.printf("%-20s %-10s %-15s%n",
                "Candidate", "Votes", "Status");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < votes.length; i++) {
            String status = (votes[i] == maxVotes && maxVotes > 0)
                    ? "Top candidate" : "Candidate";

            System.out.printf("%-20s %-10d %-15s%n",
                    names[i], votes[i], status);

            if (votes[i] == maxVotes) {
                winnerCount++;
            }
        }

        System.out.println("----------------------------------------------");
        System.out.println("Total votes: " + totalVotes);

        System.out.println("\n========== WINNER ANNOUNCEMENT ==========");

        if (maxVotes == 0) {
            System.out.println("No votes recorded. No winner.");
        } else if (winnerCount > 1) {
            System.out.println("Election result: TIE!");

            for (int i = 0; i < votes.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println(names[i] + " - "
                            + votes[i] + " votes");
                }
            }
        } else {
            for (int i = 0; i < votes.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println("Winner: " + names[i]);
                    System.out.println("Highest vote count: " + maxVotes);
                }
            }
        }
    }

    // Method 4: Save results to a file
    static void saveResults(String[] names, int[] votes) {
        try (PrintWriter writer =
                new PrintWriter(new FileWriter("election_results.txt"))) {

            writer.println("STUDENT COUNCIL ELECTION RESULTS");
            writer.println("================================");

            int maxVotes = votes[0];
            int totalVotes = 0;

            for (int vote : votes) {
                if (vote > maxVotes) {
                    maxVotes = vote;
                }
                totalVotes += vote;
            }

            for (int i = 0; i < names.length; i++) {
                writer.println(names[i] + " : " + votes[i] + " votes");
            }

            writer.println("Total votes: " + totalVotes);

            if (maxVotes == 0) {
                writer.println("No votes recorded. No winner.");
            } else {
                int count = 0;

                for (int vote : votes) {
                    if (vote == maxVotes) {
                        count++;
                    }
                }

                if (count > 1) {
                    writer.println("Result: TIE");

                    for (int i = 0; i < votes.length; i++) {
                        if (votes[i] == maxVotes) {
                            writer.println("Tied candidate: " + names[i]);
                        }
                    }
                } else {
                    for (int i = 0; i < votes.length; i++) {
                        if (votes[i] == maxVotes) {
                            writer.println("Winner: " + names[i]);
                        }
                    }
                }
            }

            System.out.println("\nResults saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Method 5: Read previously saved results
    static void readResults() {
        try (BufferedReader reader =
                new BufferedReader(new FileReader("election_results.txt"))) {

            String line;
            System.out.println("\n===== PREVIOUS ELECTION RESULTS =====");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("No saved results found. Save results first.");
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" STUDENT COUNCIL ELECTION TALLY SYSTEM");
        System.out.println("======================================");

        int n;

        System.out.print("Enter number of candidates: ");

        while (!sc.hasNextInt() || (n = sc.nextInt()) <= 0) {
            System.out.println("Enter a valid positive number.");
            if (sc.hasNextInt()) {
                sc.nextInt();
            } else {
                sc.next();
            }
            System.out.print("Enter number of candidates: ");
        }

        n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];
        int[] votes = new int[n];

        enterCandidates(names, votes);
        enterVotes(votes, names);
        displayResults(names, votes);
        saveResults(names, votes);

        System.out.print("\nDo you want to view saved results? (yes/no): ");
        String answer = sc.nextLine().trim();

        if (answer.equalsIgnoreCase("yes")) {
            readResults();
        }

        System.out.println("\nThank you for using the system!");
        sc.close();
    }
}
