
import java.util.Scanner;

public class CustomStringOperations{

    /**
     * Compares two strings character by character.
     * @param str1 First string.
     * @param str2 Second string.
     * @return True if both strings are equal, otherwise False.
     */
    public boolean compareStrings(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        for(int i = 0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Reverses the given string.
     * @param str Input string.
     * @return Reversed string.
     */
    public String reverseString(String str){
        char[] reversed = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            reversed[str.length() - 1 - i] = str.charAt(i);
        }
        return new String(reversed);
    }

    /**
     * Converts lowercase letters to uppercase and vice versa.
     * @param str Input string.
     * @return String with swap case characters.
     */
    public String swapCase(String str){
        char []result = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                result[i] = (char)(ch - 'a' + 'A');
            }else if(ch >= 'A' && ch <= 'Z'){
                result[i] = (char)(ch - 'A' + 'a');
            }else{
                result[i] = ch;
            }
        }
        return new String(result);
    }

    /**
     * Finds and returns the longest word in the given string.
     * If multiple words have the same length, returns the last occuring longest word.
     * @param str string containing words seperated by spaces.
     * @return The longest word from the string.
     */
    public String getLargestWord(String str){
        int maxLength = 0;
        StringBuilder currWord = new StringBuilder();
        String longestWord = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ' '){
                currWord.append(str.charAt(i));
            }else{
                if(currWord.length() >= maxLength){
                    longestWord = currWord.toString();
                    maxLength = currWord.length();
                }
                currWord.setLength(0);
            }
        }
        if(currWord.length() >= maxLength){
            longestWord = currWord.toString();
        }
        return longestWord;
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc Scanner class object.
     * @param min Lowest range of input.
     * @param max Highest range of input.
     * @return Valid integer between the range.
     */
    public static int getNumInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomStringOperations obj = new CustomStringOperations();
        boolean flag = true;
        while(flag){
            System.out.println("====== Welcome to String Modifier ======");
            System.out.println("1. Compare two srings.");
            System.out.println("2. Reverse a String.");
            System.out.println("3. Swap case of string characters.");
            System.out.println("4. Get longest word from the string.");
            System.out.println("5. Exit the program.");
            System.out.println("========================================");
            System.out.print("Enter the Option number you want to choose: ");
            int choice = getNumInput(sc, 1, 5);
            String str1, str2;
            switch(choice){
                case 1:
                    System.out.print("Enter First String: ");
                    str1 = sc.nextLine();
                    System.out.print("Enter Second String: ");
                    str2 = sc.nextLine();
                    System.out.println("Result = " + obj.compareStrings(str1, str2));
                    break;
                case 2:
                    System.out.print("Enter the string: ");
                    str1 = sc.nextLine();
                    System.out.println("Reversed string = " + obj.reverseString(str1));
                    break;
                case 3:
                    System.out.print("Enter the string: ");
                    str1 = sc.nextLine();
                    System.out.println("Swapped case string = " + obj.swapCase(str1));
                    break;
                case 4:
                    System.out.print("Enter the string: ");
                    str1 = sc.nextLine();
                    System.out.println("Largest word = " + obj.getLargestWord(str1));
                    break;
                default:
                    flag = false;
            }
            System.out.println("\n\n");
        }
    }
}