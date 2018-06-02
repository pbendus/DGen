package doc_utils;

public enum DocVariableConst {
    DIPLOMA("diploma"),
    REGISTRATION_NUMBER("reg"),
    ADD_REGISTRATION_NUMBER("add_reg"),
    DATE_OF_ISSUE("date_of_issue"),
    DATE_OF_ISSUE_ADD("date_of_issue_add"),
    FAMILY_NAME("family_name"),
    GIVEN_NAME("given_name"),
    GIVEN_NAME_TR("given_name_tr"),
    FAMILY_NAME_TR("family_name_tr"),
    DATE_OF_BIRTH("date_of_birth"),
    MAIN_FIELD("main_field"),
    FIELD_OF_STUDY("field_of_study"),
    OFFICIAL_DURATION_OF_PROGRAMME("official_duration_of_programme"),
    ACCESS_REQUIREMENTS("access_requirements"),
    MODE_OF_STUDY("mode_of_study"),
    CREDITS_GAINED("credits_gained"),
    CLASSIFICATION_SYSTEM("classification_system"),
    CLASSIFICATION_SYSTEM_DESCRIPTION("classification_system_description"),
    DURATION_OF_TRAINING("duration_of_training"),
    INFORMATION_ON_CERTIFICATION("information_on_certification"),
    PREVIOUS_DOCUMENT("previous_document"),
    DATE("date"),

    COMPONENT_N("component_n"),
    COMPONENT_TITLE("component_title"),
    COMPONENT_CREDITS("component_credits"),
    COMPONENT_SCORE("component_score"),
    COMPONENT_RATING_POINT("component_rating_point"),
    COMPONENT_NATIONAL_GRADE("component_national_grade"),
    RESEARCH_N("research_n"),
    RESEARCH_TITLE("research_title"),
    RESEARCH_CREDITS("research_credits"),
    RESEARCH_SCORE("research_score"),
    RESEARCH_RATING_POINT("research_rating_point"),
    RESEARCH_NATIONAL_GRADE("research_national_grade"),
    INTERNSHIP_N("internship_n"),
    INTERNSHIP_TITLE("internship_title"),
    INTERNSHIP_CREDITS("internship_credits"),
    INTERNSHIP_SCORE("internship_score"),
    INTERNSHIP_RATING_POINT("internship_rating_point"),
    INTERNSHIP_NATIONAL_GRADE("internship_national_grade"),
    ATTESTATION_N("attestation_n"),
    ATTESTATION_TITLE("attestation_title"),
    ATTESTATION_CREDITS("attestation_credits"),
    ATTESTATION_SCORE("attestation_score"),
    ATTESTATION_RATING_POINT("attestation_rating_point"),
    ATTESTATION_NATIONAL_GRADE("attestation_national_grade"),
    ECTS_CREDITS("ects_credits");

    private final String value;

    DocVariableConst(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
