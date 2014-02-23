/* encryption.java
   This program contains methods to encrypted a messaged entered by the user
   by using the Caeser cipher, matrix transposition cipher, or Vigenere cipher
 */

// Import scanner
import java.util.Scanner;

public class encryption
{

    // Set up globals for messages

    // Set up all the message strings
    public static String messageOriginal = "";
    public static String messageEncrypt = "";
    public static String messageDecrypt = "";

    /* Runs the Vigenere cipher or Caeser using a passcode and multiple or one shifted alphabets
       It sets the global message variables which are printed out in the main method
     */
    public static void runVigenereOrCaeser(String passCode, String message) {
        // Set up the cipher object
        VCaeser vEncrypt = new VCaeser(passCode, messageOriginal);

        // Generate the password alphabet using the passcode
        vEncrypt.passwordAlpha();

        // Encrypt the message
        vEncrypt.encrypt();

        // Set the encrypted message
        messageEncrypt = vEncrypt.getMessageEncrypted();

        // Set the encrypted message in the cipher object
        vEncrypt.setMessage(messageEncrypt);

        // Set the passcode to decrypt
        vEncrypt.setPassword(passCode);

        // Decrypt the message
        vEncrypt.decrypt();

        // Set the decrypted message to the one from the cipher object
        messageDecrypt = vEncrypt.getMessageUnEncrypted();
    }

    /* Runs the matrix transposition cipher */
    public static void runMatrixTranspo(int numColumns, String passCode, String message) {
        // Set up the cipher object
        MatrixTranspo mEncrypt = new MatrixTranspo(numColumns, passCode, messageOriginal);

        // Encrypt the message
        mEncrypt.encrypt();

        // Set the encrypted message
        messageEncrypt = mEncrypt.getMessageEncrypted();

        // Decrypt the message
        // Give it the correct password
        mEncrypt.setPassword(passCode);
        mEncrypt.setColumns(passCode.length());
        // Set the message to the encrypted message
        mEncrypt.setMessage(messageEncrypt);

        // Decrypt the message
        mEncrypt.decrypt();

        // Set the decrypted message
        messageDecrypt = mEncrypt.getMessageUnEncrypted();
    }

    /* This main function selects the type of encryption, and passcode if necessary, and
       the message to be encrypted. It then encrypts it then decyrpts it as necessary.
     */
    public static void main(String[] args)
    {
        // Set up line scanner for input
        Scanner scan = new Scanner(System.in);

        // Input from user for choice
        int optionNumber = 0;
        // Get user to select which encryption method to use
        System.out.print("Welcome to the super secure line Mr. President, please enter a message: ");
        // Get the message
        messageOriginal = scan.nextLine();

        System.out.println("Your message is:");
        System.out.println(messageOriginal);

        // Give user the three options
        System.out.println("\n1 Ceaser\n2 Vigenere Cipher\n3 Matrix Transposition");
        System.out.print("Please select an encryption method above by typing in the number: ");

        // Get user input
        String temp = "";
        do {
            temp = scan.next();
            // Flush next line
            scan.nextLine();
            optionNumber = Integer.parseInt(temp);
            if(optionNumber < 1 || optionNumber > 3) {
                System.out.print("Please enter a valid option: ");
            }
        } while(optionNumber < 1 || optionNumber > 3);

        // Set up variables for use in case statments
        String passCode = "";
        String numColumns = "";
        // Call the encryption type chosen, and encrypt / decrypt the message
        switch(optionNumber) {
            // Ceaser cipher
            case 1:
                // Get the single char to shift by from the user
                System.out.print("Please enter a letter to shift the alphabet by for " +
                        "encryption: ");
                do{
                    passCode = scan.next();
                    // Flush next line
                    scan.nextLine();
                    // Make sure the passcode entered is just one char so the entire alphabet
                    // is shifted and it acts like a normal Caeser cipher
                    if(passCode.length() > 1 || passCode.length() == 0) {
                        System.out.print("Please enter one letter to shift the alphabet by: ");
                    }
                } while(passCode.length() > 1 || passCode.length() == 0);

                // Perform the encryption / decryption
                runVigenereOrCaeser(passCode, messageOriginal);
                break;
            // Vigenere cipher
            case 2:
                // Get the passcode for the Vigenere cipher to make alphabets
                System.out.print("Please enter a passcode for " +
                        "encryption: ");
                passCode = scan.next();
                // Flush next line
                scan.nextLine();

                // Perform the encryption / decryption
                runVigenereOrCaeser(passCode, messageOriginal);
                break;
            // Matrix transposition cipher
            case 3:
                // Get the number of columns and the key
                System.out.print("Please the number of columns for the encryption: ");
                numColumns = scan.next();

                // Flush next line
                scan.nextLine();

                System.out.print("Please the order of the columns for the encryption key: ");
                passCode = scan.next();

                // Flush next line
                scan.nextLine();

                // Perform the encryption / decryption
                runMatrixTranspo(Integer.parseInt(numColumns), passCode, messageOriginal);
                break;
            default:
                break;
        }

        // Show the encrypted and decrypted messages
        System.out.println("Your encrypted message is:\n\t" + messageEncrypt);
        System.out.println("Your decrypted message is:\n\t" + messageDecrypt);
    }
}
