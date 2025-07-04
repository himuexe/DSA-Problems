import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DNA {
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> list =new HashSet<>();
        for(int i=0;i<= s.length()-10;i++){
            String substring = s.substring(i,i+10);
            if(set.contains(substring)){
                list.add(substring);
            }
            else{
                set.add(substring);
            }
        }
        return new ArrayList(list);
    }

}
