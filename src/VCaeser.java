/* VCaeser.java
    Description: This creates multiple Caeser alphabets based
    on the passcode entered and repeated for the length of the
    message to encrypt or decrypt. For each letter in the passcode,
    and alphabet is created that is shifted to the letter in the passcode
    which is then used to encrypt the message.
 */

// Import arraylist
import java.util.ArrayList;

public class VCaeser
{
    // Stores the passCode for encryption
    private String password = "";
    // This is an array list that contains one or more Caeser shifted alphabets
    private ArrayList<Caeser> passwordAlpha;
    // Stores the message user sets
    private String message = "";
    //Stores old message with capitalization
    private String messageOriginal = "";

    // Get the length of the message
    private int length;

    // Decrypted message
    private String messageUnEncrypted = "";
    // Encrypted message
    private String messageEncrypted = "";

    // Make a basic alphabet for easy comparison
    private ArrayList<String> alpha;


    // Constructor accepts password and message as parameters
    // This can also be used as a Caeser cipher if only one letter is entered
    // for the password
    public VCaeser(String password, String message)
    {
        // Set password and message
        messageOriginal = message;

        // To Uppercase for easier encryption/decryption
        // on both message and password
        this.password = password.toUpperCase();
        this.message = message.toUpperCase();

        //Set length of message
        length = message.length();

        // Make the basic alphabet
        alpha = new ArrayList<String>();
        // Declare char at A to start alphabet
        char ch = 65;

        // Add entire alphabet to arraylist
        for (int i = 0; i < 26; i++)
        {
            // Add letter to alphabet
            alpha.add(ch + "");
            // Next letter
            ch++;
        }
    }

    /* Getter methods */

    // Returns the encrypted message as a String
    public String getMessageEncrypted()
    {
        // Fix the capitalization in the message returned
        return fixCapitals(messageEncrypted);
    }
    // Returns the decrypted message as a String
    public String getMessageUnEncrypted()
    {
        // Fix the capitalization in the message returned
        return fixCapitals(messageUnEncrypted);
    }
    
    /* Setter methods */

    // Set the password (can be one letter for just Caeser)
    public void setPassword(String password)
    {
        this.password = password.toUpperCase();
        passwordAlpha();
    }
    // Set the message (can be either encrypted or unencrypted)
    public void setMessage(String message)
    {
        messageOriginal = message;
        this.message = message.toUpperCase();
    }

    /* Other methods */

    /* This method returns the capitalization back
     * into an encrypted or decrypted message after an operation is
     * is performed on it
     */
    public String fixCapitals(String s)
    {
        // Set temp string
        String temp = "";

        // Loop through length of string and compare to original message
        // To fix capitialization
        for(int i=0; i<length; i++)
        {
            // If old message was uppercase at this
            // point then leave uppercase
            if(messageOriginal.charAt(i) >= 65 &&
                    messageOriginal.charAt(i) <= 90)
            {
                temp += s.charAt(i) + "";
            }
            // If old message was lowercase at this point,
            // then change to lowercase
            else if(messageOriginal.charAt(i) >= 97 &&
                    messageOriginal.charAt(i) <= 122)
            {
                temp += (s.charAt(i) + "").toLowerCase();
            }
            // Ignore puncuation and spaces
            else {
                temp += s.charAt(i);
            }
        }

        // Return the fixed string
        return temp;
    }

    /* This method makes multiple new alphabets in
     * a Caeser cipher using array lists and the passcode that passed in
     * on creating the V cipher
     */
    public void passwordAlpha()
    {
        // New array list containing multiple alphabets
        // From the Caeser object, or only one if just
        // doing a basic Caeser cipher
        passwordAlpha = new ArrayList<Caeser>();

        // Make Caeser alphabets for each character
        // in the password and store in ArrayList same length as
        // the password
        for(int i=0; i<password.length(); i++)
        {
            Caeser c = new Caeser(password.charAt(i));
            passwordAlpha.add(c);
        }
    }

    /* This method encrypts the message in a Vigenere
     * Cipher or Ceaser if passcode is just one letter
     * by creating new alphabets according to the passcode, and
     * comparing their positions in the shifted alphabet */
    public void encrypt()
    {
        // String to return
        String temp = "";

        // Keep track of count and index
        int count = 0;
        int index = 0;

        // Go through entire message length to encrypt each letter
        for(int i=0; i<length; i++)
        {
            // Make sure it is a letter, else don't encrypt
            // Message already converted to uppercase at this point
            if(message.charAt(i) >= 65 &&
                    message.charAt(i) <=90)
            {
                // Find index of current letter in unecrypted message
                // In a normal alphabet
                index = alpha.indexOf(message.charAt(i)+ "");

				/* Add to encrypted message by
				 * Finding the character in the Caeser alphabet
				 * that corresponds to the position of the unecrypted
				 * character in the message. We use the passcode count to
				 * figure out which alphabet to use here. */
                temp += passwordAlpha.get(count).getAlpha().get(index);

                // Add to count and rollover if we are on the repeat of the
                // password
                count++;

                // Check if we need to rollover
                if(count == password.length()) {
                    count = 0;
                }
            }
            // Just add punctuation / space / special chars as normal
            else {
                temp += message.charAt(i);
            }
        }

        // Set the ecnrypted message to the temp created here
        messageEncrypted = temp;
    }


    /* Decrypts the cipher using the oppisite method of the one above */
    public void decrypt()
    {
        // Set a temp method to get the decrypted message
        String temp = "";

        // Keep track of count and index
        int count = 0;
        int index = 0;

        // Go through entire message length to decrypt each letter
        for(int i=0; i<length; i++)
        {
            // Make sure char is a letter
            if(message.charAt(i) >= 65 &&
                    message.charAt(i) <=90)
            {
                // Find index of current char in encrypted message and using the passcode
                // alphabet created
                index = passwordAlpha.get(count).getAlpha().indexOf(message.charAt(i)+ "");

				/* Add to unencrypted message by
				* finding the character in the normal alphabet that corresponds to the 
				* index found above */
                temp += alpha.get(index);

                // Add to count and rollover if password finished
                count++;

                // Rollover
                if(count == password.length()) {
                    count = 0;
                }
            }
            // Add punctuation / space / special chars as normal
            else
                temp += message.charAt(i);
        }

        // Set the unencrypted message to temp
        messageUnEncrypted = temp;
    }
}
