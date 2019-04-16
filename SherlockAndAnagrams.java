import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
 * Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
 * */
public class Solution {

    /**
     * Consider you pass string "abba"
     * It will iterate through the string and add it to the map to find the occurrence of the value be it 1 letter or more.
     * Eg map contains keys like [a, ab, abb, b, bb] All will be sorted and stored in the map as a key
     * occurrence of the value will always be incremented by 1
     * In the end will sum up by calculating the average
     * Say aabb or bb occurs once only in the iteration so calculate it to 0 because it has no anagrams i.e. 1 * (1-1) /2 which returns 0
     * abb ab a b occurs twice in the string so we need to avg out the occurrence. So 2*(2-1)/2 = 1
     * and then sum it up which returns 4
     * */
    private static int sherlockAndAnagrams(String s){
        Map<String, Integer> key2Points = new HashMap<>();
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<=s.length();j++){
                String key = generateKey(s.substring(i,j));
                if(!key2Points.containsKey(key)){
                    key2Points.put(key,1);
                }else{
                    key2Points.put(key, key2Points.get(key) + 1);
                }
            }
        }
        return key2Points.values().stream().mapToInt(count -> count * (count-1)/2).sum();
    }

    private static String generateKey(String value){
        return value.chars().sorted().mapToObj(letter -> String.valueOf((char)letter)).collect(Collectors.joining());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = sherlockAndAnagrams("abba");
        System.out.println(String.valueOf(result));
    }
}