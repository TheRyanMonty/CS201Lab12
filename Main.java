//NAME: Ryan Montgomery
//DATE: 11/11/25
//ASSIGNMENT: Week 12 lab
//LECTURE SECTION: Tu/Thurs

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        String errorFile = "error.txt";

    System.out.println("Starting Recursion Program");
        try (BufferedReader reader   = new BufferedReader(new FileReader(inputFile));
             BufferedWriter errorW   = new BufferedWriter(new FileWriter(errorFile));
             PrintWriter outputW     = new PrintWriter(new FileWriter(outputFile))) {

            String line;
            //Read in input file
            while ((line = reader.readLine()) != null) {
                //Trim the line
                line = line.trim();
                //If the line is blank, iterate the loop
                if (line.length() == 0) continue;

                // factorialR
                if (line.startsWith("factorialR,")) {
                    String valuePart = line.substring(line.indexOf(",") + 1).trim();
                    try {
                        long value = Long.parseLong(valuePart);
                        if (value < 0) {
                            errorW.write("factorialR Error: " + line + ". The value: " + value + " is invalid.\n");
                        } else {
                            long result = Recursion.factorialR(value);
                            outputW.write("The factorial of " + value + " is " + result + "\n");
                        }
                    } catch (NumberFormatException e) {
                        errorW.write("factorialR Error: " + line + ". The value: " + valuePart + " is invalid.\n");
                    }
                }

                // isPrimeR
                else if (line.startsWith("isPrimeR,")) {
                    String valuePart = line.substring(line.indexOf(",") + 1).trim();
                    try {
                        int value = Integer.parseInt(valuePart);
                        if (value <= 1) {
                            outputW.write(value + " is a not prime number\n");
                        } else {
                            boolean prime = Recursion.isPrimeR(value, 2);
                            if (prime)
                                outputW.write(value + " is a prime number\n");
                            else
                                outputW.write(value + " is a not prime number\n");
                        }
                    } catch (NumberFormatException e) {
                        errorW.write("isPrimeR Error: isPrimeR," + valuePart +
                                ". The value: " + valuePart + " is invalid.\n");
                    }
                }

                // sumR
                else if (line.startsWith("sumR,")) {
                    String[] parts = line.split(",");
                    ArrayList<Integer> nums = new ArrayList<>();
                    boolean valid = true;

                    for (int i = 1; i < parts.length; i++) {
                        String token = parts[i].trim();
                        try {
                            nums.add(Integer.parseInt(token));
                        } catch (NumberFormatException e) {
                            errorW.write("sumR Error: The value " + token + " is invalid\n");
                            valid = false;
                            break;
                        }
                    }

                    if (valid && !nums.isEmpty()) {
                        int sum = Recursion.sumR(nums, 0);
                        outputW.write("The sum of the values: ");
                        for (int i = 0; i < nums.size(); i++) {
                            outputW.write(nums.get(i) + (i < nums.size() - 1 ? " " : ""));
                        }
                        outputW.write(" is " + sum + "\n");
                    }
                }

                // palindromeR
                else if (line.startsWith("palindromeR,")) {
                    String str = line.substring(line.indexOf(",") + 1).trim();
                    boolean isPal = Recursion.palindromeR(str, 0, str.length() - 1);
                    if (isPal)
                        outputW.write(str + " is a palindrome\n");
                    else
                        outputW.write(str + " is not a palindrome\n");
                }

                // reverseStringR
                else if (line.startsWith("reverseStringR,")) {
                    String str = line.substring(line.indexOf(",") + 1).trim();
                    outputW.print("The string " + str + " in reverse is: ");
                    Recursion.reverseStringR(outputW, str);
                    outputW.println();
                }

                // Check for anything else and error
                else {
                    String commandName = line.contains(",")
                            ? line.substring(0, line.indexOf(","))
                            : line;  // handle cases with no comma
                    errorW.write("Command Error: " + line +
                            ". Command entered is invalid — " + commandName + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }
 
    }

    public static boolean isDigits(String str){
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
