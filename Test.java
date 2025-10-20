public class Test {

  public static void assertEquals(String actual, String expected) throws Exception {

    int max = Math.max(actual.length(), expected.length());

    String buffer = "";
    for (int i = 0; i < max; i++) {
      if (actual.charAt(i) != expected.charAt(i)) {
        throw new Exception(
            "\n\nNot equals!\n" +
                "Expected: '" + expected + "'\n" +
                "Got:      '" + actual + "'\n" +
                "           " + buffer + "^");
      }
      buffer += " ";
    }
    System.out.println("Passed!");
  }

  public static void assertEquals(Number n1, Number n2) throws Exception {
    assertEquals("" + n1, "" + n2);
  }
}
