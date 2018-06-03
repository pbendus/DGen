-- CREATE DATABASE IF NOT EXISTS bachelor_diploma;

---------------------------------------------------------------------------------
-- Information technologies (translate)
CREATE TABLE field_of_study (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE
);

-- Software Engineering
CREATE TABLE main_field (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE
);

---------------------------------------------------------------------------------
--  1 year 4 months of full-time study (90 ECTS credits)
CREATE TABLE duration_of_study (
  id    INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  value VARCHAR(255) NOT NULL UNIQUE
);

---------------------------------------------------------------------------------
--  1 year 4 months of full-time study (90 ECTS credits)
CREATE TABLE official_duration_of_programme (
  id                   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name                 VARCHAR(255) NOT NULL UNIQUE,
  mode_of_study_id     INTEGER      NOT NULL,
  duration_of_study_id INTEGER      NOT NULL,

  FOREIGN KEY (mode_of_study_id) REFERENCES mode_of_study (id),
  FOREIGN KEY (duration_of_study_id) REFERENCES duration_of_study (id)

);

---------------------------------------------------------------------------------
-- Bachelor’s (Specialist’s) degrees in relative qualifications. On the results of specialty examinations
CREATE TABLE access_requirements (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE diploma_subject (
  id         INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  subject_uk TEXT    NOT NULL,
  subject_en TEXT    NOT NULL
);

CREATE TABLE previous_document (
  id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT    NOT NULL
);

---------------------------------------------------------------------------------
-- Duration of Study ID & Access Requirements connection
CREATE TABLE ar_dos (
  id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  duration_of_study_id   INTEGER NULL,
  access_requirements_id INTEGER NOT NULL
);

---------------------------------------------------------------------------------
-- Full time
CREATE TABLE mode_of_study (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE
);
---------------------------------------------------------------------------------
-- Overall classification of the qualification
CREATE TABLE classification_system (
  id       INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name     VARCHAR(255) NOT NULL UNIQUE,
  criteria VARCHAR(255) NOT NULL UNIQUE
);

---------------------------------------------------------------------------------
-- PROTOCOL
CREATE TABLE protocol (
  id      INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name_uk VARCHAR(255) NOT NULL,
  name_en VARCHAR(255) NOT NULL
);

---------------------------------------------------------------------------------
-- DURATION OF TRAINING
CREATE TABLE duration_of_training (
  id                   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name                 VARCHAR(255) NOT NULL,
  mode_of_study_id     INTEGER      NOT NULL,
  duration_of_study_id INTEGER      NOT NULL,

  FOREIGN KEY (mode_of_study_id) REFERENCES mode_of_study (id),
  FOREIGN KEY (duration_of_study_id) REFERENCES duration_of_study (id)
);

---------------------------------------------------------------------------------
-- GROUPS
CREATE TABLE "group" (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE
);

---------------------------------------------------------------------------------
-- Student
CREATE TABLE student (
  id                   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  family_name          VARCHAR(255) NOT NULL,
  given_name           VARCHAR(255) NOT NULL,
  family_name_tr       VARCHAR(255) NOT NULL,
  given_name_tr        VARCHAR(255) NOT NULL,
  date_of_birth        DATE         NOT NULL,
  protocol_id          INTEGER      NOT NULL,
  previous_document_id INTEGER      NOT NULL,
  mode_of_study_id     INTEGER      NOT NULL,
  duration_of_study_id INTEGER      NOT NULL,
  group_id             INTEGER      NOT NULL,

  FOREIGN KEY (mode_of_study_id) REFERENCES mode_of_study (id),
  FOREIGN KEY (duration_of_study_id) REFERENCES duration_of_study (id),
  FOREIGN KEY (protocol_id) REFERENCES protocol (id),
  FOREIGN KEY (previous_document_id) REFERENCES previous_document (id),
  FOREIGN KEY (group_id) REFERENCES "group" (id)
);
---------------------------------------------------------------------------------
--  ECTS credits
CREATE TABLE ects_credits (
  id                   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name                 VARCHAR(255) NOT NULL,
  duration_of_study_id INTEGER      NOT NULL,

  FOREIGN KEY (duration_of_study_id) REFERENCES duration_of_study (id)
);

---------------------------------------------------------------------------------
-- Diploma
CREATE TABLE diploma (
  id                                INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  number                            VARCHAR(255) NOT NULL UNIQUE,
  registration_number               VARCHAR(255) NOT NULL,
  addition_registration_number      VARCHAR(255) NOT NULL,
  ects_credits_id                   VARCHAR(255) NOT NULL,
  date_of_issue                     DATE         NOT NULL,
  student_id                        INTEGER      NOT NULL UNIQUE,
  main_field_id                     INTEGER      NOT NULL,
  field_of_study_id                 INTEGER      NOT NULL,
  official_duration_of_programme_id INTEGER      NOT NULL,
  access_requirements_id            INTEGER      NOT NULL,
  classification_system_id          INTEGER      NOT NULL,
  duration_of_training_id           INTEGER      NOT NULL,
  diploma_subject_id                INTEGER      NOT NULL,

  FOREIGN KEY (student_id) REFERENCES student (id),
  FOREIGN KEY (main_field_id) REFERENCES main_field (id),
  FOREIGN KEY (field_of_study_id) REFERENCES field_of_study (id),
  FOREIGN KEY (official_duration_of_programme_id) REFERENCES official_duration_of_programme (id),
  FOREIGN KEY (access_requirements_id) REFERENCES access_requirements (id),
  FOREIGN KEY (duration_of_training_id) REFERENCES duration_of_training (id),
  FOREIGN KEY (classification_system_id) REFERENCES classification_system (id),
  FOREIGN KEY (diploma_subject_id) REFERENCES diploma_subject (id),
  FOREIGN KEY (ects_credits_id) REFERENCES ects_credits (id)
);
-- A, B, C, D, E, FX, F
CREATE TABLE rating_point (
  id                 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name               CHAR(2) NOT NULL UNIQUE,
  min_national_score INTEGER NOT NULL,
  max_national_score INTEGER NOT NULL
);

--
CREATE TABLE national_grade (
  id                 INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name               VARCHAR(255) NOT NULL UNIQUE,
  min_national_score INTEGER      NOT NULL,
  max_national_score INTEGER      NOT NULL
);

-- Courses, Research Projects, Internship, State attestation
CREATE TABLE educational_component_type (
  id   INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE
);

---------------------------------------------------------------------------------
-- Template
CREATE TABLE educational_component_template (
  id                            INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  educational_component_type_id INTEGER      NOT NULL,
  credits                       REAL         NOT NULL,
  course_title                  VARCHAR(255) NOT NULL,

  FOREIGN KEY (educational_component_type_id) REFERENCES educational_component_type (id)
);

---------------------------------------------------------------------------------
-- All grades
CREATE TABLE educational_component (
  id                                INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  educational_component_template_id INTEGER NOT NULL,
  national_score                    INTEGER NOT NULL,
  rating_point_id                   INTEGER NOT NULL,
  national_grade_id                 INTEGER NOT NULL,
  diploma_id                        INTEGER NOT NULL,

  FOREIGN KEY (educational_component_template_id) REFERENCES educational_component_template (id),
  FOREIGN KEY (rating_point_id) REFERENCES rating_point (id),
  FOREIGN KEY (national_grade_id) REFERENCES national_grade (id),
  FOREIGN KEY (diploma_id) REFERENCES diploma (id)
);

---------------------------------------------------------------------------------
-- All grades
CREATE TABLE variables (
  id          INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
  variable    VARCHAR(255) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL
);


