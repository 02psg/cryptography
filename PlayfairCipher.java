import java.util.Scanner;

public class PlayfairCipher {

    
    public static final int SIZE=5;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        char matrix[][] = generateKeyMatrix(key);
        printMatrix(matrix);

        String plaintext = scanner.nextLine();
        System.out.println(plaintext);

       String ciphertext= encrypt(plaintext, matrix);
       System.out.println(ciphertext);
        

       

        scanner.close();
    }
    private static char[][] generateKeyMatrix(String key) {
        char[][] keyMatrix = new char[SIZE][SIZE];
        boolean[] taken = new boolean[26];

        
        int row = 0, col = 0;

        for (char ch : key.toCharArray()) {
            if (ch == 'J') {
                ch = 'I'; // Replace 'J' with 'I'
            }

            if (!taken[ch - 'A']) {
                keyMatrix[row][col] = ch;
                taken[ch - 'A'] = true;
                col++;

                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') {
                continue;
            }

            if (!taken[ch - 'A']) {
                keyMatrix[row][col] = ch;
                col++;

                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        return keyMatrix;
    }
    
    public static void printMatrix(char matrix[][]){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){

                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
    private static String encrypt(String plaintext, char[][] keyMatrix) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char first = plaintext.charAt(i);
            char second = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int[] posFirst = findPosition(first, keyMatrix);
            int[] posSecond = findPosition(second, keyMatrix);

            if (posFirst[0] == posSecond[0]) { 
                ciphertext.append(keyMatrix[posFirst[0]][(posFirst[1] + 1) % SIZE]);
                ciphertext.append(keyMatrix[posSecond[0]][(posSecond[1] + 1) % SIZE]);
            } else if (posFirst[1] == posSecond[1]) {
                ciphertext.append(keyMatrix[(posFirst[0] + 1) % SIZE][posFirst[1]]);
                ciphertext.append(keyMatrix[(posSecond[0] + 1) % SIZE][posSecond[1]]);
            } else {
                ciphertext.append(keyMatrix[posFirst[0]][posSecond[1]]);
                ciphertext.append(keyMatrix[posSecond[0]][posFirst[1]]);
            }
        }

        return ciphertext.toString();
    }
    private static int[] findPosition(char ch, char[][] keyMatrix) {
        int[] position = new int[2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keyMatrix[i][j] == ch) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return position;
    }

}

