import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        printHelloCat();
        System.out.println("*********************************************");
        System.out.println("*          Enter number of players          *");
        System.out.println("*********************************************");
        int playerNumber = scanner.nextInt();
        ArrayList<String> names = new ArrayList<>();
        System.out.print("\033c");
        System.out.println("**********************");
        System.out.println("*  Enter your names  *");
        System.out.println("**********************");
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
        printPlayerCats();
        System.out.println("Enter any key to continue");
        scanner.nextLine();
        System.out.print("\033c");

        ArrayList<String> myWords = new ArrayList<>();
        ArrayList<String> explanations = new ArrayList<>(); //add later

        myWords.add("silver");
        myWords.add("smurf");
        myWords.add("bottle");
        myWords.add("poland");
        myWords.add("poetry");
        myWords.add("achievement");
        myWords.add("ecology");
        myWords.add("dollar");
        myWords.add("president");
        myWords.add("coffee");
        explanations.add("metal");
        explanations.add("blue creature");
        explanations.add("used to store drinks");
        explanations.add("warsaw");
        explanations.add("literature");
        explanations.add("deleted valorant"); //for Wonker propaganda material hi Wonker
        explanations.add("green");
        explanations.add("money");
        explanations.add("obama");
        explanations.add("drink");

        int[] leaderboard = new int[playerNumber];
        int[] oneRoundScore = new int[playerNumber];

        boolean keepGoing = true;
        for (int i = 0; i < myWords.size(); i++) { //remove enhanced for loop and swiTCH ALL MYWORD TO MYWORDS.GET(I);
            char[] currentWord = myWords.get(i).toCharArray(); //change back to normal for loop with i and add explkANtriaons
            char[] currentWordHidden = new char[currentWord.length];
            printInstructions();
            System.out.println("Hint: " + explanations.get(i)); //remove enhanced for loop
            for (int k = 0; k < currentWord.length; k++) {
                currentWordHidden[k] = '_';
                System.out.print(currentWordHidden[k] + " ");
            }
            System.out.println();
            int playerPoints = 0;

            ArrayList<String> turns = new ArrayList<>();
            for (int j = 0; j < names.size(); j++) {
                turns.add(j, names.get(j));
            }
            int z = turns.size();
            System.out.println(turns.get(0) + "'s turn to play.");
            printTurnsKitten();
            while (keepGoing) {
                for (int l = 0; l < z; l++) { //change to turns size because of eliminations
                    String playerGuess = scanner.nextLine();
                    System.out.print("\033c");
                    printInstructions();
                    System.out.println("Hint: " + explanations.get(i)); //remove enhanced for loop
                    if (playerGuess.length() == 1) {
                        while (myWords.get(i).contains(playerGuess) && playerGuess.length() == 1) {
                            unhideLetters(currentWord, currentWordHidden, playerGuess);
                            System.out.println("Correct guess.");
                            printTurnsKitten();
                            playerPoints += 100;
                            oneRoundScore[l] += playerPoints;
                            leaderboard[l] += playerPoints;
                            if (!(oneRoundScore[l] > myWords.get(i).length() * 100 / 2)) {
                                System.out.println("Player " + turns.get(l) + " gets another attempt");
                            }
                            if (oneRoundScore[l] > myWords.get(i).length() * 100 / 2) {
                                playerPoints = 0;
                                int o = l;
                                for (int m = 1; m < z; m++) {
                                    if (l == z - 1) {
                                        System.out.println("Player " + turns.get(l) + " has reached max pts. Each player gets 1 attempt to guess the full word. " + turns.get(0) + "'s turn");
                                        l = 0;
                                    }
                                    else {
                                        System.out.println("Player " + turns.get(l) + " has reached max pts. Each player gets 1 attempt to guess the full word. " + turns.get(l + 1) + "'s turn");
                                        l++;
                                    }
                                    playerGuess = scanner.nextLine();
                                    System.out.print("\033c");
                                    printInstructions();
                                    System.out.println("Hint: " + explanations.get(i)); //remove enhanced for loop
                                    if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                                        playerPoints += 100 * myWords.get(i).length();
                                        leaderboard[m] += playerPoints;
                                        unhideWord(currentWord);
                                        System.out.println("Congratulations! Player " + turns.get(l) + " won");
                                        printWinnerCat();
                                        System.out.println("Press any key to get a new word");
                                        scanner.nextLine();
                                        System.out.print("\033c");
                                        playerPoints = 0;
                                        l += z;
                                        for (int v = 0; v < playerNumber; v++) {
                                            oneRoundScore[v] = 0;
                                        }
                                        break;
                                    }
                                    else {
                                        unhideLetters(currentWord, currentWordHidden, playerGuess);
                                        System.out.println("Wrong guess. " + turns.get(l) + " lost");
                                        printLoserCat();
                                    }
                                    if (m == z - 1) {
                                        System.out.print("\033c");
                                        printInstructions();
                                        System.out.println("Hint: " + explanations.get(i));
                                        unhideWord(currentWord);
                                        System.out.println("Nobody has guessed right. " + turns.get(o) + " wins.");
                                        printWinnerCat();
                                        System.out.println("Press any key to get a new word");
                                        scanner.nextLine();
                                        System.out.print("\033c");
                                        for (int v = 0; v < playerNumber; v++) {
                                            oneRoundScore[v] = 0;
                                        }
                                        l += z;
                                        break;
                                    }
                                }
                                keepGoing = false;
                                l += z;
                                break;
                            }
                            playerPoints = 0;
                            playerGuess = scanner.nextLine();
                            System.out.print("\033c");
                            printInstructions();
                            System.out.println("Hint: " + explanations.get(i)); //remove enhanced for loop
                            if (playerGuess.length() > 1) {
                                if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                                    playerPoints += 100 * myWords.get(i).length();
                                    leaderboard[l] += playerPoints;
                                    unhideWord(currentWord);
                                    System.out.println("Congratulations! Player " + turns.get(l) + " won");
                                    printWinnerCat();
                                    System.out.println("Press any key to get a new word");
                                    scanner.nextLine();
                                    System.out.print("\033c");
                                    keepGoing = false;
                                    for (int v = 0; v < playerNumber; v++) {
                                        oneRoundScore[v] = 0;
                                    }
                                    playerPoints = 0;
                                    l += z;
                                }
                                else {
                                    unhideLetters(currentWord, currentWordHidden, playerGuess);
                                    if (l == z - 1) {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: " + turns.get(0));
                                        printLoserCat();
                                        turns.remove(l);
                                        l--;
                                        z--;
                                    }
                                    else {
                                        System.out.println("Wrong guess. Player eliminated. Next turn: player " + turns.get(l + 1)); //turns.get(l)
                                        printLoserCat();
                                        turns.remove(l);
                                        l--;
                                        z--;
                                    }
                                    break;
                                }
                            }
                        }
                        if (!myWords.get(i).contains(playerGuess) && playerGuess.length() == 1) {
                            unhideLetters(currentWord, currentWordHidden, playerGuess);
                            if (l == z - 1) {
                                System.out.println("Wrong. Next turn: " + turns.get(0));
                            }
                            else {
                                System.out.println("Wrong guess. Next turn: " + turns.get(l + 1)); //turns.get(l)
                            }
                            printTurnsKitten();
                        }
                    }
                    else {
                        if (playerGuess.equalsIgnoreCase(myWords.get(i))) {
                            playerPoints += 100 * myWords.get(i).length();
                            leaderboard[l] += playerPoints;
                            unhideWord(currentWord);
                            System.out.println("Congratulations! Player " + turns.get(l) + " won");
                            printWinnerCat();
                            System.out.println("Press any key to get a new word");
                            scanner.nextLine();
                            System.out.print("\033c");
                            keepGoing = false;
                            for (int v = 0; v < playerNumber; v++) {
                                oneRoundScore[v] = 0;
                            }
                            playerPoints = 0;
                            break;
                        }
                        else {
                            unhideLetters(currentWord, currentWordHidden, playerGuess);
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
                            printLoserCat();
                        }
                    }
                }
            }
            keepGoing = true;
        }
        System.out.println("Game finished. No more words for you! Leaderboard:");
        for (int i = 0; i < leaderboard.length; i++) {
            System.out.println(names.get(i) + ": " + leaderboard[i] + " pts");
        }
        printPlayerCats();
    }
    private static void unhideLetters(char[] currentWord, char[] currentWordHidden, String playerGuess) {
        for (int p = 0; p < currentWord.length; p++) {//hide letters
            if (playerGuess.charAt(0) == (currentWord[p])) {
                currentWordHidden[p] = currentWord[p];
            }
            System.out.print(currentWordHidden[p] + " ");
        }
        System.out.println();
    }
    public static void unhideWord(char[] currentWord) {
        for (char c : currentWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void printHelloCat() {
        System.out.println("             *     ,MMM8&&&.            *");
        System.out.println("                  MMMM88&&&&&    .");
        System.out.println("                 MMMM88&&&&&&&");
        System.out.println("     *           M    HI!    &");
        System.out.println("                 MMM88&&&&&&&&");
        System.out.println("                 'MMM88&&&&&&'");
        System.out.println("                   'MMM8&&&'      *");
        System.out.println("          |\\___/|");
        System.out.println("          )     (             .              '");
        System.out.println("         =\\     /=");
        System.out.println("           )===(       *");
        System.out.println("          /     \\");
        System.out.println("          |     |");
        System.out.println("         /       \\");
        System.out.println("         \\       /");
        System.out.println("  _/\\_/\\_/\\__  _/_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_");
        System.out.println("  |  |  |  |( (  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  | ) ) |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |(_(  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        System.out.println("*********************************************");
    }
    public static void printTurnsKitten() {
        System.out.println("                        Cat: I'm watching you!");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println(" ____|____|____|____|____|____|____|____|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|___|_         ____|____|____|____|____|__");
        System.out.println(" |____|____|____|____|___|    (\\.-./)  _|____|____|____|___|___|_ ");
        System.out.println("____|____|____|____|____|_  = (^ Y ^) =  _|____|____|____|____|__");
        System.out.println("|____|____|____|____|____|___ /`---`\\ __|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|____|_U___|_U|____|____|____|____|____|_  ");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println("____|____|____|____|____|____|____|____|____|____|____|____|____|_");
    }
    public static void printPlayerCats() {
        System.out.println("                      /^--^\\     /^--^\\     /^--^\\");
        System.out.println("                      \\____/     \\____/     \\____/");
        System.out.println("                     /      \\   /      \\   /      \\");
        System.out.println("                    |        | |        | |        |");
        System.out.println("                     \\__  __/   \\__  __/   \\__  __/");
        System.out.println("|^|^|^|^|^|^|^|^|^|^|^|^\\ \\^|^|^|^/ /^|^|^|^|^\\ \\^|^|^|^|^|^|^|^|^|^|^|^|");
        System.out.println("| | | | | | | | | | | | |\\ \\| | |/ /| | | | | | \\ \\ | | | | | | | | | | |");
        System.out.println("########################/ /######\\ \\###########/ /#######################");
        System.out.println("| | | | | | | | | | | | \\/| | | | \\/| | | | | |\\/ | | | | | | | | | | | |");
        System.out.println("|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|");

    }
    public static void printLoserCat() {
        System.out.println("                        Cat: What a loser!");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println(" ____|____|____|____|____|____|____|____|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|___|_         ____|____|____|____|____|__");
        System.out.println(" |____|____|____|____|___|    (\\.-./)  _|____|____|____|___|___|_ ");
        System.out.println("____|____|____|____|____|_  = (^ Y ^) =  _|____|____|____|____|__");
        System.out.println("|____|____|____|____|____|___ /`---`\\ __|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|____|_U___|_U|____|____|____|____|____|_  ");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println("____|____|____|____|____|____|____|____|____|____|____|____|____|_");
    }
    public static void printWinnerCat() {
        System.out.println("                        Cat: Good job! Finally!");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println(" ____|____|____|____|____|____|____|____|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|___|_         ____|____|____|____|____|__");
        System.out.println(" |____|____|____|____|___|    (\\.-./)  _|____|____|____|___|___|_ ");
        System.out.println("____|____|____|____|____|_  = (^ Y ^) =  _|____|____|____|____|__");
        System.out.println("|____|____|____|____|____|___ /`---`\\ __|____|____|____|____|____|");
        System.out.println(" __|____|____|____|____|____|_U___|_U|____|____|____|____|____|_  ");
        System.out.println(" |____|____|____|____|____|____|____|____|____|____|____|____|____");
        System.out.println("____|____|____|____|____|____|____|____|____|____|____|____|____|_");
    }
    public static void printInstructions() {
        System.out.println("    _                ___       _.--.");
        System.out.println("    \\`.|\\..----...-'`   `-._.-'_.-'`");
        System.out.println("    /  ' `         ,       __.--'");
        System.out.println("    )/' _/     \\   `-_,   /");
        System.out.println("    `-'\" `\"\\_  ,_.-;_.-\\_ ',  ");
        System.out.println("        _.-'_./   {_.'   ; /");
        System.out.println("       {_.-``-'         {_/");
        System.out.println("*****************************************");
        System.out.println("*  Guess the letter or the entire word  *");
        System.out.println("*****************************************");
    }
}