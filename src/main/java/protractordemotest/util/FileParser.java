package protractordemotest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.fail;

public class FileParser {

    private Scanner loadTestData(final String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/test/resources/testdata/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail(String.format("File '%s' was not found.", fileName));
        }
        return scanner;
    }

    public List<String[]> getListOfTestData(final String fileName) {
        ArrayList<String[]> listOfData = new ArrayList<>();
        Scanner testData = loadTestData(fileName);
        while (testData.hasNext()) {
            String line = testData.next();
            String[] tokens = line.split("\\|");
            listOfData.add(tokens);
        }
        testData.close();
        return listOfData;
    }
}