/*
https://www.codewars.com/kata/5552101f47fc5178b1000050/train/java
[6 kyu] Playing with digits

Some numbers have funny properties. For example:

89 --> 8¹ + 9² = 89 * 1
695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2
46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
Given two positive integers n and p, we want to find a positive integer k, if it exists, such that the sum of the digits of n raised to consecutive powers starting from p is equal to k * n.

If it is the case we will return k, if not return -1.

Note: n and p will always be strictly positive integers.

Examples:

n = 89; p = 1 ---> 1 since 8¹ + 9² = 89 = 89 * 1
n = 92; p = 1 ---> -1 since there is no k such that 9¹ + 2² equals 92 * k
n = 695; p = 2 ---> 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2
n = 46288; p = 3 ---> 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51

 */

import java.util.LinkedList;

public class DigPow {

  public static void main(String[] args) {
    int bbbb = 2;

    System.out.println(bbbb);

    // System.out.println(Math.pow(8, 1));
    // System.out.println(digPow(46288, 3));
    testDigPow();
  }

  private static long digPow(int n, int p) {
    int temp = n;
    LinkedList<Integer> digits = new LinkedList<>();

    while (temp > 0) {
      digits.addFirst(temp % 10);
      temp /= 10;
    }

    long product = 0;
    for (int d : digits) {
      product += (long) Math.pow(d, p++);
    }

    return product % n == 0 ? product / n : -1;
  }

  private static void testDigPow() {
    try {
      Test.assertEquals(digPow(89, 1), 1);
      Test.assertEquals(digPow(92, 1), -1);
      Test.assertEquals(digPow(695, 2), 2);
      Test.assertEquals(digPow(46288, 3), 51);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
