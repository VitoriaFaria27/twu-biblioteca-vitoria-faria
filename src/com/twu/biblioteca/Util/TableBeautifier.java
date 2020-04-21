package com.twu.biblioteca.Util;

import java.util.HashMap;
import java.util.Map;

public class TableBeautifier {

    public static String beautifyTable(String[][] table) {

        Map<Integer, Integer> columnLengths = populateColumnLengths(table);

        final StringBuilder formatString = new StringBuilder();
        columnLengths.forEach((key, value) -> formatString.append("| %").append(value).append("s "));
        formatString.append("|\n");

        StringBuilder result = new StringBuilder();

        for(Object[] row : table){
            result.append(String.format(formatString.toString(), row));
        }

        return result.toString();
    }

    private static Map<Integer, Integer> populateColumnLengths(String[][] table) {
        Map<Integer, Integer> columnLengths = new HashMap<>();

        int index = 0;
        for( String[] a : table){
            for( String string : a){
                columnLengths.putIfAbsent(index, 0);
                if (columnLengths.get(index) < a[index].length()) {
                    columnLengths.put(index, a[index].length());
                }
                index++;
            }
            index = 0;
        }
        return columnLengths;
    }
}
