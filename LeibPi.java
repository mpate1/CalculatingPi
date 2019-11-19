
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LeibPi {

	// Used to count iterations
	public static int count;
	// For screen input
	private static Scanner screenInput = new Scanner(System.in);


	// Uses the Leibniz formula with floats to calculate pi.
	// This is done by setting float variables and creating a while loop that performs calculations in order to retrieve pi.
	// A counter is added to determine the number of iterations that are performed in order to attain pi.
	// There are no parameters passed in this function.
	public static float piCalculatorLeibniz(){
		float pi = 0;
		float x = -1;
		float i = 0;

		while (x!=pi) {
			x = pi;
			pi += (Math.pow(-1, i)) / (2*i + 1);
			i++;
			// System.out.println("" + (int)i);
		}
		count = (int) i; //to count the number of iterations
		return pi * 4;
	}

	// Uses the second arctan formula with floats and uses the Taylor series to calculate the arctan to calculate pi.
	// Second part of the calculation using the arctan formula to return pi.
	// This part of the method performs the final calculations using the arcTanPiFloat method in order to return pi
	// There are no parameters passed through this method.
	public static float arcTanPiCalculatorFloat()  {
		float pi = 4* ((8*(arcTanPiFloat(1/10F)) - (4*(arcTanPiFloat(1/515F))) - arcTanPiFloat(1/239F)));
		return pi;
	}
	// This part of the method takes in the value used in the main method as a float and performs a range of calculations in order to return a value that is used in the previous method above.
	// First part of the calculation, using taylor series to calculate arctan.
	public static float arcTanPiFloat(float value) {
		float total = 0;
		float old;
		int i = 0;

		do {
			old = total;
			total += ((Math.pow(-1, i)) / (2*i + 1)) * (Math.pow(value, 2*i+1));
			i++;

		} while (old != total);
		count = (int) i;
		return total;
	}	

	// Same as above, but use a double instead.	
	// This is the exact same format as above, but instead of float, we are using double.
	// Follows the same functions.
	public static double arcTanPiCalculatorDouble()  {
		double pi = 4* ((8*(arcTanPiDouble((double)1/10)) - (4*(arcTanPiDouble((double)1/515))) - arcTanPiDouble((double)1/239)));
		return pi;
	}
	
	// Same methos as arcTanPiFloat, but uses double instead when performing the calculations to yield a more accurate value of pi.
	public static double arcTanPiDouble(double value) {

		double total = 0;
		double old;
		int i = 0;

		do {
			old = total;
			total += ((Math.pow(-1, i)) / (2*i + 1)) * (Math.pow(value, 2*i+1));
			i ++;

		} while (old != total);
		count = (int) i;
		return total;
	}	


	// Applying the Ramanujan's summation improvement technique learned in class to improve the accuracy of the above technique.
	// This part is similar to the arcTanPiCalculatorDouble method, however it calls the ramanujanCalculator method instead.
	// Returns the value of pi, and does not take in any parameters.
	public static double arcTanPiCalculatorDoubleModified()  {
		double pi = 4* ((8*(ramanujanCalculator((double)1/10)) - (4*(ramanujanCalculator((double)1/515))) - ramanujanCalculator((double)1/239)));
		return pi;
	}
	// This part uses the ramanujan technique in order to calculate arctan. 
	// This technique separates the positives and the negatives and uses a do while loop to calculate arctan.
	// The separation of signs performs the calculations more precisely yielding a better value of pi. 
	// When this method is called in the main method, a value is passed it, which is taken as a parameter for this method.
	public static double ramanujanCalculator (double value) {
		int k = 0;
		double negSum = 0;
		double posSum = 0;
		double prevNegSum;
		double prevPosSum;
		int sign = 1;
		double term;

		do {
			prevNegSum = negSum;
			prevPosSum = posSum;  

			term = ((double) 1 / (2*k + 1)) * (Math.pow(value, 2*k+1));
			if (sign > 0) {
				posSum += term;
			} else {
				negSum += term;
			}
			sign = -sign;
			k++;

		}
		while (!(negSum == prevNegSum && posSum == prevPosSum));
		count = (int) k;
		return (posSum - negSum);

	} 


	// Uses the BBP formula with a double to evaluate for pi.
	// This technique uses doubles and a do while loop to evaluate for pi.
	// There are variables initialized as double which are used throughout the method in order for simplify the final calculations.
	// The formula given on the assignment instruction is evaluated through the number of calculations.
	// This method takes in no parameters.
	public static double piCalculatorBBP() {
		double pi2 = 0;
		int n = 0;
		count = 0;
		double old;
		do
		{
			old = pi2;
			double a = (1/(Math.pow(16, n)));
			double b = (4 / ((8 * (double)n) + 1));
			double c = (2 / ((8 * (double)n) + 4));
			double d = (1 / ((8 * (double)n) + 5));
			double e = (1 / ((8 * (double)n) + 6));
			pi2 += a*( b - c - d - e );
			n++;

		} while (pi2 != old);
		count = (int) n; //to count the number of iterations
		return pi2;
	}	

	// Use the BBP fomula with BigDecimals to get any number of digits. (Technique 6 & 7)
	// Final variables are initialized outside the method in order to keep the calculations tidy
	// This method follows a similar format as the method above, but initializes the variables as bigDecimal instead of double.
	// The built-in java functions like RoundingMode is used throughout the method, so a value is retrieved for the calculations instead of the number of sigfigs going on forever.
	// An integer is passed through this method according to what is passed when this method is called in the main function. 
	// This is the same method that is used for technique 6 & 7.
	static final BigDecimal oneBD = new BigDecimal(1);
	static final BigDecimal fourBD = new BigDecimal(4);
	static final BigDecimal eightBD = new BigDecimal(8);
	static final BigDecimal fiveBD = new BigDecimal(5);
	static final BigDecimal twoBD = new BigDecimal(2);
	static final BigDecimal sixBD = new BigDecimal(6);
	static final BigDecimal sixteenBD = new BigDecimal(16);

	public static BigDecimal piCalculatorBBPBig(int digitCount)//static method which invoked here argument n is size of pi value we want to print
	{
		BigDecimal pi2 = new BigDecimal(0);
		int n = 0;
		BigDecimal prev;
		count = 0;
		do 
		{
			prev = pi2;
			BigDecimal a = oneBD.divide(sixteenBD).pow(n);
			BigDecimal b = fourBD.divide(eightBD.multiply(new BigDecimal(n)).add(oneBD),digitCount, RoundingMode.HALF_UP);
			BigDecimal c = twoBD.divide(eightBD.multiply(new BigDecimal(n)).add(fourBD),digitCount, RoundingMode.HALF_UP);
			BigDecimal d = oneBD.divide(eightBD.multiply(new BigDecimal(n)).add(fiveBD),digitCount, RoundingMode.HALF_UP);
			// (1 / ((8 * (double)n) + 5));
			BigDecimal e = oneBD.divide(eightBD.multiply(new BigDecimal(n)).add(sixBD),digitCount, RoundingMode.HALF_UP);
			// (1 / ((8 * (double)n) + 6));
			pi2= pi2.add(a.multiply(b.subtract(c).subtract(d).subtract(e)));
			n++;
			count = (int) n;
		} while
			(!prev.setScale(digitCount, RoundingMode.HALF_EVEN).equals(pi2.setScale(digitCount, RoundingMode.HALF_EVEN)));

		return pi2.setScale(digitCount, RoundingMode.HALF_EVEN);
	}


	// Aids in displaying BigDecimal numbers to the screen using 100
	// digits per line.p
	public static void displayResult(BigDecimal aNum) {
		var asString = aNum.toString();
		for(int i = 0; i < asString.length(); i++) {
			System.out.print(asString.charAt(i));
			if (i > 0 && (i + 1) % 100 == 0)
				System.out.println();
		}
		System.out.println();
	} // end displayResult

	// Simplifies reporting the execution time and the number of iterations
	public static void timeIterationsReport(long start) {
		long finishTime = System.nanoTime();
		long diff = finishTime - start;
		if (diff <= 1e3)
			System.out.print("Took " + diff + " nanosec., ");
		else if (diff <= 1e6)
			System.out.print("Took " + Math.round(diff / 10.0) / 100.0 + " microsec. ");
		else if (diff <= 1e9)
			System.out.print("Took " + Math.round(diff / 1e4) / 100.0 + " millisec. ");
		else
			System.out.print("Took " + Math.round(diff / 1e7) / 100.0 + " sec. ");
		System.out.println("and required " + count + " iterations.");
		count = 0;
	} // end timeReport

	// Used to calculate and display the accuracy of a 16 digit result using the value of
	// pi stored in the Math class.
	public static void accuracyReport(double estimate) {
		var error = 100 * (estimate - Math.PI) / Math.PI;
		System.out.printf("Error is %.2e percent.\n\n", error);
	} // end accuracyReport

	// Copied from IOHelper (only method needed)
	public static int getInt(int low, String prompt, int high) {
		int numFromUser = 0;
		String dummy;
		boolean numericEntryOK;
		do {
			System.out.print(prompt);
			numericEntryOK = false;
			try {
				numFromUser = screenInput.nextInt();
				numericEntryOK = true;
			} catch (InputMismatchException e) {
				dummy = screenInput.nextLine();
				System.out.println(dummy + " is not an integer!");
				numFromUser = low;
			} // end try-catch
			// Indicate to the user why he is being prompted again.
			if (numFromUser < low || numFromUser > high) {
				System.out.println("The number is outside the legal limits.");
			}
		} while (!numericEntryOK || numFromUser < low || numFromUser > high);
		return numFromUser;
	} // end full parameter getInt method

	// This supplied main method uses assumed method names that you may certainly change.
	public static void main(String[] args) {

		long startTime;
		double estimate;
		int numDigitsDesired;

		System.out.printf("Math.PI is:\n%.16f\n\n", Math.PI);

		startTime = System.nanoTime();
		estimate = piCalculatorLeibniz();
		System.out.printf("%.16f - using Leibniz formula with float.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);


		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorFloat(); 
		System.out.printf("%.16f - using arcTan formula with float.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate); 


		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorDouble();
		System.out.printf("%.16f - using arcTan formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);


		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorDoubleModified();
		System.out.printf("%.16f - using Modified arcTan formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		estimate = piCalculatorBBP();
		System.out.printf("%.16f - using BBP formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for 16 digits:");
		var estimateBigD = piCalculatorBBPBig(16);
		System.out.println(estimateBigD);
		timeIterationsReport(startTime);
		accuracyReport(estimateBigD.doubleValue());

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for 100 digits:");
		System.out.println(piCalculatorBBPBig(100));
		timeIterationsReport(startTime);
		System.out.println();

		numDigitsDesired = getInt(1000, "How many digits do you want to try for? ", 10000);

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for " + numDigitsDesired + " digits:");
		displayResult(piCalculatorBBPBig(numDigitsDesired));
		timeIterationsReport(startTime);

		screenInput.close();

	} // end main

} // end Assignment2Start


// Question 1: Provide a copy of the output of your program.
/*
Math.PI is:
3.1415926535897930

3.1415967941284180 - using Leibniz formula with float.
Took 1.4 sec. and required 16777216 iterations.
Error is 1.32e-04 percent.

3.1415927410125732 - using arcTan formula with float.
Took 203.44 microsec. and required 3 iterations.
Error is 2.78e-06 percent.

3.1415927005681580 - using arcTan formula with double.
Took 144.27 microsec. and required 5 iterations.
Error is 1.50e-06 percent.

3.1415926535897930 - using Modified arcTan formula with double.
Took 220.55 microsec. and required 5 iterations.
Error is 0.00e+00 percent.

3.1415926535897930 - using BBP formula with double.
Took 211.88 microsec. and required 12 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 16 digits:
3.1415926535897932
Took 4.75 millisec. and required 13 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 100 digits:
3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679
Took 18.67 millisec. and required 81 iterations.

How many digits do you want to try for? 1809
Using BBP formula with BigDecimals for 1809 digits:
3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706
7982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381
9644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412
7372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160
9433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949
1298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051
3200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892
3542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318
5950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473
0359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019
8938095257201065485863278865936153381827968230301952035301852968995773622599413891249721775283479131
5155748572424541506959508295331168617278558890750983817546374649393192550604009277016711390098488240
1285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949
1293313677028989152104752162056966024058038150193511253382430035587640247496473263914199272604269922
7967823547816360093417216412199245863150302861829745557067498385054945885869269956909272107975093029
5532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380
0081647060016145249192173217214772350141441973568548161361157352552133475741849468438523323907394143
3345477624168625189835694855620992192221842725502542568876717904946016534668049886272327917860857843
83827967976
Took 1.48 sec. and required 1498 iterations.*/

// Question 2: Compare techniques 1 and 2 - which yields a better value and why is it better?
// Technique 1: Using Leibniz formula to calculate pi using floats - yields: 3.1415967941284180
// Technique 2: Using arctan forumla with floats - yields: 3.1415927410125732
// Compare: The second technique yields a better technique as the second formula prints pi to 6 decimal places, and leibniz prints pi to 5 decimal places.

// Question 3: Why does this assignment not ask you to use the Leibniz formula with a double?
// The leibniz formula is done using a float instead of a double because if double was used, the number of iterations would be increased, and the code would take more time to execute.
// float uses 4 bytes and double uses 8 bytes.

// Question 4: Compare techniques 2 and 3 - which one is better and why?
// Technique 2: Find pi using second arctan formula with floats and the taylor series to calculate arctan.
// Technique 3: Use the same technique as number 2, but using double instead.
// Compare: Technique 3 almost twice as many iterations compared to the technique using only floats and prints the value of pi to 14 decimal places (double of what float returns it to).

// Question 5: Were you able to improve the accuracy of the value reported by technique 3? If so, what algorithm change(s) did you make to the technique?
// Yes, the 4th technique did improve the accuracy of the value reported by technique 3. The implementation of Ramanujan's summation which separated the positives and negatives and then performed the calculations provided a much accurate value of pi. 

// Question 6: Compare techniques 3 and 5 - which one is better and why?
// Technique 3: Uses arctan formula and double to retrieve the value of pi
// Technique 5: Uses BBP with a double to print the value of pi.
// Compare: Technique 5 is better as it yields a better value for pi. This is because with bigDecimal, the item can grow arbitrarily large and help perform precise calculations.

// Question 7: How does the use of a BigDecimal instead of a double affect the calculation?
// BigDecimal can calculate a number to the max precision as it has infinte amount of memory and can hold very large numbers when performing a calculation. 
// Whereas, a double is only 8 bytes and provides only a 64-bit floating number - a less accurate value as rounding takes place throughout the calculations.

