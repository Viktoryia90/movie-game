import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Game.introduction();

        // Get movies list from file
        File file = new File("movies.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // declare variables outside the while-loop to make them available
        String movie = "";
        // this is dynamic array with size that can be changed:
        ArrayList<String> allMovies = new ArrayList<>();
        while (scanner.hasNextLine()) {
            // find the list of all movies in a file
            movie = scanner.nextLine();
            // add each movie to the ArrayList
            allMovies.add(movie);
        }

        //Find the length of array with movies
        // You cannot use .length - for ArrayList .size() method is need
        int movieArrayLength = allMovies.size();

        //Get 1 random movie name from the ArrayList
        int random = new Random().nextInt(movieArrayLength);
        String randomMovie = allMovies.get(random);

        // Change String with movie name to char array
        char[] movieLetters = randomMovie.toCharArray();
        char[] movieLettersToUnderscore;
        movieLettersToUnderscore = movieLetters.clone();
        int charArrayLength = movieLettersToUnderscore.length;
        System.out.println("There are " + (charArrayLength + 1) + " symbols.");

        // replace letters with "_"s
        for (int i = 0; i < charArrayLength; i++) {
            if (movieLettersToUnderscore[i] != ' ') {
                movieLettersToUnderscore[i] = '_';
            }
        }
        System.out.println(movieLettersToUnderscore);

        // Take input from user
        Scanner input = new Scanner(System.in);
        int i = 10;
        String usedLetters = "You have named: ";
        while (i > 0) {
            System.out.println("Input your letter.");
            char guess = input.next(".").charAt(0);
            boolean goodAnswer = false;
            usedLetters = usedLetters + guess + ", ";

            // compare user's guess with movie
            for (int j = 0; j < movieLetters.length; j++) {
                if (guess == movieLetters[j]) {
                    //int findLetter = randomMovie.indexOf(guess, j);
                    movieLettersToUnderscore[j] = guess;
                    goodAnswer = true;
                }
            }
            if (!goodAnswer) {
                i--;
                System.out.println("You have " + i + " mistake(s) left.");
            }

            String leftLetters = new String(movieLettersToUnderscore);
            int x = leftLetters.indexOf('_');
            if (x == -1) {
                System.out.println(movieLettersToUnderscore);
                System.out.println("You win!");
                System.exit(0);
            }

            System.out.println(usedLetters);
            System.out.println(movieLettersToUnderscore);

        }
        System.out.println("You Loose! The correct answer was:");
        System.out.println(randomMovie);
    }
}