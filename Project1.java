
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Project1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] candidates;
        int[] votes;

        System.out.println("==================================");
        System.out.println(" STUDENT COUNCIL ELECTION SYSTEM");
        System.out.println("==================================");

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Invalid number of candidates!");
            sc.close();
            return;
        }

        candidates = new String[n];
        votes = new int[n];

        // 1. Candidate Details
        System.out.println("\n--- Candidate Registration ---");

        for (int i = 0; i < n; i++) {
            System.out.print("Enter candidate " + (i + 1) + " name: ");
            candidates[i] = sc.nextLine();
            votes[i] = 0;
        }

        // 2. Vote Recording
        System.out.print("\nEnter total number of voters: ");
        int totalVoters = sc.nextInt();

        if (totalVoters < 0) {
            System.out.println("Invalid number of voters!");
            sc.close();
            return;
        }

        for (int i = 1; i <= totalVoters; i++) {
            System.out.println("\nVoter " + i);

            for (int j = 0; j < n; j++) {
                System.out.println((j + 1) + ". " + candidates[j]);
            }

            System.out.print("Enter candidate number to vote: ");
            int choice = sc.nextInt();

            if (choice >= 1 && choice <= n) {
                votes[choice - 1]++;
                System.out.println("Vote recorded successfully!");
            } else {
                System.out.println("Invalid choice! Please vote again.");
                i--;
            }
        }

        // 3. Display Candidate-wise Results
        System.out.println("\n========== ELECTION RESULTS ==========");
        System.out.printf("%-20s %s%n", "Candidate", "Votes");
        System.out.println("---------------------------------------");

        int maxVotes = 0;
        int totalVotes = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("%-20s %d%n",
                    candidates[i], votes[i]);

            totalVotes += votes[i];

            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
            }
        }

        System.out.println("---------------------------------------");
        System.out.println("Total votes recorded: " + totalVotes);

        // 4. Winner Announcement
        System.out.println("\n========== WINNER ANNOUNCEMENT ==========");

        if (maxVotes == 0) {
            System.out.println("No votes recorded. No winner.");
        } else {
            int winners = 0;

            for (int i = 0; i < n; i++) {
                if (votes[i] == maxVotes) {
                    winners++;
                }
            }

            if (winners > 1) {
                System.out.println("Election result: TIE");

                for (int i = 0; i < n; i++) {
                    if (votes[i] == maxVotes) {
                        System.out.println(candidates[i]
                                + " - " + votes[i] + " votes");
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (votes[i] == maxVotes) {
                        System.out.println("Winner: " + candidates[i]);
                        System.out.println("Votes received: " + votes[i]);
                    }
                }
            }
        }

        // 5. File Handling: Save Results
        try (PrintWriter writer =
                new PrintWriter(new FileWriter("election_results.txt"))) {

            writer.println("STUDENT COUNCIL ELECTION RESULTS");
            writer.println("================================");

            for (int i = 0; i < n; i++) {
                writer.println(candidates[i] + " : " + votes[i] + " votes");
            }

            writer.println("Total votes recorded: " + totalVotes);
            writer.println("Maximum votes: " + maxVotes);

            if (maxVotes > 0) {
                for (int i = 0; i < n; i++) {
                    if (votes[i] == maxVotes) {
                        writer.println("Winner/Tied candidate: "
                                + candidates[i]);
                    }
                }
            } else {
                writer.println("No votes recorded. No winner.");
            }

            System.out.println("\nResults saved to election_results.txt");

        } catch (IOException e) {
            System.out.println("Error saving election results: "
                    + e.getMessage());
        }

        sc.close();
        System.out.println("\nThank you for using the system!");
    }
}
