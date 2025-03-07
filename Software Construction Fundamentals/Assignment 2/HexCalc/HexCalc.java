import java.util.Scanner;

public class HexCalc {

    private static final String DIGITS = "0123456789ABCDEF";

    /**
     * Converts a hexadecimal number to decimalNum number.
     * @param hexNum
     * @return decimal number in form of floating number.
     */
    public double hexToDec(String hexNum){
        hexNum = hexNum.toUpperCase();
        int indexOfDot = -1;
        for(int i=0; i < hexNum.length(); i++){
            if(hexNum.charAt(i) == '.'){
                indexOfDot = i;
                break;
            }
        }
        double decimalNum = 0;
        int base = 16;
        int integerPart = (indexOfDot == -1 ? hexNum.length() : indexOfDot);

        for(int i=0; i<integerPart; i++){
            decimalNum = decimalNum * base + DIGITS.indexOf(hexNum.charAt(i));
        }
        if(indexOfDot != -1){
            double fractionPart = 0;
            double mult = 1.0/base;
            for(int i = indexOfDot+1; i < hexNum.length(); i++){
                fractionPart += DIGITS.indexOf(hexNum.charAt(i)) * mult;
                mult /= base;
            }
            decimalNum += fractionPart;
        } 
        return decimalNum;
    }
    
    /**
     * Converts a given decimal number into a hexadecimal string.
     * @param decimalNum
     * @return Hexadecimal number in form of String.
     */
    public String decToHex(double decimalNum){
        if(decimalNum == 0)return "0";
        int integerPart = (int)decimalNum;
        double fractionPart = decimalNum - integerPart;

        int base = 16;
        String hexNumber = "";
        while(integerPart > 0){
            hexNumber = DIGITS.charAt(integerPart % 16) + hexNumber;
            integerPart /= base;
        }
        if(fractionPart != 0){
            hexNumber += ".";
            while(fractionPart > 0){
                fractionPart *= 16;
                int intPart = (int)fractionPart;
                fractionPart -= intPart;
                hexNumber += DIGITS.charAt(intPart);
            }
        }
        return hexNumber;
    }

    /**
     * Adds two Hexadecimal Numbers
     * @param hex1
     * @param hex2
     * @return result of addition in string
     */
    public String add(String hex1, String hex2){
        double result = hexToDec(hex1) + hexToDec(hex2);
        return decToHex(result);
    }
    
    /**
     * Subtracts two hexadecimal number
     * @param hex1
     * @param hex2
     * @return result of subtraction in string
     */
    public String subtract(String hex1, String hex2){
        double result = hexToDec(hex1) - hexToDec(hex2);
        return decToHex(Math.abs(result));
    }

    public String multiply(String hex1, String hex2){
        double result = hexToDec(hex1) * hexToDec(hex2);
        return decToHex(result);
    }

    public String divide(String hex1, String hex2){
        double result = hexToDec(hex1) / hexToDec(hex2);
        return decToHex(result);
    }

    public boolean equals(String hex1, String hex2){
        return hex1 == hex2;
    }

    public static boolean isGreaterThan(String hex1, String hex2){
        int hex1Length = hex1.length();
        int hex2Length = hex2.length();
        if(hex1Length < hex2Length){
            hex1 = "0".repeat(hex2Length-hex1Length) + hex1;
        }else{
            hex2 = "0".repeat(hex1Length - hex2Length) + hex2;
        }
        int result = hex1.compareTo(hex2);
        if(result > 0){
            return true;
        }
        return false;
    }

    public static void compare(String s1, String s2) {
        int i= s1.compareToIgnoreCase(s2);
        
        System.out.println("What You want : \n 1. S1==S2 \n 2. S1>S2 \n 3. S1<S2 ");
        Scanner scan = new Scanner(System.in);
        int choice=scan.nextInt();
        switch(choice) {
        case 1:if(i==0) {System.out.println(true); }
        else System.out.println(false);
        break;
        case 2: if(i>0)
            System.out.println(true);
        else
            System.out.println(false);
        break;
        case 3: if(i<0)
            System.out.println(true);
        else
            System.out.println(false);
        break;
        default: System.out.println("Invalid Selection");
            
            
        }
        
        
    }


    public static boolean isLessThan(String hex1, String hex2){
        int hex1Length = hex1.length();
        int hex2Length = hex2.length();
        if(hex1Length < hex2Length){
            hex1 = "0".repeat(hex2Length - hex1Length) + hex1;
        }else{
            hex2 = "0".repeat(hex1Length - hex2Length) + hex2;
        }
        compare(hex1, hex2);
        // if(result > 0){
        //     return true;
        // }
        // return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isLessThan("E9.20", "E9.10"));
    }
}
