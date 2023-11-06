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
        String[] names = new String[playerNumber];
        System.out.println("Enter your names");
        scanner.nextLine();
        for (int i = 0; i < playerNumber; i++) {
            names[i] = scanner.nextLine();
        }
        for (int i = 0; i < playerNumber; i++) {
            int index = random.nextInt(playerNumber);
            String a = names[index];
            names[index] = names[i];
            names[i] = a;
        }
        System.out.println("You will be playing in following order:");
        for (int i = 0; i < playerNumber; i++) {
            System.out.println(names[i]);
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
        for (int i = 0; i < myWords.size(); i++) {
            char[] currentWord = myWords.get(i).toCharArray();
            char[] currentWordHidden = new char[currentWord.length];
            for (int k = 0; k < currentWord.length; k++) {
                currentWordHidden[k] = '_';
                System.out.print(currentWordHidden[k] + " ");
            }
            int playerPoints = 0;
            System.out.println ("Guess the letter or the entire word");
            System.out.println(names[0] + "'s turn to play.");
            while (keepGoing) {
                for (int l = 0; l < playerNumber; l++) { //change to turns size because of eliminations
                    String playerGuess = scanner.nextLine();
                    if (playerGuess.length() == 1) {
                        while (myWords.get(i).contains(playerGuess) && playerGuess.length() == 1) {
                            System.out.println("Correct guess. Player " + names[l] + " gets another attempt");
                            playerPoints += 100;
                            leaderboard[l] += playerPoints;
                            playerPoints = 0;
                            if (leaderboard[l] > myWords.get(i).length() * 100 / 2) {
                                int o = l;
                                for (int m = 1; m < playerNumber; m++) {
                                    if (l == playerNumber - 1) {
                                        System.out.println("Player" + names[l] + "has reached max pts. Each player gets 1 attempt to guess the full word. " + names[0] + "'s turn");
                                        l = 0;
                                    }
                                    else {
                                        System.out.println("Player" + names[l] + "has reached max pts. Each player gets 1 attempt to guess the full word. " + names[l + 1] + "'s turn");
                                        l++;
                                    }
                                    playerGuess = scanner.nextLine();
                                    if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                                        playerPoints += 100 * myWords.get(i).length();
                                        leaderboard[m] += playerPoints;
                                        System.out.println("Congratulations! Player " + names[l] + " won");
                                        keepGoing = false;
                                        playerPoints = 0;
                                        l += playerNumber;
                                        break;
                                    }
                                    else {
                                        System.out.println("Wrong guess. " + names[l] + " lost");
                                    }
                                    if (m == playerNumber - 1) {
                                        System.out.println("Nobody has guessed right. " + names[o] + " wins.");
                                        keepGoing = false;
                                        l += playerNumber;
                                        break;
                                    }
                                }
                                keepGoing = false;
                                l += playerNumber;
                                break;
                            }
                            playerGuess = scanner.nextLine();
                            if (playerGuess.length() > 1) {
                                if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                                    playerPoints += 100 * myWords.get(i).length();
                                    leaderboard[l] += playerPoints;
                                    System.out.println("Congratulations! Player " + names[l] + "won");
                                    keepGoing = false;
                                    playerPoints = 0;
                                    l += playerNumber;
                                }
                                else {
                                    if (l == playerNumber - 1) {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: " + names[0]);
                                    }
                                    else {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: player " + names[l + 1]); //turns.get(l)
                                    }
                                    break;

                                }
                            }
                        }
                        if (!myWords.get(i).contains(playerGuess) && playerGuess.length() == 1) {
                            if (l == playerNumber - 1) {
                                System.out.println("Wrong. Next turn: " + names[0]);
                            }
                            else {
                                System.out.println("Wrong guess. Next turn: " + names[l + 1]); //turns.get(l)
                            }
                        }
                    }
                    else {
                        if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                            playerPoints += 100 * myWords.get(i).length();
                            leaderboard[l] += playerPoints;
                            System.out.println("Congratulations! Player " + "won");
                            keepGoing = false;
                            playerPoints = 0;
                            break;
                        }
                        else {
                            if (l == playerNumber - 1) {
                                System.out.println("Wrong guess. Player eliminated. Next turn: " + names[0]);
                            }
                            else {
                                System.out.println("Wrong guess. Player eliminated. Next turn: player " + names[l + 1]); //turns.get(l)
                            }
                        }
                    }
                }
            }
            keepGoing = true;
        }
        System.out.println("Game finished. Leaderboard:");
        for (int i = 0; i < leaderboard.length; i++) {
            System.out.println(names[i] + ": " + leaderboard[i] + " pts");
        }
    }
}
