import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter number of players");
        int playerNumber = scanner.nextInt();
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Enter your names");
        scanner.nextLine();
        for (int i = 0; i < playerNumber; i++) {
            names.add(scanner.nextLine());
        }
        for (int i = 0; i < playerNumber; i++) {
            int index = random.nextInt(playerNumber);
            String a = names.get(index);
            names.set(index, names.get(i));
            names.set(i, a);
        }
        System.out.println("You will be playing in following order:");
        for (int i = 0; i < playerNumber; i++) {
            System.out.println(names.get(i));
        }

        ArrayList<String> myWords = new ArrayList<>();
        ArrayList<String> explanations = new ArrayList<>();

        myWords.add("silver");
        myWords.add("smurf");
        myWords.add("bottle");
        myWords.add("america");
        myWords.add("poetry");
        myWords.add("achievement");
        myWords.add("ecology");
        myWords.add("dollar");
        myWords.add("president");
        myWords.add("coffee");
        explanations.add("metal");
        explanations.add("blue creature");
        explanations.add("used to store drinks");
        explanations.add("obama");
        explanations.add("something");
        explanations.add("when you uh");
        explanations.add("green");
        explanations.add("money");
        explanations.add("obama");
        explanations.add("drink");

        int[] leaderboard = new int[playerNumber];

        int n = 0;

        boolean keepGoing = true;
        for (String myWord : myWords) {
            char[] currentWord = myWord.toCharArray();
            char[] currentWordHidden = new char[currentWord.length];
            for (int k = 0; k < currentWord.length; k++) {
                currentWordHidden[k] = '_';
                System.out.print(currentWordHidden[k] + " ");
            }
            int playerPoints = 0;

            ArrayList<String> turns = new ArrayList<>();
            for (int j = 0; j < names.size(); j++) {
                turns.add(j, names.get(j));
            }
            int z = turns.size();
            System.out.println("Guess the letter or the entire word");
            System.out.println(turns.get(0) + "'s turn to play.");
            while (keepGoing) {
                for (int l = 0; l < z; l++) { //change to turns size because of eliminations
                    String playerGuess = scanner.nextLine();
                    if (playerGuess.length() == 1) {
                        while (myWord.contains(playerGuess) && playerGuess.length() == 1) {
                            System.out.println("Correct guess. Player " + turns.get(l) + " gets another attempt");
                            playerPoints += 100;
                            leaderboard[l] += playerPoints;
                            if (playerPoints > myWord.length() * 100 / 2) {
                                playerPoints = 0;
                                int o = l;
                                for (int m = 1; m < z; m++) {
                                    if (l == z - 1) {
                                        System.out.println("Player" + turns.get(l) + "has reached max pts. Each player gets 1 attempt to guess the full word. " + turns.get(l + 1) + "'s turn");
                                        l = 0;
                                    }
                                    else {
                                        System.out.println("Player" + turns.get(l) + "has reached max pts. Each player gets 1 attempt to guess the full word. " + turns.get(l + 1) + "'s turn");
                                        l++;
                                    }
                                    playerGuess = scanner.nextLine();
                                    if (playerGuess.equalsIgnoreCase(myWord)) {
                                        playerPoints += 100 * myWord.length();
                                        leaderboard[m] += playerPoints;
                                        System.out.println("Congratulations! Player " + turns.get(l) + " won");
                                        keepGoing = false;
                                        playerPoints = 0;
                                        l += z;
                                        break;
                                    }
                                    else {
                                        System.out.println("Wrong guess. " + turns.get(l) + " lost");
                                    }
                                    if (m == z - 1) {
                                        System.out.println("Nobody has guessed right. " + turns.get(o) + " wins.");
                                        keepGoing = false;
                                        l += z;
                                        break;
                                    }
                                }
                                keepGoing = false;
                                l += z;
                                break;
                            }
                            playerGuess = scanner.nextLine();
                            if (playerGuess.length() > 1) {
                                if (playerGuess.equalsIgnoreCase(myWord)) {
                                    playerPoints += 100 * myWord.length();
                                    leaderboard[l] += playerPoints;
                                    System.out.println("Congratulations! Player " + turns.get(l) + " won");
                                    keepGoing = false;
                                    playerPoints = 0;
                                    l += z;
                                } 
                                else {
                                    if (l == z - 1) {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: " + turns.get(0));
                                        turns.remove(l);
                                        l--;
                                        z--;
                                    }
                                    else {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: player " + turns.get(l + 1)); //turns.get(l)
                                        turns.remove(l);
                                        l--;
                                        z--;
                                    }
                                    break;
                                }
                            }
                        }
                        if (!myWord.contains(playerGuess) && playerGuess.length() == 1) {
                            if (l == z - 1) {
                                System.out.println("Wrong. Next turn: " + turns.get(0));
                            }
                            else {
                                System.out.println("Wrong guess. Next turn: " + turns.get(l + 1)); //turns.get(l)
                            }
                        }
                    }
                    else {
                        if (playerGuess.equalsIgnoreCase(myWord)) {
                            playerPoints += 100 * myWord.length();
                            leaderboard[l] += playerPoints;
                            System.out.println("Congratulations! Player " + turns.get(l) + "won");
                            keepGoing = false;
                            playerPoints = 0;
                            break;
                        }
                        else {
                            if (l == z - 1) {
                                System.out.println("Wrong guess. Player eliminated. Next turn: " + turns.get(0));
                                turns.remove(l);
                                l--;
                                z--;
                            }
                            else {
                                System.out.println("Wrong guess. Player eliminated. Next turn: player " + turns.get(l + 1)); //turns.get(l)
                                turns.remove(l);
                                l--;
                                z--;
                            }
                        }
                    }
                }
            }
            keepGoing = true;
        }
        System.out.println("Game finished. Leaderboard:");
        for (int i = 0; i < leaderboard.length; i++) {
            System.out.println(names.get(i) + ": " + leaderboard[i] + " pts");
        }
    }
}