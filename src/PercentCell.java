
/**
 * Write a description of class PercentCell here.
 *
 * @author (your name)
 * @version (a version percentber or a date)
 */
public class PercentCell extends RealCell
{
    private String percent;
    private double value;
    
    public PercentCell(String percent)
    {
        this.percent = percent;
        value = Double.parseDouble(percent.substring(0, percent.length() - 1)) / 100;
    }

    @Override
    public String abbreviatedCellText(){
        int a = percent.indexOf("."); //used twice in the following statement
        return percent.substring(0,a) + "%" + getSpaces(10 - a - 1);
    }
    
    @Override
    public String fullCellText(){
        return Double.toString(value);
    }
    
    public double getDoubleValue(){
        return value;
    }
}
