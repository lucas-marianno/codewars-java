public class Test {

  public static void assertEquals(String actual, String expected, String description) throws Exception {

    if (description != "") {
      System.out.println(description);
    }

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

  public static void assertEquals(String actual, String expected) throws Exception {
    assertEquals(actual, expected, "");
  }

  //////////////////////////  NUMBER ///////////////////////////////
  public static void assertEquals(Number actual, Number expected, String description) throws Exception {
    assertEquals(String.valueOf(actual), String.valueOf(expected), description);
  }

  public static void assertEquals(Number actual, Number expected) throws Exception {
    assertEquals(String.valueOf(actual), String.valueOf(expected));
  }

}
