
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class uniqueCharacterCount {
    private static final Map<String, Integer> cacheCount = new HashMap<>();

    public static int countUniqueCharacters(String str){
        str = str.toLowerCase();
        if(!cacheCount.containsKey(str)){
            Set<Character> uniqueCharacters = new HashSet<>();
            for(int i = 0; i < str.length(); i++){
                uniqueCharacters.add(str.charAt(i));
            }
            cacheCount.put(str, uniqueCharacters.size());
            return uniqueCharacters.size();
        }
        return cacheCount.get(str);
    }

    public static void main(String[] args) {
        System.out.println(countUniqueCharacters("Hemant Marothi"));
        System.out.println(countUniqueCharacters("Hemant Marothi"));
        System.out.println(countUniqueCharacters("Hemant Marothi"));
    }

    
}
