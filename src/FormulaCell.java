
import java.util.ArrayList;

/**
 * Write a description of class FormulaCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FormulaCell extends RealCell
{
    private String expression;
    private Spreadsheet sheet;
    
    public FormulaCell(String a, Spreadsheet b){
        expression = a;
        sheet = b;
    }

    @Override
    public String abbreviatedCellText(){
        String d = Double.toString(getDoubleValue());
        if(d.length() > 10){
            return d.substring(0, 10);
        }else{
            String spaceStr = "";
            int spaceNum = 10 - d.length();
            for(int i = 0; i < spaceNum; i++){
                spaceStr += " ";
            }
            return d + spaceStr;
        }
    }
    
    @Override
    public String fullCellText(){
        return expression ;
    }
    
    @Override
    public double getDoubleValue(){
        ArrayList<Double> nums = new ArrayList<Double>();
        ArrayList<String> operators = new ArrayList<String>();
        
        String[] exp = this.expression.trim().split(" ");
        
        if(this.expression.indexOf("SUM") > 0 || this.expression.indexOf("AVG") > 0){
            //evaluate SUM method
            double sum = 0.0;
            int count = 0;
            
            String range = "";
            for(String t:exp){
                if(t.indexOf("-")>0){
                    range = t;
                }
            }
            
            Location loc1 = new SpreadsheetLocation(range.substring(0,range.indexOf("-")));
            Location loc2 = new SpreadsheetLocation(range.substring(range.indexOf("-")+1));
            
            for(int i = loc1.getRow(); i <= loc2.getRow(); i++){
                for(int j = loc1.getCol(); i <= loc2.getCol(); j++){
                    RealCell cell = (RealCell)sheet.getCell(i,j);
                    System.out.println(cell.fullCellText());
                    sum += cell.getDoubleValue();
                    count++;
                }
            }
            
            if(this.expression.indexOf("AVG") > 0 ){
                return sum / count;
            }else{
                return sum;
            }
            
        }else{
            //evaluate a formula
            int intOperators = 0;
            int intNums = 0;
            for(int i = 0; i < exp.length; i++){
                if(isValidOperator(exp[i])){
                    operators.add(exp[i]);
                    intOperators++;
                }else if(isValidDouble(exp[i])){
                    nums.add(Double.valueOf(exp[i]));
                    intNums++;
                }else if(isValidLoc(exp[i])){
                    Location loc = new SpreadsheetLocation(exp[i]);
                    RealCell cell = (RealCell)sheet.getCell(loc);
                    nums.add(cell.getDoubleValue());
                }
            }
            
            double result = nums.get(0);
            for(int i = 0; i < operators.size(); i++){
                if(operators.get(i).equals("+")){
                    result += nums.get(i+1);
                }else if(operators.get(i).equals("-")){
                    result -= nums.get(i+1);
                }else if(operators.get(i).equals("*")){
                    result = result * nums.get(i+1);
                }else if(operators.get(i).equals("/")){
                    result = result / nums.get(i+1);
                }
            }
            return result;
        }
    }
    
    /*helpful methods*/
    
    public boolean isValidDouble(String str){
        String isValid = ".-0123456789";
        for(int i = 0; i < str.length(); i++){
            if(isValid.indexOf(str.charAt(i)) < 0){
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidOperator(String str){
        String isValid = "+-/*";
        for(int i = 0; i < str.length(); i++){
            if(isValid.indexOf(str.charAt(i)) < 0){
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidLoc(String str){
        String isValid = "ABCDEFGHIJKL012345689";
        for(int i = 0; i < str.length(); i++){
            if(isValid.indexOf(str.charAt(i)) < 0 ){
                return false;
            }
        }
        return true;
    }

}
