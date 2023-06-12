# Dynata Surveys

Description of the development:

There is a list with the information of 300 people who participated in different surveys.
For each survey the respondent can receive points that later can be claimed as different gifts.

You can find active and inactive members in the file, in a survey we only can target the active members,
but it is possible that the currently inactive members participated in previous surveys.

We are storing 4 different participation statuses:
- (1) Not asked - Not participated in the questionnaire (was not asked)
- (2) Rejected - Not participated in the questionnaire (was asked, but no response)
- (3) Filtered - Based on the filtering questions, the member did not fit into the targeted group
- (4) Completed - Filled and finished the questionnaire
  The respondent can receive point only in 3 and 4 cases (points may differ between Filtered or Completed).

All the data are stored in csv files:
Members.csv - all the members who can participate in surveys
Surveys.csv - information about surveys that will target the members in order to collect their answers
Participation.csv  - information about survey participation, which member was part of which survey
Statuses.csv - all the possible statuses of a survey participation


## APIs
- **API 1:** Fetch all the respondents who completed the questionnaire for the given survey id.
    ###### /api/surveys/{surveyId}/members?status={status}
- **API 2:** Fetch all the surveys that were completed by the given member id.
    ###### /api/members/{memberId}
- **API 3:** Fetch the list of points (with the related survey id) that the member collected so far (the input is the member id).
  ###### /api/members/{memberId}/points
- **API 4:** Fetch the list of members who can be invited for the given survey (not participated in that survey yet and active).
    ###### /api/surveys/{surveyId}/members/not-invited"
- **API 5:** Fetch the list of surveys with statistics.
  ###### /api/surveys/statistics

## Requirements

The task was to write tests for API endpoints.

### DISCLAIMER

*As I was not sure of the tests to implement, because I did not have the necessary requirements, some may not be fulfilled perfectly, they are only hypothetical examples.*

*I have imagined scenarios that may not be valid, but it was to move forward and make the framework more complete.*

*For example: I did validations for API 1, where for 'REJECTED' status the response should not be empty (as i saw in the most of the cases), but I don’t know if that should be the correct behavior.*

## Project Structure

This is a maven project framework where RestAssured was used with Java and TestNG to perform automated testing.

Within the 'pom.xml' file I defined the dependencies I thought were necessary for the project.

Then in the test/java package I created three packages:
- **api**: Contains the apis with their respective methods. All APIs inherit from a 'BaseMethods' class that are common to all to avoid code duplication and all APIs implement an interface called 'ApiInterface' that allows me to implement each class (or api) methods according to their needs.
- **tests**: Contains classes referring to each API where tests and assertions are defined. In turn it also has a base class containing tests common to all APIs and where configuration is specified through TestNG annotations such as @BeforeClass and @AfterClass perform specifying data before and after each class respectively.
- **utils**: Contains common and reusable methods and functions that provide additional functionality or facilitate specific tasks.

I also created a 'resources' directory where I put the CSV files containing the APIs information, the JSON files needed for schema validation, a logger property file, and the testng.xml file. 
## Environment Setup

You can start the application with the following command (java 11 is required to be installed first):
java -jar dynata-surveys-0.0.1-SNAPSHOT.jar

Once the application is running, you can find the swagger documentation of the API here:
http://localhost:8080/swagger-ui/index.html

## Execution of Tests

Run the '**testng.xml**' file that is under the path: **src/test/resources/testng.xml**

## Resultados Esperados

Descripción de los resultados esperados de las pruebas automatizadas.


## Autores

- [Francisco Garcia Nava]
