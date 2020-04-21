package com.twu.biblioteca.Util;

import java.util.HashMap;
import java.util.Map;

public class TableBeautifier {

    public static String beautifyTable(String[][] table) {

        Map<Integer, Integer> columnLengths = populateColumnLengths(table);

        String formatter = createFormatter(columnLengths);

        return formatTable(table, formatter);
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

    private static String createFormatter(Map<Integer, Integer> columnLengths) {
        String formatter;

        final StringBuilder formatString = new StringBuilder();
        columnLengths.forEach((key, value) -> formatString.append("| %").append(value).append("s "));
        formatString.append("|\n");

        formatter = formatString.toString();
        return formatter;
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
}
