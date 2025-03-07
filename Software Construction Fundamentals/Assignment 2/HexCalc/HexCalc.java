
public class HexCalc {

    private static final String DIGITS = "0123456789ABCDEF";

    /**
     * Converts a hexadecimal number to decimalNum number.
     * @param hexNum
     * @return decimal number in form of floating number.
     */
    public static double hexToDec(String hexNum){
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
    public static String decToHex(double decimalNum){
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
    public static String add(String hex1, String hex2){
        double result = hexToDec(hex1) + hexToDec(hex2);
        return decToHex(result);
    }
    
    /**
     * Subtracts two hexadecimal numbers
     * @param hex1
     * @param hex2
     * @return result of subtraction in string
     */
    public static String subtract(String hex1, String hex2){
        double result = hexToDec(hex1) - hexToDec(hex2);
        return decToHex(Math.abs(result));
    }

    /**
     * Multiplies two Hexadecimal numbers
     * @param hex1
     * @param hex2
     * @return result of multiplication in string
     */
    public static String multiply(String hex1, String hex2){
        double result = hexToDec(hex1) * hexToDec(hex2);
        return decToHex(result);
    }

    /**
     * Divides two Hexadecimal Numbers.
     * @param hex1
     * @param hex2
     * @return result of division in form of string.
     */
    public static String divide(String hex1, String hex2){
        double result = hexToDec(hex1) / hexToDec(hex2);
        return decToHex(result);
    }

    /**
     * Checks if given hexadecimal values are equal or not.
     * @param hex1
     * @param hex2
     * @return boolean value
     */
    public static boolean equals(String hex1, String hex2){
        return hex1.equals(hex2);
    }

    /**
     * Checks if Given first hex string is greater than second hex string or not.
     * @param hex1
     * @param hex2
     * @return boolean value.
     */
    public static boolean isGreaterThan(String hex1, String hex2){
        int hex1Length = hex1.length();
        int hex2Length = hex2.length();
        if(hex1Length < hex2Length){
            hex1 = "0".repeat(hex2Length-hex1Length) + hex1;
        }else{
            hex2 = "0".repeat(hex1Length - hex2Length) + hex2;
        }
        int result = hex1.compareTo(hex2);
        return result > 0;
    }

    /**
     * Checks if Given first hex string is less than second hex string or not.
     * @param hex1
     * @param hex2
     * @return boolean value.
     */
    public static boolean isLessThan(String hex1, String hex2){
        int hex1Length = hex1.length();
        int hex2Length = hex2.length();

        if(hex1Length < hex2Length){
            hex1 = "0".repeat(hex2Length - hex1Length) + hex1;
        }else{
            hex2 = "0".repeat(hex1Length - hex2Length) + hex2;
        }
        int result = hex1.compareToIgnoreCase(hex2);
        return result > 0;
    }

    public static void main(String[] args) {
        
    }
}
