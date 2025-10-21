public class CreatePhoneNumber {
  public static void main(String[] args) {
    testCreatePhoneNumber();
  }

  public static String createPhoneNumber(int[] numbers) {
    return String.format("(%s%s%s) %s%s%s-%s%s%s%s", numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
  }

  private static void testCreatePhoneNumber() {
    try {
      Test.assertEquals(createPhoneNumber(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }), "(123) 456-7890");
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
