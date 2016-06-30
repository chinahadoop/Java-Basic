package cn.chinahadoop.java;

/**
 * Chinahadoop Fibonacci Example!
 *
 */
public class Fibonacci {
	public static void main(String[] args) {
		int sum = 0, a = 1, b = 1;
		int n = Integer.parseInt(args[0]);

		if (n < 1) {
			System.out.println("Please Input a positive integer.");
		}

		if (n < 2) {
			System.out.println(1);
			sum = 1;
		} else {
			System.out.print("1 1 ");
			int itemNum = 2;
			sum = 2;

			while (itemNum < n) {
				a += b;
				System.out.print(a + " ");
				sum += a;
				itemNum++;
				if (itemNum >= n) {
					break;
				}

				b += a;
				System.out.print(b + " ");
				sum += b;
				itemNum++;
				if (itemNum >= n) {
					break;
				}
			}
			
			System.out.println();
			System.out.println("Avarage is " + ((double)sum) / n);
		}
	}
}
