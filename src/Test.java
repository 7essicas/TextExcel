
public class Test {
	static Spreadsheet grid = new Spreadsheet();
	public static void main(String[] args) {
		for (int row = 1; row < 11; row++)
        {
            for (int col = 1; col < 7; col++)
            {
                String cellName = Character.toString((char)('A' + col)) + (row + 1);
                grid.processCommand(cellName + " = 1");
            }
        }
        String formula1 = "( 4 * 5.5 / 2 + 1 - -11.5 )";
        String formula2 = "( sUm B6-g11 )";
        String formula3 = "( AvG f8-F9 )";
        System.out.println(grid.getGridText());
        grid.processCommand("K9 = " + formula1);
        grid.processCommand("J10 = " + formula2);
        grid.processCommand("I11 = " + formula3);
	}
}
