public class Test {

  public static void assertEquals(String actual, String expected, String description) throws Exception {

    if (description != "") {
      System.out.println(description);
    }

    int actualLen = actual.length();
    int expectedLen = expected.length();
    int min = Math.min(actualLen, expectedLen);

    boolean isDifferentLength = actualLen != expectedLen;

    String buffer = "";
    for (int i = 0; i < min; i++) {
      if (actual.charAt(i) != expected.charAt(i)) {
        throw new Exception(description + " Failed! ❌" +
            "\n\nNot equals!\n" +
            "Expected: '" + expected + "'\n" +
            "Got:      '" + actual + "'\n" +
            "           " + buffer + "^");
      }
      buffer += " ";
    }
    if (isDifferentLength) {
      throw new Exception(description + " Failed! ❌" +
          "\n\nNot equals!\n" +
          "Expected: '" + expected + "'\n" +
          "Got:      '" + actual + "'\n" +
          "           " + buffer + "^");
    }
    System.out.println(description + " Passed! ✅");
  }

  public static void assertEquals(String actual, String expected) throws Exception {
    assertEquals(actual, expected, "");
  }

  ////////////////////////// NUMBER ///////////////////////////////
  public static void assertEquals(Number actual, Number expected, String description) throws Exception {

    if (actual.doubleValue() == expected.doubleValue()) {
      System.out.println(description + " Passed! ✅");
      return;
    }
    assertEquals(String.valueOf(actual), String.valueOf(expected), description);
  }

  public static void assertEquals(Number actual, Number expected) throws Exception {
    assertEquals(actual, expected, "");
  }

}
