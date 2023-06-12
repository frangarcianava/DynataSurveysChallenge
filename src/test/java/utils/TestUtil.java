package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestUtil {

    public static List<String> statuses;
    public static List<String> members;
    public static List<String> surveys;
    public static int member;
    public static int surveyID;

    static {
        try {
            statuses = getStatus();
            members = getDataFromCSV("src/test/resources/csvFiles/Members.csv", "Member Id");
            member = randomMemberOrSurvey(members);
            surveys = getDataFromCSV("src/test/resources/csvFiles/Surveys.csv","Survey Id");
            surveyID = randomMemberOrSurvey(surveys);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    //Verify the http response status returned.
    public int checkStatusCode(Response response) {
        response.then().log().status();
        return response.getStatusCode();
    }

    public ValidatableResponse checkJSONSchema(Response response) {
        return response.then();
    }

    public static List<String> getDataFromCSV(String csvFilePath, String columnName) throws CsvException {
        List<String> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            boolean isFirstLine = true;
            int nameColumnIndex = -1;

            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;

                    // Obtain the index of column "columnName"
                    nameColumnIndex = getColumnIndex(line, columnName);
                    continue;
                }

                String name = line[nameColumnIndex];
                list.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static int getColumnIndex(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1; // Columna no encontrada
    }

    public static List<String> getStatus() throws CsvException {
        List<String> statuses = getDataFromCSV("src/test/resources/csvFiles/Statuses.csv","Name");
        return statuses.stream().map(s -> s.toUpperCase().replaceAll(" ","_")).collect(Collectors.toList());
    }

    public static LinkedHashMap<String,String> getParticipationStatus(String memberId) {
        List<String[]> filteredRows = filterCSV("src/test/resources/csvFiles/Participation.csv", "Member Id", memberId);
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        for (String[] row : filteredRows) {
            map.put(row[1],row[2]); //row[1] is surveyId and row[2] is status.
        }
        return map;
    }

    public static List<String[]> filterCSV(String csvFile, String columnName, String filterValue) {
        List<String[]> filteredRows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = reader.readNext();

            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length > columnIndex && row[columnIndex].equalsIgnoreCase(filterValue)) {
                    filteredRows.add(row);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return filteredRows;
    }

    public static int randomMemberOrSurvey(List<String> list) throws CsvException {
        // Generate a random number in the range from 0 to 500 (in this case)
        Random random = new Random();
        int randomNumber = random.nextInt(501);

        // Make sure the random number is within the index range of the list
        int randomIndex = randomNumber % list.size();

        // Obtain the random ID
        String randomId = list.get(randomIndex);

        return Integer.parseInt(randomId);
    }

    public static boolean checkIfMemberOrSurveyExists(List<String> list) throws CsvException {
        int randomNumber = randomMemberOrSurvey(list);
        return list.contains(String.valueOf(randomNumber));
    }


}


