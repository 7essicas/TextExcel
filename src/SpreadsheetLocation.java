
public class SpreadsheetLocation implements Location {
	private String row;
    private String column;
    
    public SpreadsheetLocation(String cellName)
    {
       char[] arr = cellName.toCharArray();
       column = Character.toString(arr[0]);
       if(arr.length > 2){
           row = String.valueOf(arr[1]) + String.valueOf(arr[2]);
        }else{
            row = String.valueOf(arr[1]);
        }
    }
    
    @Override
    public int getRow()
    {
        return Integer.parseInt(row)-1;
    }

    @Override
    public int getCol()
    {
        String[] letters = {"A", "B", "C", "D","E","F","G","H","I","J","K","L"};
        for(int i = 0; i < letters.length; i++){
            if(column.equals(letters[i])){
                return i;
            }
        }
        return 0;
    }

}
