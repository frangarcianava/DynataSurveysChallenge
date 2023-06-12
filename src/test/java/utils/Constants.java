package utils;

public class Constants {
    public static final String GET_MEMBERS_OF_SURVEY_API = "/surveys/{surveyId}/members?status={status}";
    public static final String SURVEYS_COMPLETED_BY_MEMBER_API = "/members/{memberId}";
    public static final String MEMBER_POINTS_API = "/members/{memberId}/points";
    public static final String ACTIVE_NOT_PARTICIPATED_API = "/surveys/{surveyId}/members/not-invited";
    public static final String SURVEY_STATISTICS_API = "/surveys/statistics";

    //These two parameters are random. You could specify here the number you want.
    public static final int SURVEY_ID = TestUtil.surveyID;
    public static final int MEMBER_ID = TestUtil.member;

    public static final String INVALID_STATUS = "INVALID";
    public static final String STATUS_NOT_ASKED = TestUtil.statuses.get(0);
    public static final String STATUS_REJECTED = TestUtil.statuses.get(1);
    public static final String STATUS_FILTERED = TestUtil.statuses.get(2);
    public static final String STATUS_COMPLETED = TestUtil.statuses.get(3);


}
