/*
 * https://www.codewars.com/kata/52fba66badcd10859f00097e/train/java
 * [7 kyu] Disemvowel Trolls
 * 
 * Trolls are attacking your comment section!
 *
 * A common way to deal with this situation is to remove all of the vowels from
 * the trolls' comments, neutralizing the threat.
 *
 * Your task is to write a function that takes a string and return a new string
 * with all vowels removed.
 *
 * For example, the string "This website is for losers LOL!" would become
 * "Ths wbst s fr lsrs LL!".
 *
 * Note: for this kata y isn't considered a vowel.
 */

public class Troll {
  public static void main(String[] args) {
    testDisemvowel();
  }

  public static String disemvowel(String str) {
    return str.replaceAll("[AEIOUaeiou]", "");
  }

  public static String disemvowel_old(String str) {
    StringBuffer newStr = new StringBuffer();

    for (int i = 0; i < str.length(); i++) {
      char currentChar = str.charAt(i);

      if ("AEIOUaeiou".indexOf(currentChar) != -1)
        continue;

      newStr.append(currentChar);
    }
    return newStr.toString();
  }

  public static void testDisemvowel() {
    try {
      Test.assertEquals("Ths wbst s fr lsrs LL!", Troll.disemvowel("This website is for losers LOL!"));
      Test.assertEquals("N ffns bt,\nYr wrtng s mng th wrst 'v vr rd",
          Troll.disemvowel("No offense but,\nYour writing is among the worst I've ever read"));
      Test.assertEquals("Wht r y,  cmmnst?", Troll.disemvowel("What are you, a communist?"));

    } catch (Exception e) {
      System.out.println("Testing failed!!!");
      System.out.println(e);
    }
  }

}
