package cellular;

import java.util.Scanner;

public class Generator {

	private static final int GRID_SIZE = 10;
	
	public static void main(String[] args) {
		
		int seed = 0;
		boolean run = true;
		int counter = 0;
		Integer[][] cells = new Integer[GRID_SIZE][GRID_SIZE];
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter a number between 1 and 10: ");
		seed = scnr.nextInt();
		
		// INITIAL POPULATION
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				
				cells[i][j] = 1;
			}
		}
		cells[seed][seed / 2] = 0;
		
		// LOOP
		while (run) {
			for (int i = 0; i < GRID_SIZE; i++) {
				for (int j = 0; j < GRID_SIZE; j++) {
					
					if (cells[i][j] == 0) {
						cells[i][j] = 8;
					} else {
						ruleOne(cells);
						ruleTwo(cells);
						ruleThree(cells);
					}
				}
			}
			counter++;
			if (counter == GRID_SIZE*100) {
				System.out.println("Enter 1 to continue, 0 to stop: ");
				int temp = scnr.nextInt();
				counter = 0;
				if (temp == 0) {
					run = false;
				}
			}
		}
		
		// REDUCER
		System.out.println("REDUCING");
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (cells[i][j] < 1) {
					cells[i][j] = Math.abs(cells[i][j]);
				}
				while (cells[i][j] >= 10) {
					System.out.println(cells[i][j]);
					cells[i][j] = cells[i][j] / 2;
				}
			}
		}
		
		// DISPLAY
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				
				System.out.print(cells[i][j] + " ");
				
			}
			System.out.println("");
		}
	
		
	}

	// RULE #1
	public static void ruleOne(Integer[][] a) {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (a[i][j] == 8) {
					try {
						a[i+1][j+1] = a[i][j] * a[i-1][j-1];
						a[i-1][j-1] = 3 + a[i][j-1];
					} catch (IndexOutOfBoundsException e) {
		//				System.out.println(e);
					}
				} else if (a[i][j] == 7) {
					a[i][j] = 3;
				}
			}
		}	
	}
	
	// RULE #2
	private static void ruleTwo(Integer[][] a) {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (a[i][j] == 3) {
					try {
						a[i+1][j+1] = 7;
						a[i-1][j-1] = a[i][j] + 2;
						a[i][j+1] = a[i-2][j+1] * a[i][j+1];
						a[i+1][j] = 4 * a[i][j];
					} catch (IndexOutOfBoundsException e) {
				//		System.out.println(e);
					}
				}
			}
		}	
	}
	
	// RULE #3
	private static void ruleThree(Integer[][] a) {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (a[i][j] == 1) {
					try {
						a[i-1][j+1] = 3;
						a[i-1][j-1] = 3;
					} catch (IndexOutOfBoundsException e) {
			//			System.out.println(e);
					}
				}
			}
		}	
	}
}

