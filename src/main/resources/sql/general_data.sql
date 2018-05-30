INSERT INTO field_of_study (name)
VALUES ('12 - Інформаційні технології / 12 - Information technologies');
-----------------------------------------------------------------------------------------------------
INSERT INTO main_field (name)
VALUES ('121 - Інженерія програмного забезпечення / 121 - Software engineering');
-----------------------------------------------------------------------------------------------------
INSERT INTO duration_of_study (value) VALUES ('Для школярів(4 роки)');
INSERT INTO duration_of_study (value) VALUES ('Для скороченого терміну(3 роки)');
INSERT INTO duration_of_study (value) VALUES ('Для скороченого терміну(2 роки)');
-----------------------------------------------------------------------------------------------------
INSERT INTO mode_of_study (name)
VALUES ('Очна / Full-time');
INSERT INTO mode_of_study (name)
VALUES ('Заочна / Part-time');
-----------------------------------------------------------------------------------------------------
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 1, '4 роки за очною формою навчання (240 кредитів ЕКТС) / 4 years of full-time study (240 ECTS credits)');
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 2, '3 роки за очною формою навчання (240 кредитів ЕКТС) / 3 years of full-time study (240 ECTS credits)');
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 3, '2 роки за очною формою навчання (240 кредитів ЕКТС) / 2 years of full-time study (240 ECTS credits)');

INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 1, '4 роки за заочною формою навчання (240 кредитів ЕКТС) / 4 years of part-time study (240 ECTS credits)');
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 2, '3 роки за заочною формою навчання (240 кредитів ЕКТС) / 3 years of part-time study (240 ECTS credits)');
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 3, '2 роки за заочною формою навчання (240 кредитів ЕКТС) / 2 years of part-time study (240 ECTS credits)');
-----------------------------------------------------------------------------------------------------
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 1, '4 роки за очною формою навчання (240 кредитів ЕКТС) / 4 years of full-time study 01.09.2014 – 30.06.2018');
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 2, '3 роки за очною формою навчання (240 кредитів ЕКТС) / 3 years of full-time study 01.09.2015 – 30.06.2018');
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 3, '2 роки за очною формою навчання (240 кредитів ЕКТС) / 2 years of full-time study 01.09.2016 – 30.06.2018');

INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 1, '4 роки за заочною формою навчання (240 кредитів ЕКТС) / 4 years of part-time study 01.09.2014 – 30.06.2018 ');
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 2, '3 роки за заочною формою навчання (240 кредитів ЕКТС) / 3 years of part-time study 01.09.2015 – 30.06.2018');
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 3, '2 роки за заочною формою навчання (240 кредитів ЕКТС) / 2 years of part-time study 01.09.2016 – 30.06.2018');
------------------------------------------------------------------------------------------------------
INSERT INTO access_requirements (name)
VALUES (
  'Повна загальна середня освіта, вступ здійснюється за результатами ЗНО / Complete general secondary secondary education, on the results of the external independent examination.');
INSERT INTO access_requirements (name)
VALUES (
  'Початковий рівень вищої освіти, вступ здійснюється за результатами фахових вступних випробувань / The initial level of higher education, on the results of professional entrance examinations.');
INSERT INTO access_requirements (name)
VALUES (
  'Вступ здійснюється за результатами вступних випробувань / On the results of entrance examinations.');
------------------------------------------------------------------------------------------------------
INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES (
  '«Розробка програмного продукту автоматичної генерації додатків європейського взірця для студентів, що навчаються за ступенем вищої освіти “бакалавр”»',
  '«Development of software for automatic generation of applications of the European model for students studying in the higher education" Bachelor"»');
INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES ('«Розробка WEB-базованого програмного забезпечення для покращення роботи готельного бізнесу»',
        '«Development of WEB-based software for improvement of hotel business»');

INSERT INTO previous_document (name)
VALUES ('Диплом бакалавра / Bachelor’s Diploma B16 № 197185');
INSERT INTO previous_document (name)
VALUES ('Диплом бакалавра / Bachelor’s Diploma B17 № 197185');

INSERT INTO classification_system (name, criteria)
VALUES ('Диплом / Diploma',
        'Диплом засвідчує успішне виконання програми підготовки / Diploma certifies a successful academic performance');
INSERT INTO classification_system (name, criteria)
VALUES ('Диплом з відзнакою/ Diploma with Honors',
        'не менше як 75 відсотків відмінних оцінок з усіх навчальних дисциплін та практичної підготовки, з оцінками "добре" з інших дисциплін та з оцінками "відмінно" за результатами державної атестації / I 75% of ’’excellent” grades for academic courses (disciplines) and internships, 25% of “good” grades in other academic courses (disciplines), only “excellent” grades for State certification.');

INSERT INTO protocol (name_uk, name_en)
VALUES ('(протокол №2 від 20/12/2017)', '(protocol №2 dated 20/12/2017)');

INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (2, '1 кредит ЄКТС – 30 годин / 1 ECTS credits – 30 study hours');
INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (3, '1 кредит ЄКТС – 30 годин / 1 ECTS credits – 30 study hours');
INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (1, '1 кредит ЄКТС – 36 годин / 1 ECTS credits – 36 study hours');

INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, diploma_subject_id, previous_document_id)
VALUES ('Депутат', 'Андрій Богданович', 'Deputat', 'Andrii', '1997-04-06', 1, 1, 1);
INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, diploma_subject_id, previous_document_id)
VALUES ('Баклицький', 'Юрій Володимирович', 'Baklytskyi', 'Yurii', '1995-02-25', 1, 2, 2);

INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, classification_system_id, duration_of_training_id)
VALUES ('M17 № 086783', '557м/18', '557м/18', 3, '2018-06-30', 1, 1, 1, 1, 1, 2, 1);
INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, classification_system_id, duration_of_training_id)
VALUES ('M18 № 086784', '558м/18', '558м/18', 1, '2018-06-30', 2, 1, 1, 1, 2, 1, 1);

INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('A', 90, 100);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('B', 82, 89);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('C', 75, 81);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('D', 67, 74);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('E', 60, 66);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('FX', 35, 59);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('F', 0, 34);

INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Відмінно / Excellent', 90, 100);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Добре / Good', 75, 89);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Задовільно / Satisfactory', 60, 74);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Незадовільно / Fail', 0, 59);

INSERT INTO educational_component_type (name) VALUES ('Дисципліни / Courses');
INSERT INTO educational_component_type (name) VALUES ('Курсові роботи / Research projects');
INSERT INTO educational_component_type (name) VALUES ('Практика / Internship');
INSERT INTO educational_component_type (name) VALUES ('Державна атестація / State attestation');
