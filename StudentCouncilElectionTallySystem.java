
import java.util.ArrayList;
import java.util.Scanner;

public class StudentCouncilElectionTallySystem {

    static Scanner sc = new Scanner(System.in);

    static String[] candidates = new String[3];
    static int[] votes = new int[3];

    static ArrayList<String> voterIDs = new ArrayList<>();

    // Register candidates
    static void registerCandidates() {
        System.out.println("\n--- REGISTER CANDIDATES ---");

        for (int i = 0; i < candidates.length; i++) {
            System.out.print("Enter candidate " + (i + 1) + " name: ");
            candidates[i] = sc.nextLine().trim();

            while (candidates[i].isEmpty()) {
                System.out.print("Name cannot be empty. Enter again: ");
                candidates[i] = sc.nextLine().trim();
            }
        }

        System.out.println("Candidates registered successfully!");
    }

    // Cast a vote
    static void castVote() {
        System.out.print("\nEnter your Voter ID: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Voter ID cannot be empty!");
            return;
        }

        if (voterIDs.contains(id)) {
            System.out.println("You have already voted!");
            return;
        }

        System.out.println("\n--- CANDIDATES ---");

        for (int i = 0; i < candidates.length; i++) {
            System.out.println((i + 1) + ". " + candidates[i]);
        }

        System.out.print("Enter your candidate choice: ");

        String input = sc.nextLine();
        int candidateChoice;

        try {
            candidateChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Enter a number.");
            return;
        }

        if (candidateChoice < 1 ||
            candidateChoice > candidates.length) {
            System.out.println("Invalid candidate number!");
            return;
        }

        votes[candidateChoice - 1]++;
        voterIDs.add(id);

        System.out.println("Your vote has been recorded successfully!");
    }

    // Display election results
    static void displayResults() {
        System.out.println("\n====== ELECTION RESULTS ======");

        int maxVotes = 0;
        int totalVotes = 0;

        for (int i = 0; i < candidates.length; i++) {
            System.out.println(
                candidates[i] + " : " + votes[i] + " votes"
            );

            totalVotes += votes[i];

            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
            }
        }

        System.out.println("------------------------------");
        System.out.println("Total votes: " + totalVotes);

        if (totalVotes == 0) {
            System.out.println("No votes have been cast yet.");
            return;
        }

        int winnerCount = 0;

        for (int i = 0; i < candidates.length; i++) {
            if (votes[i] == maxVotes) {
                winnerCount++;
            }
        }

        if (winnerCount > 1) {
            System.out.println("\nResult: TIE!");

            for (int i = 0; i < candidates.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println(
                        "Candidate: " + candidates[i]
                    );
                }
            }
        } else {
            for (int i = 0; i < candidates.length; i++) {
                if (votes[i] == maxVotes) {
                    System.out.println(
                        "\nWINNER: " + candidates[i]
                    );
                }
            }
        }

        System.out.println("==============================");
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" STUDENT COUNCIL ELECTION TALLY SYSTEM");
        System.out.println("====================================");

        registerCandidates();

        int choice = 0;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Cast Vote");
            System.out.println("2. View Election Results");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter 1, 2, or 3.");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    castVote();
                    break;

                case 2:
                    displayResults();
                    break;

                case 3:
                    System.out.println(
                        "Thank you for using the Election Tally System!"
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice! Please select 1, 2, or 3."
                    );
            }

        } while (choice != 3);

        sc.close();
    }
}
