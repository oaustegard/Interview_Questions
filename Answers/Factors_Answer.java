/* Factors of a Number

Define a static utility function in a separate class that creates a collection of all the numbers that are factors of the user's number (> 1). For example, if the function is called factor, factor(36) should return {1,2,3,4,6,9,12,18,36}. The numbers in your list should be unique and ordered from least to greatest, and 1 and the original number should be included.
*/
import java.util.ArrayList;
import java.lang.Math; 
import java.lang.IllegalArgumentException;

class Factors_Answer {
  public static void example() {
    System.out.print("factor(1): ");
    System.out.println(factor(1));
    
    System.out.print("factor(36): ");
    System.out.println(factor(36));
    
    System.out.print("factor(49): ");
    System.out.println(factor(49));
    
    System.out.print("factor(523): ");
    System.out.println(factor(523));
    
    System.out.print("factor(123456789): ");
    System.out.println(factor(123456789));
  }

  /** 
  * Returns an ordered list of factors for a given input integral number
  * E.g. factor(36) should return {1,2,3,4,6,9,12,18,36}
  * 
  * @param int the number whose factors to return
  * @return ArrayList<Integer> the number's factors
  * @throws IllegalArgumentException If the entered number is less than 1 
  **/
  public static ArrayList<Integer> factor(int number)
  {
    //check for illegal parameters
    if (number < 1){
      throw new IllegalArgumentException("Please enter an integer greater or equal to 1");
    }

    ArrayList<Integer> factorList = new ArrayList<Integer>();
    factorList.add(1);
    if (number > 1) {
      factorList.add(number);
    }
    //iterate up to the max factor: sqrt(number)
    for (int f=2, f2, pos=1; f <= (int)Math.sqrt(number); f++) {
      //if a factor add both factors to the middle of the list
      if (number % f == 0) {
        factorList.add(pos, f);
        //get the second factor - ensure it's not a duplicate (i.e. sqrt(number))
        f2 = number / f;
        if (f2 != f) {
          factorList.add(pos+1, f2);
        }
        pos++;
      }
    }
    return factorList;
  }

}