package magic;

public class MagicCube {

	public static void main(String[] args) {
		int n = 4;
		int[][][] magicCube = getMagicCube(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < magicCube.length; k++) {
					if (k != 0) {
						System.out.print(",");
					}
					System.out.print(magicCube[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * This method generates magic cube using extended Siamese method to
	 * construct odd order magic cube
	 * 
	 * @see <a href="http://www.magichypercube.com">Magic Hyper Cube
	 *      Generator</a>
	 * @param n
	 * @return
	 */
	public static int[][][] getMagicCube(int n) {
		if (n % 2 == 1) {
			int[][][] magicCube = new int[n][n][n];
			// layer index, row index, column index
			int l, r, c;
			l = 0;
			r = n / 2;
			c = n / 2;

			// If you want to port this code in C++
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < magicCube.length; k++) {
						magicCube[i][j][k] = 0;
					}
				}
			}

			int last = (int) Math.pow(n, 3);
			for (int i = 0; i < last; i++) {
				magicCube[l][r][c] = i + 1;
				l--;
				l = normalize(n, l);
				c--;
				c = normalize(n, c);
				if (magicCube[l][r][c] != 0) {
					r--;
					r = normalize(n, r);
					c++;
					c = normalize(n, c);
					if (magicCube[l][r][c] != 0) {
						r++;
						r = normalize(n, r);
						l += 2;
						l = normalize(n, l);
					}
				}
			}

			return magicCube;
		} else if (n % 4 == 0) {
			int[][][] magicCube = new int[n][n][n];
			int lastPlusOne = (int) Math.pow(n, 3) + 1;
			int number = 1;
			int nBy4 = n / 4;
			for (int l = 0; l < n; l++) {
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < magicCube.length; c++) {
						boolean parity = false;
						if (l >= nBy4 && l < 3 * nBy4) {
							parity = true;
						}
						if (r >= nBy4 && r < 3 * nBy4) {
							parity = !parity;
						}
						if (c >= nBy4 && c < 3 * nBy4) {
							parity = !parity;
						}
						magicCube[l][r][c] = (parity) ? lastPlusOne - number
								: number;
						number++;
					}
				}
			}
			return magicCube;
		}

		throw new RuntimeException(
				"Singly even magic cubes are not returned by this method.");
	}

	// index should be between 0 to n-1
	private static int normalize(int n, int index) {
		while (index < 0) {
			index = index + n;
		}
		while (index > n - 1) {
			index = index - n;
		}
		return index;
	}
}
