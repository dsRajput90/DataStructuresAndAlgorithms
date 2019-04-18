import java.util.*;

/**
 * You are given an array and you need to find number of triplets of indices(i,j,k)
 * such that the elements at those indices are in geometric progression for a given common ratio r and i < j < k.
 *
 * For example, arr = [1,4,16,64] If ,r=4 we have [1,4,16] and [4,16,64]  at indices (0,1,2) and (1,2,3).
 * */
public class CountTriplets {

    /**
     * Steps:
     * 1. Firstly create a map of all the array numbers as key and their occurrence/index position added to the list value.
     * 2. In the next iteration check if list number value and value r yields 0. If remainder is not 0 continue to the next value.
     * 3. If returns 0 then get firstNumber and lastNumber.
     * 4. firstNumber is current list number divides by r
     * 5. lastNumber will be current list number * r
     * 6. result will be multiplication of firstNumber count and lastNumberCount
     * 7. For firstNumber => get the position of number using binary search if position > 0 return else return -position - 1
     * 8. For lastNumber => get the position  of number using binary search if position < 0 -1-position-1 and return size of indices-1-position(Eg in arr 1,3,9,9, while iterating
     * for value 3, 2 triplets are posible at position (0,1,2) and (0,1,3).
     * So the calculation of lastNumber returns value 2.
     * 9. Iterate the loop and sum the result.
     * 10. Return the result.
     *
     * */
    private static long countTriplets(List<Long> arr, long r) {
        long result = 0;
        Map<Long,List<Integer>> triplets = new HashMap<>();
        for(int i=0;i<arr.size();i++){
            long number = arr.get(i);
            if(!triplets.containsKey(number)){
                triplets.put(number, new ArrayList<>());
            }
            triplets.get(number).add(i);
        }

        for(int i=0;i<arr.size();i++){
            long number = arr.get(i);
            if(number%r!=0){
                continue;
            }
            long firstNumber = number/r;
            if(number*r > Integer.MAX_VALUE)
                continue;

            long lastNumber = number*r;

            result+=findBeforeCount(triplets,firstNumber,i) *
                    findAfterCount(triplets,lastNumber,i);

        }

        return result;
    }

    private static long findBeforeCount(Map<Long,List<Integer>> triplets,Long firstNumber, int index){
        if(!triplets.containsKey(firstNumber)){
            return 0;
        }
        List<Integer> indices = triplets.get(firstNumber);
        int position = Collections.binarySearch(indices,index);
        if(position<0){
            position = -1-position;
        }
        return position;
    }
    private static long findAfterCount(Map<Long,List<Integer>> triplets,Long lastNumber, int index){
        if(!triplets.containsKey(lastNumber)){
            return 0;
        }
        List<Integer> indices = triplets.get(lastNumber);
        int position = Collections.binarySearch(indices,index);
        if(position<0){
            position = -1-position-1;
        }
        return indices.size() - 1 - position;

    }

    public static void main(String[] args) {
        Long result = countTriplets(Arrays.asList(1L, 3L, 9L, 9L),3);
        System.out.println(String.valueOf(result));
    }
}