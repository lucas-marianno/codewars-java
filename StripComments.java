import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * [4 kyu] Strip Comments
 *
 * https://www.codewars.com/kata/51c8e37cee245da6b40000bd/train/java
 *
 * Complete the solution so that it strips all text that follows any of a set of
 * comment markers passed in. Any whitespace at the end of the line should also
 * be stripped out.
 * 
 * Example:
 * 
 * Given an input string of:
 * ```
 * apples, pears # and bananas
 * grapes
 * bananas !apples
 * ```
 * 
 * The output expected would be:
 * 
 * apples, pears
 * grapes
 * bananas
 */
public class StripComments {

  public static String stripComments(String text, String[] commentSymbols) {

    // commentSymbols = sanitizeRegexSymbols(commentSymbols);

    String rgx = "(?m)\\s*$|\\s*[" + String.join("", commentSymbols) + "].*$";
    return text.replaceAll(rgx, "");
  }

  private static String[] sanitizeRegexSymbols(String[] commentSymbols) {
    final String rgxSymbols = "()[]{}|\\^$.*+?";

    for (int i = 0; i < commentSymbols.length; i++) {
      if (rgxSymbols.indexOf(commentSymbols[i]) != -1) {
        commentSymbols[i] = "\\" + commentSymbols[i];
      }
    }
    return commentSymbols;
  }

  public static void main(String[] args) {
    test();
  }

  private static void test() {
    try {
      Test.assertEquals(
          stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" }),
          "apples, pears\ngrapes\nbananas");
      Test.assertEquals(
          stripComments("a #b\nc\nd $e f g", new String[] { "#", "$" }),
          "a\nc\nd");
      Test.assertEquals(
          stripComments("a \n b \nc ", new String[] { "#", "$" }),
          "a\n b\nc");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
