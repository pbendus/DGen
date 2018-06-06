INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES (
  '«Розробка програмного продукту автоматичної генерації додатків європейського взірця для студентів, що навчаються за ступенем вищої освіти “бакалавр”»',
  '«Development of software for automatic generation of applications of the European model for students studying in the higher education" Bachelor"»');
INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES ('«Розробка WEB-базованого програмного забезпечення для покращення роботи готельного бізнесу»',
        '«Development of WEB-based software for improvement of hotel business»');
-----------------------------------------------------------------------------------------------------
INSERT INTO previous_document (name, name_en)
VALUES ('Диплом бакалавра', 'Bachelor’s Diploma B16 № 197185');
INSERT INTO previous_document (name, name_en)
VALUES ('Диплом бакалавра', ' Bachelor’s Diploma B17 № 197185');
-----------------------------------------------------------------------------------------------------
INSERT INTO protocol (name_uk, name_en)
VALUES ('(протокол №2 від 20/12/2017)', '(protocol №2 dated 20/12/2017)');
-----------------------------------------------------------------------------------------------------
INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, previous_document_id, mode_of_study_id, duration_of_study_id, group_id)
VALUES ('Депутат', 'Андрій Богданович', 'Deputat', 'Andrii', '1997-04-06', 1, 1, 1, 1, 3);
INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, previous_document_id, mode_of_study_id, duration_of_study_id, group_id)
VALUES ('Дівнич', 'Ігор Володимирович', 'Divny`ch ', 'Igor', '1995-02-25', 1, 2, 1, 2, 4);
-----------------------------------------------------------------------------------------------------
INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, classification_system_id, duration_of_training_id, diploma_subject_id)
VALUES ('M17 № 086783', '557м/18', '557м/18', 3, '2018-06-30', 1, 1, 1, 1, 1, 2, 1, 1);
INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, classification_system_id, duration_of_training_id, diploma_subject_id)
VALUES ('M18 № 086784', '558м/18', '558м/18', 1, '2018-06-30', 2, 1, 1, 1, 2, 1, 1, 2);
-----------------------------------------------------------------------------------------------------