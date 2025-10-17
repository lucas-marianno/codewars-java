public class Test {

  public static void assertEquals(String phraseA, String phraseB) throws Exception {
    if (phraseA.length() != phraseB.length())
      throw new Exception("\nNot equals!\nDifferent Length");

    int max = Math.max(phraseA.length(), phraseB.length());

    String buffer = "";
    for (int i = 0; i < max; i++) {
      if (phraseA.charAt(i) != phraseB.charAt(i)) {
        throw new Exception(
            "\nNot equals!\n\n" +
                phraseA + "\n" +
                phraseB + "\n" +
                buffer + "^");
      }
      buffer += " ";
    }
    System.out.println("Passed!");
  }
}
