
/**
 * Write a description of class ValueCell here.
 *
 * @author (your name)
 * @version (a version doubleValber or a date)
 */
public class ValueCell extends RealCell
{
    private String doubleVal;
    private String originalVal;
    private double value;
    
    public ValueCell(String originalVal)
    {
        this.originalVal = originalVal;
        value = Double.parseDouble(originalVal);
        this.doubleVal = Double.toString(value);
    }

    @Override
    public String abbreviatedCellText(){
        if(doubleVal.length() > 10){
            return doubleVal.substring(0, 10);
        }else{
            return doubleVal + getSpaces(10 - doubleVal.length());
        }
    }
    
    @Override
    public String fullCellText(){
        return this.originalVal;
    }
    
    public double getDoubleValue(){
        return value;
    }
   
}
