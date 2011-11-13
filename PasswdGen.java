/**
 * @author Xiao G. Wu
 * CS111A - Assignment 11
 * @version 1.0 11/12/2011
 */

import java.lang.StringBuilder;
import java.util.Scanner;
import java.util.Random;

/**
 * This class generates "pronounceable" passwords of specified length. 
 * Length is specified as a command line argument.
 */

public class PasswdGen {
    // Specify how many passwords to generate per batch
    final static int NUM_PASSWDS_PER_PATCH = 10; 

    /**
     * Main method
     */

    public static void main(String[] args) {
        // Read from stdio
        Scanner keyboard = new Scanner(System.in);
        boolean keepGoing = false;
        int passwdLength = 0;
        // Extra Credit toggle
        boolean ec = false;

        // Check to make sure user entered an argument for password length.  
        // If not provide usage.
        if (args.length != 1) {
            System.out.println("Please specify one argument");
            System.out.println("Usage: PasswdGen minLength");
        } else {
            keepGoing = true;
            // Convert argument to integer
            passwdLength = Integer.parseInt(args[0]);
            // TODO Make sure it's an integer
        }

        while (keepGoing) {
            // Call password generator
            generatePasswds(passwdLength, ec);
            System.out.print("Press Enter key for new list, type 'EC' to toggle extra credit,  or type quit to exit: "); 
            // Read input
            String userInput = keyboard.nextLine();
            // Check for Quit condition
            if (userInput.equals("Quit") || userInput.equals("quit")) {
                keepGoing = false;
            } // Check for Extra Credit condition
            else if (userInput.equals("EC")) {
                ec = !ec; // toggle EC
            }
        }
    }
    
    /**
     * This method generates and prints the passwords
     * @param minLegth minimum password length
     * @param ec extra Credit toggle
     */

    public static void generatePasswds(int minLength, boolean ec) {
        // POOLS of choices
        // Common English digraphs
        String[] digraphs = {"th", "er", "on", "an", "re", "he", "in", "ed", "nd",
            "ha", "at", "en", "es", "of", "or", "nt", "ea", "ti", "to", "it", "st",
            "io", "le", "is", "ou", "ar", "as", "de", "rt", "ve"};
        // Common English trigraphs
        String[] trigraphs = {"the", "and", "tha", "ent", "ion", "tio", "for", 
            "nde", "has", "nce", "edt", "tis", "oft", "sth", "men"}; 
        // Decimal Numbers
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        // Common English Punctuations
        String[] puncts = {"'", "[", "]", "(", ")", "{", "}", "<", ">", ":", 
            ",", "-", "!", ".", "?", ";", "/"};
        // English Capital Letters
        String[] caps = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};
        // Create random number
        Random ran = new Random();
        // Create StringBuilder
        StringBuilder passwd = new StringBuilder();
        for (int i = 1; i <= NUM_PASSWDS_PER_PATCH; i++) {
            while (passwd.length() < minLength) { // While less than min passwd length
                int nextPick = 0;

                if (ec) { // Check for EC condition, if true pull from all pools
                    nextPick = ran.nextInt(5);
                }
                else { // Default is to only pull from di/tri-graph pools
                    nextPick = ran.nextInt(2);
                }

                // Check to see which pool to pull from and append to passwd
                if (nextPick == 0) { // Pick a random digraph
                    passwd.append(digraphs[ran.nextInt(digraphs.length)]);
                }
                else if (nextPick == 1) { // Pick a random trigraph
                    passwd.append(trigraphs[ran.nextInt(trigraphs.length)]);
                }
                else if (nextPick == 2) { // Pick a random number
                    passwd.append(nums[ran.nextInt(nums.length)]); 
                }
                else if (nextPick == 3) { // Pick a random punctuation
                    passwd.append(puncts[ran.nextInt(puncts.length)]);
                }
                else { // Pick a random capital character
                    passwd.append(caps[ran.nextInt(caps.length)]);
                }
            } 
            // Print passwd
            System.out.printf("%d:\t%s\n", i, passwd);
            // Reset passwd
            passwd.delete(0, passwd.length());
        }
    }
}
