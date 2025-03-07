import java.util.Scanner;

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
    public static String addTwoHexString(String hex1, String hex2){
        double result = hexToDec(hex1) + hexToDec(hex2);
        return decToHex(result);
    }
    
    /**
     * Subtracts two hexadecimal numbers
     * @param hex1
     * @param hex2
     * @return result of subtraction in string
     */
    public static String subtractTwoHexString(String hex1, String hex2){
        double result = hexToDec(hex1) - hexToDec(hex2);
        return decToHex(Math.abs(result));
    }

    /**
     * Multiplies two Hexadecimal numbers
     * @param hex1
     * @param hex2
     * @return result of multiplication in string
     */
    public static String multiplyTwoHexString(String hex1, String hex2){
        double result = hexToDec(hex1) * hexToDec(hex2);
        return decToHex(result);
    }

    /**
     * Divides two Hexadecimal Numbers.
     * @param hex1
     * @param hex2
     * @return result of division in form of string.
     */
    public static String divideTwoHexString(String hex1, String hex2){
        double result = hexToDec(hex1) / hexToDec(hex2);
        return decToHex(result);
    }

    /**
     * Checks if given hexadecimal values are equal or not.
     * @param hex1
     * @param hex2
     * @return boolean value
     */
    public static boolean isEquals(String hex1, String hex2){
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

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
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
        String hex1, hex2;
        while(true){
            System.out.println("=======  Welcome to Hex Calculator  =======");
            System.out.println("1. Arithmetic Operations");
            System.out.println("2. Logical Operations");
            System.out.println("3. Conversion Operations");
            System.out.println("4. Exit the Calculator");
            System.out.println("===========================================");
            System.out.print("Enter the number of your choice: ");
            int operationType = getNumInput(sc, 1, 4);
            if(operationType == 1){
                String arithmeticResult = null;
                System.out.println("========  Arithmetic Operations  =========");
                System.out.println("1. Addition of two hex numbers");
                System.out.println("2. Subtraction of two hex numbers");
                System.out.println("3. Multiplication of two hex numbers");
                System.out.println("4. Division of two hex numbers");
                System.out.println("==========================================");
                System.out.print("Enter the number of your choice: ");
                operationType = getNumInput(sc, 1, 4);
                System.out.print("Enter first hexadecimal number: ");
                hex1 = sc.next();
                System.out.print("Enter second hexadecimal number: ");
                hex2 = sc.next();
                switch(operationType){
                    case 1:
                        arithmeticResult = addTwoHexString(hex1, hex2);
                        break;
                    case 2:
                        arithmeticResult = subtractTwoHexString(hex1, hex2);
                        break;
                    case 3:
                        arithmeticResult = multiplyTwoHexString(hex1, hex2);
                        break;
                    case 4:
                        arithmeticResult = divideTwoHexString(hex1, hex2);
                        break;
                }
                System.out.println("Result = " + arithmeticResult);
            }else if(operationType == 2){
                boolean logicalResult = true;
                System.out.println("========   Logical Operations   =========");
                System.out.println("1. Equality of two hex numbers");
                System.out.println("2. Greater of two hex numbers");
                System.out.println("3. Smaller of two hex numbers");
                System.out.println("==========================================");
                System.out.print("Enter the number of your choice: ");
                operationType = getNumInput(sc, 1, 3);
                System.out.print("Enter first hexadecimal number: ");
                hex1 = sc.next();
                System.out.print("Enter second hexadecimal number: ");
                hex2 = sc.next();
                switch(operationType){
                    case 1:
                        logicalResult = isEquals(hex1, hex2);
                        break;
                    case 2:
                        logicalResult = isGreaterThan(hex1, hex2);
                        break;
                    case 3:
                        logicalResult = isLessThan(hex1, hex2);
                        break;
                }
                System.out.println("Result = " + logicalResult);
            }else if(operationType == 3){
                double decimalNum;
                System.out.println("========  Conversion Operations  =========");
                System.out.println("1. Decimal to Hexadecimal");
                System.out.println("2. Hexadecimal to Decimal");
                System.out.println("==========================================");
                System.out.print("Enter the number of your choice: ");
                operationType = getNumInput(sc, 1, 2);
                switch(operationType){
                    case 1:
                        System.out.println("Enter the decimal number to be conerted: ");
                        decimalNum = sc.nextDouble();
                        System.out.println("Result = " + decToHex(decimalNum));
                        break;
                    case 2:
                        System.out.println("Enter the hexadecimal number to be converted: ");
                        hex1 = sc.next();
                        System.out.println("Result = " + hexToDec(hex1));
                        break;
                }
            }else{
                break;
            }
            System.out.println("\n\n");
        }
    }
}
