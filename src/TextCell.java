
/**
 * Write a description of class TextCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextCell implements Cell
{
    private String txt;

    public TextCell(String t){
        txt = t;
    }

    public String abbreviatedCellText(){
        if(txt.length() < 10){
            String spaceStr = "";
            if(txt.length() < 10){
                int spaceNum = 10 - txt.length();
                for(int i = 0; i < spaceNum; i++){
                    spaceStr += " ";
                }
            }
            return txt + spaceStr;
        }else{
            return txt.substring(0,10);
        }
        
    }
    
    public String fullCellText(){
        return '\"' + txt + '\"';
    }
}
