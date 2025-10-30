import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.codewars.com/kata/52a78825cdfc2cfc87000005/train/java
 * 
 * [2 kyu] Evaluate mathematical expression
 * 
 * Instructions
 * Given a mathematical expression as a string you must return the result as a
 * number.
 * 
 * Numbers
 * Number may be both whole numbers and/or decimal numbers. The same goes for
 * the returned result.
 * 
 * Operators
 * You need to support the following mathematical operators:
 * 
 * Multiplication *
 * Division / (as floating point division)
 * Addition +
 * Subtraction -
 * Operators are always evaluated from left-to-right, and * and / must be
 * evaluated before + and -.
 * 
 * Parentheses
 * You need to support multiple levels of nested parentheses, ex. (2 / (2 +
 * 3.33) * 4) - -6
 * 
 * Whitespace
 * There may or may not be whitespace between numbers and operators.
 * 
 * An addition to this rule is that the minus sign (-) used for negating numbers
 * and parentheses will never be separated by whitespace. I.e all of the
 * following are valid expressions.
 * 
 * ```
 * 1-1 // 0
 * 1 -1 // 0
 * 1- 1 // 0
 * 1 - 1 // 0
 * 1- -1 // 2
 * 1 - -1 // 2
 * 1--1 // 2
 * 
 * 6 + -(4) // 2
 * 6 + -( -4) // 10
 * ```
 * 
 * And the following are invalid expressions
 * ```
 * 1 - - 1 // Invalid
 * 1- - 1 // Invalid
 * 6 + - (4) // Invalid
 * 6 + -(- 4) // Invalid
 * ```
 * 
 * Validation
 * You do not need to worry about validation - you will only receive valid
 * mathematical expressions following the above rules.
 * 
 * Restricted APIs
 * To keep up the difficulty of the kata, use of some classes and functions is
 * disallowed. Their names cannot appear in the solution file, even in comments
 * and variable names.
 */

public class MathEvaluator {

  public static void main(String[] args) {

    testCalculator();

  }

  public static double calculate(String expression) {
    expression = cleanExpr(expression);

    while (expression.indexOf("(") != -1) {
      expression = calcInnerPar(expression);
    }

    int lastSignIndex = getLastIndexOfSign(expression);

    if (lastSignIndex == -1) {
      return Double.parseDouble(expression);
    }
    char sign = expression.charAt(lastSignIndex);

    String firstTerm = expression.substring(0, lastSignIndex);
    String lastTerm = expression.substring(lastSignIndex + 1);

    double termA;
    double termB = Double.parseDouble(lastTerm);

    int beforeLastSignIndex = getLastIndexOfSign(firstTerm);

    if (lastSignIndex - 1 == beforeLastSignIndex) {// adjacent signs
      lastTerm = sign + lastTerm;
      termB = Double.parseDouble(lastTerm);
      sign = firstTerm.charAt(beforeLastSignIndex);
      firstTerm = firstTerm.substring(0, firstTerm.length() - 1);
      beforeLastSignIndex = getLastIndexOfSign(firstTerm);
    }

    if (beforeLastSignIndex == -1) { // no sign on termA
      termA = Double.parseDouble(firstTerm);
    } else {
      char beforeLastSign = firstTerm.charAt(beforeLastSignIndex);

      if (getSignValue(sign) > getSignValue(beforeLastSign)) {

        double midTerm = Double.parseDouble(firstTerm.substring(beforeLastSignIndex + 1));

        firstTerm = firstTerm.substring(0, beforeLastSignIndex);
        termB = evalTerms(midTerm, termB, sign);
        sign = beforeLastSign;
      }

      termA = calculate(firstTerm);
    }

    return evalTerms(termA, termB, sign);

  }

  private static String cleanExpr(String expr) {
    return expr
        .replaceAll("\s", "")
        .replaceAll("--", "+")
        .replaceAll("-\\+", "-")
        .replaceAll("\\+-", "-")
        .replaceAll("\\+\\+", "+");
  }

  private static String calcInnerPar(String expr) {

    // match only the innermost parenthesized expressions that do not contain
    // other parentheses.
    Pattern pattern = Pattern.compile("(\\([^\\(\\)]*\\))");
    Matcher matcher = pattern.matcher(expr);

    String replaced = matcher.replaceAll(match -> {
      String m = match.group();

      try {
        String mtNoPar = m.substring(1, m.length() - 1);
        return String.valueOf(calculate(mtNoPar));
      } catch (Exception e) {
        return m;
      }
    });

    return cleanExpr(replaced);
  }

  private static double evalTerms(double termA, double termB, char sign) {
    switch (sign) {
      case '+':
        return termA + termB;
      case '-':
        return termA - termB;
      case '*':
        return termA * termB;
      case '/':
        return termA / termB;
      default:
        return 0;
    }
  }

  /**
   * Returns the last index of a sign in a math expr as string.
   *
   * index `0` WILL NOT be checked. (the only sign that can be first is `-`
   * therefore it should be considered negation anyways)
   *
   * @param s String
   * @return
   */
  private static int getLastIndexOfSign(String s) {
    for (int i = s.length() - 1; i > 0; i--) {
      if ("-+*/".indexOf(s.charAt(i)) != -1) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Evaluates the given sign and returns its numerical value.
   *
   * The higher the value, the higher its importance in the order of operations
   * 
   * Returns `-1` if the sign is invalid.
   *
   * @param sign
   * @return
   */
  private static int getSignValue(char sign) {
    switch (sign) {
      case '*':
      case '/':
        return 1;
      case '+':
      case '-':
        return 0;
      default:
        return -1;
    }
  }

  public static void testCalculator() {
    try {
      // Test.assertEquals(calculate("1+1"), 2, "addition");
      // Test.assertEquals(calculate("1 - 1"), 0, "Subtraction");
      // Test.assertEquals(calculate("1* 1"), 1, "Multiplication");
      // Test.assertEquals(calculate("1 /1"), 1, "division");
      // Test.assertEquals(calculate("2*4-3"), 5, "expression");
      // Test.assertEquals(calculate("2-4*3"), -10, "expression 2");
      // Test.assertEquals(calculate("2*(4-3)"), 2, "parentheses");
      // Test.assertEquals(calculate("-123"), -123);
      // Test.assertEquals(calculate("123"), 123);
      // Test.assertEquals(calculate("2 /2+3 * 4.75- -6"), 21.25);
      // Test.assertEquals(calculate("12* 123"), 1476);
      // Test.assertEquals(calculate("2 / (2 + 3) * 4.33 - -6"), 7.732);
      // Test.assertEquals(calculate("12* -1"), -12);
      // Test.assertEquals(calculate("12* 123/-(-5 + 2)"), 492);
      // Test.assertEquals(calculate("((80 - (19)))"), 61);
      Test.assertEquals(calculate(
          "(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11)"),
          -1);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
