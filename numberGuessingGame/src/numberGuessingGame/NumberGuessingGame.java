package numberGuessingGame;
import java.util.*;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        int lowestAttempts = Integer.MAX_VALUE;
        
        // Main loop for playing the game
        while(playAgain) {
            int currentAttempts = playGame(scanner, random);
            
            // Update lowestAttempts if currentAttempts is lower
            if (currentAttempts < lowestAttempts) {
                lowestAttempts = currentAttempts;
                System.out.println("Congratulations! New Lowest number of Attempts: " + lowestAttempts);
            }
            
            // Prompt for playing again
            System.out.print("let's go for next round(yes/no) : ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }
        System.out.println("Thanks for playing! ");    
        scanner.close();    
    }
    
    // Method to play a single game
    private static int playGame(Scanner scanner, Random random) {
        System.out.println("Welcome to the Number World! Let's start Guessing...");
        System.out.println("Difficulty levels:-");
        System.out.println("1.Easy 1-15, 5 attempts ");
        System.out.println("2.Medium 1-15, 3 attempts");
        System.out.println("3.Hard 1-20, 3 attempts");
        
        // Choosing difficulty level
        int difficultyChoice = 0;
        while(difficultyChoice < 1 || difficultyChoice > 3) {
            System.out.println();
            System.out.print("Enter your difficulty level: ");
            if(!scanner.hasNextInt()) {
                System.out.print("Invalid Input! Enter the valid number: ");
                scanner.next();
                continue;        
            }
            
            difficultyChoice = scanner.nextInt();
            if(difficultyChoice < 1 || difficultyChoice > 3 ) {
                System.out.println("Invalid Input! Enter the number (1 or 2 or 3):  ");
            }
        }
        
        // Setting maxNumber and maxAttempts based on difficulty
        int maxNumber;
        int maxAttempts;
        switch(difficultyChoice) {
            case 1:
                maxNumber = 15;
                maxAttempts = 5;
                break;
            case 2:
                maxNumber = 15;
                maxAttempts = 3;
                break;
            case 3:
                maxNumber = 20;
                maxAttempts = 3;
                break;
            default:
                maxNumber = 10;
                maxAttempts = 5;
        }
        
        // Generating target number
        int target_number = random.nextInt(maxNumber)+1;
        int attempts = 0;
        
        // Guessing loop
        while(attempts < maxAttempts) {
            System.out.println("Attempts: "+ (attempts+1) + "(Remaining attempts: " + (maxAttempts - attempts -1) + ")");
            System.out.print("Guess the Number(1 - "+maxNumber+"): ");
            
            // Handling non-integer inputs
            if(!scanner.hasNextInt()) {
                System.out.println("Invalid Input! Enter the valid Number: ");
                scanner.nextInt();
                continue;
            }
            
            int user_number = scanner.nextInt();
            
            // Comparing user's guess with target number
            if(target_number == user_number) {
                System.out.println("Congratulations!! Your guess is right👍");
                System.out.println("Wish you guess everything correctly in your life hereafter.");
                int currentAttempts = attempts + 1;
                return currentAttempts;
            }
            else if(target_number > user_number) {
                System.out.println("The Number is higher than you guess");
            }
            else {
                System.out.println("The Number is lower than you guess");
            }
            
            attempts++;
        }
        // Displaying message if maxAttempts reached
        if(attempts == maxAttempts) {
            System.out.println("Sorry, you've reached the maximum number of attempts.");
            System.out.println("The Correct Number: "+target_number);
            System.out.println("I guess, you're going to play it again!!");
        }
        
        return Integer.MAX_VALUE;
    }
}
