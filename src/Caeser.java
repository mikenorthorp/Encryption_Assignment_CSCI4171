/* Caeser.java
 * This object creates a new alphabet based
 * on the Caeser Cipher by taking in 
 * a letter from the alphabet and shifting
 * A to that position in the alphabet. 
 */

// Import array list
import java.util.ArrayList;

public class Caeser
{
    // Declare an array list for the shifted alphabet
    private ArrayList<String> shiftedAlphabet;

    // String to make sure the char is uppercase
    private String upperCheck = "";

    // Constructor creates a shifted alphabet
    public Caeser(char ch)
    {
        // Make sure the char passed in is converted to uppercase
        upperCheck = "" + ch;
        upperCheck = upperCheck.toUpperCase();
        ch = upperCheck.charAt(0);

        // Make array list for the shifted alphabet
        shiftedAlphabet = new ArrayList<String>();

        // A-Z runs 65 - 90, loop over and create the new alphabet
        for (int i = 0; i < 26; i++)
        {
            // Add letter to new alphabet, starting with the one passed
            // in
            shiftedAlphabet.add(ch + "");
            // Go to next letter
            ch++;

            // Rollover if goes past Z
            if(ch == 91){
                ch = 65;
            }
        }
    }

    // Getter method to return shifted alphabet for use in 
    // Normal Caeser cipher or Vigenere cipher
    public ArrayList<String> getAlpha()
    {
        return shiftedAlphabet;
    }
}
