package com.twu.biblioteca;

import com.twu.biblioteca.Util.TableBeautifier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaUtilTest {

    String[][] table;
    private static final String TEST_TABLE = "| id | First Name | Last Name | Age |\n" +
            "|  1 |       John |   Johnson |  45 |\n" +
            "|  2 |        Tom |           |  35 |\n" +
            "|  3 |       Rose |   Johnson |  22 |\n" +
            "|  4 |      Jimmy |    Kimmel |     |\n";


    @Before
    public void setup(){

        table = new String[][] { { "id", "First Name", "Last Name", "Age" },
                { "1", "John", "Johnson", "45" }, { "2", "Tom", "", "35" }, { "3", "Rose", "Johnson", "22" },
                { "4", "Jimmy", "Kimmel", "" } };
    }

    @Test
    public void shouldReturnBeautifiedTable() {
        String result = TableBeautifier.beautifyTable(table);

        assertThat(result, is(TEST_TABLE));
    }
}
