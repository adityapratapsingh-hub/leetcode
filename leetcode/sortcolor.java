
public class SortColors {
    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        solution1(arr);
        solution2(arr);
    }
    
    // T.C.:- O(N) + O(N) = O(2N) ~ O(N)
    // S.C.:- O(1)
    public static void solution1(int[] nums) {
         // 2-pass
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            } else if (nums[i] == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        
        int idx = 0; 
        while(count0 > 0) {
            nums[idx] = 0;
            idx++;
            count0--;
        }

        while(count1 > 0) {
            nums[idx] = 1;
            idx++;
            count1--;
        }

        while(count2 > 0) {
            nums[idx] = 2;
            idx++;
            count2--;
        }
    }

    // T.C.:- O(N) 1 Pass
    // S.C.:- O(1)
    public static void solution2(int[] nums) {
        int posZero = 0;
        int posTwo = nums.length - 1;
        int start = 0;
        while(start <= posTwo) {
            if(nums[start] == 0) {
                int temp = nums[start];
                nums[start] = nums[posZero];
                nums[posZero] = temp;
                posZero++;
                start++;
            } else if(nums[start] == 1) {
                start++;
            } else {
                int temp = nums[start];
                nums[start] = nums[posTwo];
                nums[posTwo] = temp;
                posTwo--;
            }
        }
    }
}
package Lecture4;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> results = solution(numRows);
        System.out.println(results);
    }

    public static List<List<Integer>> solution(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        for(int row = 1; row <= numRows; row++) {
            List<Integer> rowData = new ArrayList<>();
            for(int col = 1; col <= row; col++) {
                if(col == 1 || col == row) {
                    rowData.add(1);
                } else {
                    List<Integer> prev = results.get(results.size()-1);
                    int idx = col-1;
                    int sum = prev.get(idx) + prev.get(idx-1);
                    rowData.add(sum);
                }
            }

            // rowData form
            results.add(rowData);
        }

        return results;
    }
}