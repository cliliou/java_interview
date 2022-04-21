package collection;

import io.vavr.control.Option;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * You should complete the function in this class
 * <p>
 * Feel free to define any method and / or class you want
 */
class CollectionTest {


  /**
   * operation : x -> ((x * 2) + 3) ^ 5
   */
  public static List<Double> compute1(List<Integer> input) {
    return input.parallelStream().mapToDouble(i->pow((i*2.0) + 3.0)).boxed().collect(Collectors.toList());
  }

  public static Double pow(Double i){
    Double res = 1.0;
    Double n = 5.0;
    while(n >= 1)
    {
      res = res * i;
      n--;
    }
    return res;
  }

  /**
   * operation : abc -> AbcAbc
   */
  public static List<String> compute2(List<String> input) {
    return input.parallelStream().map(i-> concat(i)).collect(Collectors.toList());
  }

  public static String concat(String i)
  {
    if(i.length() > 0) {
      return i.substring(0,1).toUpperCase() + i.substring(1) + i.substring(0,1).toUpperCase() + i.substring(1);
    }
    return "";
  }

}
