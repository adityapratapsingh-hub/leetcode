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