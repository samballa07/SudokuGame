
public class Solver {
	
	private int[][] puzzle;
	
	public class Tuple {
		public int row;
		public int col;
		
		public Tuple(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public Solver(int[][] gameBoard) {

		puzzle = gameBoard;
	}
	
	public boolean solve() {
		Tuple find = find_empty();
		int row, col;
				
		if (find != null) {
			row = find.row;
			col = find.col;
		}else {
			return true;
		}
		
		for (int i = 1; i < 10; i++) {
			if (valid(find, i)) {
				puzzle[row][col] = i;
				
				if (solve())
					return true;
				
				puzzle[row][col] = 0;
			}
		}
		
		return false;
	}
	
	public boolean valid(Tuple pos, int num) {
		int row = pos.row;
		int col = pos.col;
		
		for(int i = 0; i < puzzle.length; i++) {
			if(puzzle[row][i] == num && col != i) {
				return false;
			}
		}
		for(int i = 0; i < puzzle.length; i++) {
			if(puzzle[i][col] == num && col != i) {
				return false;
			}
		}
		
		int x = Math.floorDiv(col, 3);
		int y = Math.floorDiv(row, 3);
		
		for(int i = y*3; i < y * 3 + 3; i++) {
			for(int j = x * 3; j < x * 3 + 3; j++) {
				if(puzzle[i][j] == num && (i != row && j != col)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Tuple find_empty() {
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[0].length; j++) {
				if (puzzle[i][j] == 0) {
					return new Tuple(i, j);
				}
			}
		}
		return null;
	}
	
	public static void print(int[][] p) {
		String display;
		System.out.println("   | 1 2 3 | 4 5 6 | 7 8 9 |");
		System.out.println("   -------------------------");

		for(int i = 0; i < p.length; i++) {
			if(i % 3 == 0 && i != 0) {
				System.out.println("   - - - - - - - - - - - - -");
			}
			
			for(int j = 0; j < p[0].length; j++) {
				if(j == 0) {
					System.out.print(" " + (i+1) + " | ");

				} else if(j % 3 == 0) {
					System.out.print("| ");
				}
				
				if (p[i][j] == 0) {
					display = "*";
				}else {
					display = Integer.toString(p[i][j]);
				}
				if(j == 8) {
					System.out.print(display + " |\n");
				}else {
					System.out.print(display + " ");
				}
			}
		}
		System.out.println("   -------------------------");

	}
	public int[][] getSolvedBoard(){
		this.solve();
		return puzzle;
	}
	
	
}
