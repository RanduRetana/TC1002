package Clase1;

import java.util.Arrays;
public class BasicSintax {
    public static int reverseInt(int n) {
        int reversed = 0;
        while (n != 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }
        return reversed;
    }

    public static void primeNumber(int n) {
        int i = 2;
        boolean isPrime = true;
        while (i < n) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        if (isPrime) {
            System.out.println(n + " is prime");
        } else {
            System.out.println(n + " is not prime");
        }
    }

    public static float standardDeviation(Float[] n) {
        float sum = 0;
        for (float i : n) {
            sum += i;
        }
        float mean = sum / n.length;
        float sumOfSquares = 0;
        for (float i : n) {
            sumOfSquares += (i - mean) * (i - mean);
        }
        return (float) Math.sqrt(sumOfSquares / n.length);
    }

    public static int[] invertIntArray(int[] arr) {
        int[] inverted = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            inverted[i] = arr[arr.length - i - 1];
        }
        return inverted;
    }

    public static void main (String[] args) {
        System.out.println(reverseInt(98765));
        primeNumber(13159);
        primeNumber(13160);
        Float[] n = {1.f, 2.f, 3.f, 4.f, 5.f, 6.f, 7.f, 8.f, 9.f, 10.f};
        System.out.println(standardDeviation(n));
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(invertIntArray(arr)));
    }
}
