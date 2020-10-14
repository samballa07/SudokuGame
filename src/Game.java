import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	
	int[][] board = new int[9][9];
	int[][] solved;
	private static final int EXIT = 0;
	
	public Game(int[][] gameBoard) {
		System.out.print("Welcome to Sudoku Puzzle!\n");
		deepCopy(gameBoard);
		Solver s = new Solver(gameBoard);
		solved = s.getSolvedBoard();
		
	}
	
	public void runGame() {
		Scanner input = new Scanner(System.in);
		
		int userIn = -1;
		
		do {
			
			printMenu();
			try {
				userIn = input.nextInt();

				if (userIn >= 0 && userIn <= 5) {
					
					switch(userIn) {
					case 1 :
						placeAndCheck();
						break;
					case 2 :
						place();
						break;
					case 3 :
						if(check() == true) {
							break;
						}
						break;
					case 4 :
						Solver.print(board);
						break;
					case 5 :
						deepCopy(solved);
						Solver.print(board);
						userIn = EXIT;
						break;
					case EXIT :
						System.out.println("\nThanks for playing!");
					}
					
					
				}else {
					System.out.println("\nInvalid Input!");
				}
			}catch(InputMismatchException e) {
				System.out.println("Invalid Input!");
				input.next();
			}
			

		}while(userIn != EXIT);
	}
	
	public void placeAndCheck() {
		Scanner input = new Scanner(System.in);
		int row = 0;
		int col = 0;
		int num = 0;
		do {
			System.out.print("Enter the row number(1-9): ");
			row = input.nextInt();
			System.out.print("Enter the column number(1-9); ");
			col = input.nextInt();
			System.out.print("Enter the number you want to place here: ");
			num = input.nextInt();
		}while(row < 1 || row > 9 || col < 1 || 
				col > 9 || num < 1 || num > 9);
		if(num == solved[row-1][col-1]) {
			board[row-1][col-1] = num;
			System.out.println("\nGoodjob! That number belongs in that location");
		}else {
			System.out.println("\nThat number does not belong there!");
		}
	}
	
	public boolean check() {
		boolean check = true;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] != solved [i][j]) {
					check = false;
				}
			}
		}
		if(check == true) {
			System.out.println("Goodjob! You solved this puzzle");

		}else {
			System.out.println("This puzzle is not solved correctly. Keep going");
		}
		return check;
	}
	
	//deep copy is made of solved board
	public void deepCopy(int[][] newBoard) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board[i][j] = newBoard[i][j];
			}
		}
	}
	
	public void place() {
		Scanner input = new Scanner(System.in);
		int row = 0;
		int col = 0;
		int num = 0;
		do {
			System.out.print("Enter the row number(1-9): ");
			row = input.nextInt();
			System.out.print("Enter the column number(1-9); ");
			col = input.nextInt();
			System.out.print("Enter the number you want to place here: ");
			num = input.nextInt();
		}while(row < 1 || row > 9 || col < 1 || 
				col > 9 || num < 1 || num > 9);
		int old;
		if(board[row-1][col-1] != 0) {
			old = board[row-1][col-1];
			board[row-1][col-1] = num;
			System.out.println("Replaced " + old + " with " + 
								num + " in row " + row + " and col " + col);
		}else {
			board[row-1][col-1] = num;
			System.out.println("Placed " + num + " in row " + row 
								+ " and col " + col);
		}
		
	}
	
	
	public static void printMenu() {
		System.out.print("\nWhat would you like to do?\n");
	    
		System.out.println("1 - Place Number with Check");
		System.out.println("2 - Place Number On Board");
		System.out.println("3 - Check Board");
		System.out.println("4 - Print Board");
		System.out.println("5 - Solve Puzzle and Exit");
		System.out.println("0 - Exit Game");
		System.out.print("Enter your choice(0-5): ");

	}
	public static void main(String[] args) {
		int[][] arr2 = {{7, 8, 0, 4, 0, 0, 1, 2, 0}, 
				{6, 0, 0, 0, 7, 5, 0, 0, 9},
				{0, 0, 0, 6, 0, 1, 0, 7, 8},
				
				{0, 0, 7, 0, 4, 0, 2, 6, 9},
				{0, 0, 1, 0, 5, 0, 9, 3, 0},
				{9, 0, 4, 0, 6, 0, 0, 0, 5},
				
				{0, 7, 0, 3, 0, 0, 0, 1, 2},
				{1, 2, 0, 0, 0, 7, 4, 0, 0},
				{0, 4, 9, 2, 0, 6, 0, 0, 7}};
		Game g = new Game(arr2);
		g.runGame();
		
		//other sudoku board you can send into Game object
		int[][] arr1 = {{ 0, 0, 4,   0, 0, 0,   0, 6, 7 },
                	{ 3, 0, 0,   4, 7, 0,   0, 0, 5 },
                	{ 1, 5, 0,   8, 2, 0,   0, 0, 3 },
                    
                	{ 0, 0, 6,   0, 0, 0,   0, 3, 1 },
                	{ 8, 0, 2,   1, 0, 5,   6, 0, 4 },
                	{ 4, 1, 0,   0, 0, 0,   9, 0, 0 },
                    
                	{ 7, 0, 0,   0, 8, 0,   0, 4, 6 },
                	{ 6, 0, 0,   0, 1, 2,   0, 0, 0 },
                	{ 9, 3, 0,   0, 0, 0,   7, 1, 0 } };
		
		int[][] arr = {{7, 8, 0, 4, 0, 0, 1, 2, 0}, 
					{6, 0, 0, 0, 7, 5, 0, 0, 9},
					{0, 0, 0, 6, 0, 1, 0, 7, 8},
					
					{0, 0, 7, 0, 4, 0, 2, 6, 9},
					{0, 0, 1, 0, 5, 0, 9, 3, 0},
					{9, 0, 4, 0, 6, 0, 0, 0, 5},
					
					{0, 7, 0, 3, 0, 0, 0, 1, 2},
					{1, 2, 0, 0, 0, 7, 4, 0, 0},
					{0, 4, 9, 2, 0, 6, 0, 0, 7}};
		
	}
}
