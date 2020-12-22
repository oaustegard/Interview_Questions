import java.lang.IllegalArgumentException;
import java.time.*;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* What's My Number
Between 1 and 1,000,000, there is only 1 number that meets the following criteria. Write a program to find out which number meets these criteria: 

-The number has two or more digits.
-The number is prime.
-The number does NOT contain a 0 or 1.
-The sum of all of the digits is less than or equal to 10.
-The first two digits add up to be odd.
-The second to last digit is even.
-The last digit is equal to how many digits are in the number.
*/
// 523

class WhatsMyNumber_Answer {
    
  //execution counters
  static int e2, eprime, e17, ef2o, eless10, e2leven, eld;
    
  public static void example() {
    Instant start = Instant.now();

    List<String> result = IntStream.rangeClosed(10, 3223) // cursory filter based on the criteria 
      .filter(i -> {
        return true
          //the order of these will determine total time taken - balance of expense and effectiveness 
          && secondToLastDigitIsEven(i)
          && lastDigitEqualToDigitCount(i)
          //&& hasTwoOrMoreDigits(i) //not really necessary
          && firstTwoDigitsSumIsOdd(i)
          && sumOfDigitsIsLessThanOrEqualTo10(i)
          && doesNotContainZeroOrOne(i)
          && isPrime(i)
        ;
      })
      .mapToObj(i -> String.valueOf(i))
      .collect(Collectors.toList());
    
    Instant finish = Instant.now();

    System.out.println("Matching Numbers: " + result.stream().collect(Collectors.joining(", ")));
      
    System.out.format("e2=%d, eprime=%d, e17=%d, ef2o=%d, eless10=%d, e2leven=%d, eld=%d%n", 
      e2, eprime, e17, ef2o, eless10, e2leven, eld);
    System.out.format("total executions = %d%n", e2+eprime+e17+ef2o+eless10+e2leven+eld);
    System.out.format("total duration = %dms%n", Duration.between(start, finish).toMillis());  
  }

  private static boolean hasTwoOrMoreDigits(int i) {
    e2++;
    return getDigitCount(i) >= 2;
  }

  private static boolean isPrime(int i) {
    eprime++;
    for (int n=2; n<Math.sqrt(i); n++){
      if (i % n == 0) {return false;}
    }
    return true;
  }

  private static boolean doesNotContainZeroOrOne(int i) {
    e17++;
    return !(String.valueOf(i).contains("0") 
      || String.valueOf(i).contains("1"));
  }

  private static boolean firstTwoDigitsSumIsOdd(int i){
    ef2o++;
    if (i <= 99){
      return false;
    }
    int firstTwo = Integer.parseInt(String.valueOf(i).substring(0,2));
    return sumOfDigits(firstTwo) % 2 > 0;
  }

  private static boolean sumOfDigitsIsLessThanOrEqualTo10(int i) {
    eless10++;
    if (i < 0) { throw new IllegalArgumentException("i must be >= 0"); }

    return sumOfDigits(i) <= 10;
  }

  private static boolean secondToLastDigitIsEven(int i) {
    e2leven++;
    return getReverseDigitValue(i, 2) % 2 ==0;
  }

  private static boolean lastDigitEqualToDigitCount(int i) {
    eld++;
    return getReverseDigitValue(i, 1) == getDigitCount(i);
  }
  
  private static int sumOfDigits(int i) {
    if (i < 0) { throw new IllegalArgumentException("i must be >= 0"); }
    
    int sum = 0;
    for (int n=1; n<=getDigitCount(i); n++){
      sum += getReverseDigitValue(i, n);
    }
    return sum;
  }

  private static int getDigitCount(int i){
    if (i < 0) { throw new IllegalArgumentException("i must be >= 0"); }
    return (int)Math.log10(i) + 1;
  }

  private static int getReverseDigitValue(int i, int pos){
    if (i < 0) { throw new IllegalArgumentException("i must be >= 0"); }
    if (pos < 1) { throw new IllegalArgumentException("pos must be >= 1"); }
    return (i % (int)Math.pow(10, pos)) / (int)Math.pow(10, pos-1);
  }

}