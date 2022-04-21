package basic;

import io.vavr.control.Option;

/**
 * For this basic test, you should not use any library. e.g. you should not use Math.pow for power method
 */
public class BasicTest {

  /**
   * return i ^ n for positive Integer, None otherwise
   * alse return None in case of errors
   */
  public static Option<Integer> power(Integer i, Integer n) {
    if(i < 0 || n < 0) {
      return Option.none();
    }
    long res_test = 1;
    Integer res = 1;
    while(n >= 1)
    {
      res_test = res_test * i;
      if(res_test > Integer.MAX_VALUE)
        return Option.none();
      res = res * i;
      n--;
    }
    return Option.of((Integer)res);
  }
}
