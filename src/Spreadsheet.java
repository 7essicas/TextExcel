

public class Spreadsheet implements Grid
{
    private Cell[][] spreadSheet;
    
    public Spreadsheet(){
        spreadSheet = new Cell[20][12];
        for(int i = 0; i < spreadSheet.length; i++){
            for(int j = 0; j < spreadSheet[0].length; j++){
                spreadSheet[i][j] = new EmptyCell();
            }
        }
    }

    
    @Override
    public String processCommand(String command)
    {
        if(command.length() < 4){
            //command: <cell>
            return getMessage(command);
        } else if(command.indexOf("=") > 0){
            //command: <cell> = <value>
            return assignValue(command);
        } else if(command.toLowerCase().equals("clear")){
            return clearSheet();
        } else{
            command = command.toUpperCase();
            return clearCell(command);
        }
    }
    
    //cell inspection
    public String getMessage(String loc){
        SpreadsheetLocation commandLoc = new SpreadsheetLocation(loc.toUpperCase());
        return spreadSheet[commandLoc.getRow()][commandLoc.getCol()].fullCellText();
    }
    
    //assignment to string values
    public String assignValue(String command){
        //find location
        int rowNumber = Integer.valueOf(command.substring(1,3).trim())-1;
        int colNumber = Character.toUpperCase(command.charAt(0))-'A';
        
        //find message
        int s = command.indexOf(" = ");
        String message = command.substring(s+3);
        
        //find cell and message correctly
        if(command.indexOf("\"") > 0){
            //message is a string
            message = message.substring(1,message.length()-1);
            System.out.println("TextCell");
            spreadSheet[rowNumber][colNumber] = new TextCell(message);
        } else if(command.indexOf("(") > 0 && command.indexOf(")") > 0){
            //message is a formula
            message = message.toUpperCase();
            System.out.println("FormulaCell");
            spreadSheet[rowNumber][colNumber] = new FormulaCell(message, this);
        }else if(command.indexOf("%") > 0){
            //message is a percent
        	System.out.println("percent cell");
            spreadSheet[rowNumber][colNumber] = new PercentCell(message);
        }else{
            //message is a value
        	System.out.println("value cell");
            spreadSheet[rowNumber][colNumber] = new ValueCell(message);
        }

        //return the entire sheet
        return getGridText();
    }
    
    //clearing the entire sheet
    public String clearSheet(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 12; j++){
                spreadSheet[i][j] = new EmptyCell();
            }
        }
        return getGridText();
    }
    
    //clearing a particular cell
    public String clearCell(String command){
        String strLoc = command.substring(command.indexOf(" ")+1/*want to skip the space*/);
        SpreadsheetLocation loc = new SpreadsheetLocation(strLoc);
        spreadSheet[loc.getRow()][loc.getCol()] = new EmptyCell();
        return getGridText();
    }
    
    
    @Override
    public int getRows()
    {
        return spreadSheet.length;
    }

    @Override
    public int getCols()
    {
        return spreadSheet[0].length;
    }

    @Override
    public Cell getCell(Location loc)
    {
        return spreadSheet[loc.getRow()][loc.getCol()];
    }
    
    public Cell getCell(int row, int column){
        return spreadSheet[row][column];
    }
    
    public String getColLetter(int colIndex){
        String[] letters = {"A", "B", "C", "D","E","F","G","H","I","J","K","L"};
        return letters[colIndex];
    }

    @Override
    public String getGridText()
    {
        //construct first row
        String firstRow = "   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |";
        //the following rows with numbers
        String grid = "";
        for(int i = 0; i < 20; i++){
            grid += "\n";
            grid += Integer.toString(i + 1);
            //spaces before actual grid
            if(i < 9){
                grid += "  ";
            }else{
                grid += " ";
            }
            //actual grid
            grid += "|";
            for(int j = 0; j < 12; j++){
                //this is where the problem is
                grid += this.spreadSheet[i][j].abbreviatedCellText();
                grid += "|";
            }
        }
        return firstRow + grid + "\n";
    }
}

