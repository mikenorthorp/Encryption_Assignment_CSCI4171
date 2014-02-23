import java.util.ArrayList;

/**
 * This is a matrix transposition cipher that uses a passcode and number
 * of columns. The passcode is the order the columns of the matrix should be
 * in.
 */
public class MatrixTranspo {

    // Stores the passCode for encryption
    private String password = "";
    // Stores the number of columns for encryption
    private int numColumns;
    // Stores the number of rows for encryption
    private double numRows;
    // Stores the message user sets
    private String message = "";

    // Create a multidimensional char array for encryption / decryption
    char[][] multiChar;

    // Get the length of the message
    private int length;

    // Decrypted message
    private String messageUnEncrypted = "";
    // Encrypted message
    private String messageEncrypted = "";

    // Make a basic alphabet for easy comparison
    private ArrayList<String> alpha;

    // This constructor takes in a number of coulmns and a passcode based on the number of
    // columns given and a message
    public MatrixTranspo(int numColumns, String passCode, String message) {
        // Set everything up
        this.message = message;
        this.numColumns = numColumns;
        this.password = passCode;

        this.length = message.length();

        // Set up the multichar array for encryption
        setMultiChar();
    }

     /* Getter methods */

    // Returns the encrypted message as a String
    public String getMessageEncrypted()
    {
        // Fix the capitalization in the message returned
        return messageEncrypted;
    }
    // Returns the decrypted message as a String
    public String getMessageUnEncrypted()
    {
        // Fix the capitalization in the message returned
        return messageUnEncrypted;
    }

    /* Setter methods */

    // Set the password (can be one letter for just Caeser)
    public void setPassword(String password)
    {
        this.password = password;
    }
    // Set the message (can be either encrypted or unencrypted)
    public void setMessage(String message)
    {
        this.message = message;
        this.length = message.length();
        setMultiChar();
    }

    // Set the number of columns
    public void setColumns(int numColumns)
    {
        this.numColumns = numColumns;
        setMultiChar();
    }

    // Set up the multidimensional char array
    public void setMultiChar() {
        // Figure out how big the array should be to create
        this.numRows = Math.ceil((double)this.length/this.numColumns);

        // Set up multichar array for encryption
        multiChar = new char[(int)numRows][numColumns];
    }

    /* Encrypt the message */
    public void encrypt() {
        // Put the message into the multi dimensional char array
        int messageIndex = 0;
        for(int i=0; i < numRows; i++) {
            for(int j=0; j < numColumns; j++) {
                if(messageIndex < length) {
                    multiChar[i][j] = message.charAt(messageIndex);
                } else {
                    multiChar[i][j] = ' ';
                }

                // Increase message index
                messageIndex++;
            }
        }

        // Set the temp string to calculate encrypted message
        String temp = "";
        for(int i=0; i < numColumns; i++) {
            for(int j=0; j < numRows; j++) {
                temp += multiChar[j][Integer.parseInt(password.charAt(i) + "")-1] + "";
            }
        }

        messageEncrypted = temp;
    }


    /* Decrypt the message */
    public void decrypt() {

        // Temp string for unencrypted message
        String temp = "";

        // Set up index to go through entire array
        int messageIndex = 0;
        // Put the message into a multi dimensional char array according to
        // the password columnwise, to be able to read it across later
        for(int i=0; i < numColumns; i++) {
            for(int j=0; j < numRows; j++) {
                if(messageIndex < length) {
                    multiChar[j][Integer.parseInt(password.charAt(i) + "")-1] = message.charAt(messageIndex);
                } else {
                    multiChar[j][Integer.parseInt(password.charAt(i) + "")-1] = ' ';
                }

                // Increase message index
                messageIndex++;
            }
        }

        // Read out the decrypted message
        for(int i=0; i < numRows; i++) {
            for(int j=0; j < numColumns; j++) {
                temp += multiChar[i][j];
            }
        }

        // Set the unencrypted message
        messageUnEncrypted = temp;
    }
}
