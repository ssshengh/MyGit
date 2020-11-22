package BasicADT;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AboutMap {
    public static void main(String[] args) {
        Map<String, Integer> hashmap = new HashMap<>();
        hashmap.put("sisi", 22);
        hashmap.put("hengheng", 21);
        hashmap.put("gf", 22);

        System.out.println("Display hash map: " + hashmap);

        Map<String, Integer> Treemap = new TreeMap<>(hashmap);
        System.out.println("Display tree map : " + Treemap);
    }
}
