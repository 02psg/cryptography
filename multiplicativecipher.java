import java.util.*;
class HelloWorld {
    public static String encrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLowerCase(c)) {
                char encryptedChar = (char) (((c - 'a') * key % 26) + 'A');
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        int key;
        String text;

        while (true) {
            System.out.println("Multiplicative Cipher");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Brute Force Decryption");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1/2/3/4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter plaintext (lowercase only): ");
                    scanner.nextLine();  
                    text = scanner.nextLine();
                    System.out.print("Enter key (an integer coprime with 26): ");
                    key = scanner.nextInt();
                    String ciphertext = encrypt(text, key);
                    System.out.println("Encrypted message (uppercase): " + ciphertext);
                    break;
    }
}
}
}