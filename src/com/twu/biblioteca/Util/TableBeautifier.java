package com.twu.biblioteca.Util;

import java.util.HashMap;
import java.util.Map;

public class TableBeautifier {

    public static String beautifyTable(String[][] table) {

        Map<Integer, Integer> columnLengths = populateColumnLengths(table);

        final StringBuilder formatString = new StringBuilder();
        columnLengths.forEach((key, value) -> formatString.append("| %").append(value).append("s "));
        formatString.append("|\n");

        return formatTable(table, formatString.toString());
    }

    private static String formatTable(String[][] table, String formatString) {
        String trueResult;

        StringBuilder result = new StringBuilder();

        for(Object[] row : table){
            result.append(String.format(formatString, row));
        }

        trueResult = result.toString();
        return trueResult;
    }

    private static Map<Integer, Integer> populateColumnLengths(String[][] table) {
        Map<Integer, Integer> columnLengths = new HashMap<>();

        for( String[] row : table){
            for( int index = 0 ; index < row.length; index++){
                columnLengths.putIfAbsent(index, 0);
                if (columnLengths.get(index) < row[index].length()) {
                    columnLengths.put(index, row[index].length());
                }
            }
        }
        return columnLengths;
    }
}
