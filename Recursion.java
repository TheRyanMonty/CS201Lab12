import java.io.PrintWriter;
import java.util.ArrayList;

public class Recursion {

    public static long factorialR(long value){
        if (value == 0) return 1;
        return value * factorialR(value - 1);
    }
    
    public static boolean palindromeR(String str, int start, int end){
        // base case: crossed or met in the middle → palindrome
        if (start >= end) return true;

        // if ends don't match → not a palindrome
        if (str.charAt(start) != str.charAt(end)) return false;

        // recursive step: move inward
        return palindromeR(str, start + 1, end - 1);
    }

    public static void reverseStringR(PrintWriter outputWriter, String str){
        // Base case: if string length < 1, stop
        if (str.length() < 1) return;

        // Recursive call with substring (everything except first char)
        reverseStringR(outputWriter, str.substring(1));

        // After recursion, print first character (this reverses order)
        outputWriter.print(str.charAt(0));
    }

    public static boolean isPrimeR(int value, int n) {
        // base case 1: if n > sqrt(value), it’s prime
        if (n * n > value) return true;

        // base case 2: if divisible, not prime
        if (value % n == 0) return false;

        // recursive step
        return isPrimeR(value, n + 1);
    }
 
    public static int sumR(ArrayList<Integer> nums, int pos) {
        // Base case: when position == length, stop recursion
        if (pos == nums.size()) return 0;

        // Recursive step: current + sum of remaining
        return nums.get(pos) + sumR(nums, pos + 1);
    }
 

}

   