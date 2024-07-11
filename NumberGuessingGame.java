package Task_2;


import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors

/**
 *
 * @author EMNA
 */

public class NumberGuessingGame {

    static int calculateScoreFunction(int attempt) {

        switch (attempt) {
            case 1:
                return 100;
            case 2:
                return 50;
            case 3:
                return 25;
            default:
                return 0;

        }

    }

    static void GuessNumberFunction(int number, int attempt, int round) throws IOException {
       
        FileWriter file = new FileWriter("./test.txt", true); // to store data
        file.write("----------------------------ROUND " + round + "---------------------------\n");
        Scanner scan = new Scanner(System.in);
      
        System.out.println("Guess the number ?");
          
        int score;
        int guess = scan.nextInt();
        while (guess != number) {
            if (guess < number)
                System.out.println("Lesser than Generated Number!!");
            else if (guess > number)
                System.out.println("Higher than Generated Number!!");
            attempt++;
            if (attempt > 3) {
                System.out.println("You have exceeded the number of attempts");
                score = calculateScoreFunction(attempt);
                System.out.println("Your score is: " + score);
                file.write("Your score is :" + score + "\n"); // Write in the file
                break;
            }
            System.out.println("Guess the number ?");
            guess = scan.nextInt();
            System.out.println("Nombre of attempt " + attempt);
        }
        if (guess == number) {
            System.out.println("!!!! BINGO !!!!");
            score = calculateScoreFunction(attempt);
            System.out.println("Your score is: " + score);
            file.write("Your score is :" + score + "\n");

        }
        file.close(); // Close file

    }

    public static void main(String[] args) throws IOException { // Main
        File file2 = new File("./test.txt");
        Scanner scanner = new Scanner(file2);
        try (Scanner scan = new Scanner(System.in)) {
            Random rand = new Random();
            int maximum = 100, minimum = 1, attempt = 1, round = 1;
            System.out.println(
                    "Generated Number are within " + minimum + " and " + maximum + " And you've got 3 Attempts");
            int number = rand.nextInt(maximum - minimum + 1) + minimum;
            System.out.println("Number generated is (Demo purpose) " + number);
            GuessNumberFunction(number, attempt, round);
            while (true) {
                System.out.println("Do you want to have another round ? [Y/N]");
                char answer = scan.next().charAt(0);
                answer = Character.toUpperCase(answer);
                if (answer == 'Y') {
                    round++;
                    number = rand.nextInt(maximum - minimum + 1) + minimum;
                    System.out.println("Number generated is (Demo purpose) " + number);
                    GuessNumberFunction(number, attempt, round);
                }

                else {
                    System.out.println("*************************ROUND PLAYED*************************");

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
                    scanner.close();
                    break;

                }

            }

        }

        catch (Exception e) {
            System.out.println("Something went wrong.");
        }

    }

}