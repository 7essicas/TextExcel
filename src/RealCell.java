public abstract class RealCell implements Cell
{
    public RealCell()
    {
        
    }

    public abstract String abbreviatedCellText();
    
    public abstract String fullCellText();
    
    public abstract double getDoubleValue();
    
    public String getSpaces(int times){
        String spaces = "";
        for(int i = 0; i < times; i++){
            spaces += " ";
        }
        return spaces;
    }
}