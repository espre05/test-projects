package dustin.examples;  
      
    import java.math.BigDecimal;  
    import java.math.MathContext;  
    import java.math.RoundingMode;  
    import java.util.ArrayList;  
    import java.util.List;  
      
    /** 
     * Class encapsulating quadratic formula implementation. 
     *  
     * @author Dustin 
     */  
    public class SimplisticQuadraticFormula  
    {  
       /** Default precision used for specifying scale. */  
       private static final int DEFAULT_PRECISION = MathContext.DECIMAL64.getPrecision();  
      
       /** Convenient representation of zero as BigDecimal. */  
       private static final BigDecimal ZERO = new BigDecimal("0");  
      
       /** Convenient representation of two as BigDecimal. */  
       private static final BigDecimal TWO = new BigDecimal("2");  
      
       /** Convenient representation of four as BigDecimal. */  
       private static final BigDecimal FOUR = new BigDecimal("4");  
      
       /** 
        * Calculate intercepts with x-axis. 
        *  
        * @param a Coefficient 'a' from a quadratic equation to be solved. 
        * @param b Coefficient 'b' from a quadratic equation to be solved. 
        * @param c Coefficient 'c' from a quadratic equation to be solved. 
        * @return The x-intercepts or solutions to the quadratic equation (two values) 
        *     or a single value if solution to a linear equation. Note that two 
        *     solutions are always provided for quadratic equations even if they are 
        *     the same. 
        * @throws NumberFormatException Thrown when x-intercepts cannot be calculated. 
        */  
       public static List<BigDecimal> calculateXIntercepts(  
          final BigDecimal a, final BigDecimal b, final BigDecimal c)  
       {  
          final List<BigDecimal> intercepts = new ArrayList<BigDecimal>();  
          if (a.compareTo(ZERO) == 0 && b.compareTo(ZERO) == 0)  
          {  
             // neither quadratic nor linear  
             throw new NumberFormatException("Must have coefficient for one of x terms.");         
          }  
          else if (a.compareTo(ZERO) == 0)  // linear equation  
          {  
             intercepts.add(c.setScale(DEFAULT_PRECISION).negate().divide(b, RoundingMode.HALF_UP));  
          }  
          else  
          {  
             final BigDecimal intercept1 =  
                calculateNumeratorWithAddition(a, b, c)  
                   .divide(calculateDenominator(a), RoundingMode.HALF_UP);  
             intercepts.add(intercept1);  
             final BigDecimal intercept2 =  
                calculateNumeratorWithSubtraction(a, b, c)  
                   .divide(calculateDenominator(a), RoundingMode.HALF_DOWN);  
             intercepts.add(intercept2);  
          }  
          return intercepts;  
       }  
      
       /** 
        * Calculate axis of symmetry, if applicable. 
        *  
        * @param a Coefficient 'a' from a quadratic equation to be solved. 
        * @param b Coefficient 'b' from a quadratic equation to be solved. 
        * @return The "x" axis of symmetry. 
        * @throws NumberFormatException Thrown if the provided 'a' coefficient is 
        *    zero because cannot divide by zero. 
        */  
       public static BigDecimal calculateAxisOfSymmetry(final BigDecimal a, final BigDecimal b)  
       {  
          if (a.compareTo(ZERO) == 0)  
          {  
             throw new NumberFormatException(  
                "Cannot calculate axis of symmetry based on x-intercepts when a is zero.");  
          }  
          return b.setScale(DEFAULT_PRECISION).negate().divide(a.multiply(TWO), RoundingMode.HALF_UP);  
       }  
      
       /** 
        * Calculate numerator of quadratic formula where the terms are added. 
        *  
        * @param a Coefficient 'a' from a quadratic equation to be solved. 
        * @param b Coefficient 'b' from a quadratic equation to be solved. 
        * @param c Coefficient 'c' from a quadratic equation to be solved. 
        * @return Value of numerator in quadratic formula where terms are added. 
        * @throws NumberFormatException Thrown if no real solution is available. 
        */  
       private static BigDecimal calculateNumeratorWithAddition(  
          final BigDecimal a, final BigDecimal b, final BigDecimal c)  
       {  
          return b.negate().add(calculateSquareRootPortion(a, b, c));  
       }  
      
       /** 
        * Calculate numerator of quadratic formula where the terms are subtracted. 
        *  
        * @param a Coefficient 'a' from a quadratic equation to be solved. 
        * @param b Coefficient 'b' from a quadratic equation to be solved. 
        * @param c Coefficient 'c' from a quadratic equation to be solved. 
        * @return Value of numerator in quadratic formula where terms are subtracted. 
        * @throws NumberFormatException Thrown if no real solution is available. 
        */  
       private static BigDecimal calculateNumeratorWithSubtraction(  
          final BigDecimal a, final BigDecimal b, final BigDecimal c)  
       {  
          return b.negate().subtract(calculateSquareRootPortion(a, b, c));  
       }  
      
       /** 
        * Calculate denominator of quadratic formula. 
        *  
        * @param a Coefficient of 'a' from a quadratic equation to be solved. 
        * @return Value of denominator in quadratic formula. 
        * @throws NumberFormatException Thrown in 0 is provided for coefficient 'a' 
        *    because denominator cannot be zero. 
        */  
       private static BigDecimal calculateDenominator(final BigDecimal a)  
       {  
          if (a.compareTo(ZERO) == 0)  
          {  
             throw new NumberFormatException("Denominator cannot be zero.");  
          }  
          return a.multiply(TWO);  
       }  
      
       /** 
        * Calculates value of square root portion of quadratic formula. 
        *  
        * @param a Coefficient 'a' from a quadratic equation to be solved. 
        * @param b Coefficient 'b' from a quadratic equation to be solved. 
        * @param c Coefficient 'c' from a quadratic equation to be solved. 
        * @return The square root portion of the quadratic formula applied with 
        *    the three provided co-efficients. 
        * @throws NumberFormatException Thrown if there is no solution (no 
        *    intersection of the x-axis) or if a number is encountered that cannot 
        *    be handled with BigDecmal return type. 
        */  
       private static BigDecimal calculateSquareRootPortion(  
          final BigDecimal a, final BigDecimal b, final BigDecimal c)  
       {  
          BigDecimal sqrt;  
          final BigDecimal subtrahend = a.multiply(c).multiply(FOUR);  
          final BigDecimal insideSqrt = b.pow(2).subtract(subtrahend);  
          if (insideSqrt.compareTo(ZERO) < 0)  
          {  
             throw new NumberFormatException("Cannot be solved: no x-intercepts.");  
          }  
          else  
          {  
             final double value = insideSqrt.doubleValue();  
             final double sqrtDouble = Math.sqrt(value);  
             sqrt = new BigDecimal(sqrtDouble);  // may throw NumberFormatException  
          }  
          return sqrt;  
       }  
    }  