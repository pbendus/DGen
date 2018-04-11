INSERT INTO field_of_study (name)
VALUES ('12 - Інформаційні технології / 12 - Information technologies');

INSERT INTO main_field (name)
VALUES ('121 - Інженерія програмного забезпечення / 121 - Software engineering');

INSERT INTO official_duration_of_programme (value)
VALUES
  ('1 рік 4 місяці за очною формою навчання (90 кредитів ЕКТС) / 1 year 4 months of full-time study (90 ECTS credits)');

INSERT INTO access_requirements (value)
VALUES (
  'Ступінь бакалавра (ОКР - спеціаліста). За результатами вступних випробувань / Bachelor’s (Specialist’s) degrees in relative qualifications. On the results of specialty examinations');

INSERT INTO mode_of_study (value)
VALUES ('Очна / Full time');

INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES ('“Алгоритмічні моделі та методи поширення веб-базованих даних”',
        '“Algorithmic models and methods of web-based data distribution”');

INSERT INTO previous_document (value)
VALUES ('Диплом бакалавра / Bachelor’s Diploma B16 № 197185');

INSERT INTO classification_system (value, criteria)
VALUES ('Диплом / Diploma',
        'Диплом засвідчує успішне виконання програми підготовки / Diploma certifies a successful academic performance');

INSERT INTO professional_status (value)
VALUES (
  'Робота на таких посадах: професіонал в галузі програмування, розробник комп’ютерних програм, програміст (бази даних), викладач вищого навчального закладу / Qualified for the following jobs: information and communications technology professional, software and applications developer, database and network professional, university tutor');

INSERT INTO protocol (name_uk, name_en)
VALUES ('(протокол №2 від 20/12/2017)', '(protocol №2 dated 20/12/2017)');

INSERT INTO duration_of_training (value, mode_of_study_id)
VALUES (
  '1 рік і 4 місяці за очною формою навчання (90 кредитів ЄКТС) / 1 year and 4 months of full time study 01.09.2016 – 31.12.2017',
  1);

INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, diploma_subject_id, previpus_document_id)
VALUES ('Баклицький', 'Юрій Володимирович', 'Baklytskyi', 'Yurii', '1995-02-25', 1, 1, 1);

INSERT INTO diploma (number, registration_number, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, mode_of_study_id, professional_status_id, classification_system_id, duration_of_training_id)
VALUES ('M17 № 086783', '555м/17', '2017-12-31', 1, 1, 1, 1, 1, 1, 1, 1, 1);

INSERT INTO rating_point (value) VALUES ('A');
INSERT INTO rating_point (value) VALUES ('B');
INSERT INTO rating_point (value) VALUES ('C');
INSERT INTO rating_point (value) VALUES ('D');
INSERT INTO rating_point (value) VALUES ('E');
INSERT INTO rating_point (value) VALUES ('FX');
INSERT INTO rating_point (value) VALUES ('F');

INSERT INTO national_grade (value) VALUES ('Відмінно / Excellent');
INSERT INTO national_grade (value) VALUES ('Добре / Good');
INSERT INTO national_grade (value) VALUES ('Задовільно / Satisfactory');
INSERT INTO national_grade (value) VALUES ('Незадовільно / Fail');

INSERT INTO educational_component_type (name) VALUES ('Дисципліни / Courses');
INSERT INTO educational_component_type (name) VALUES ('Курсові роботи / Research projects');
INSERT INTO educational_component_type (name) VALUES ('Практика / Internship');
INSERT INTO educational_component_type (name) VALUES ('Державна атестація / State attestation');

INSERT INTO educational_component (educational_component_type_id, credit, national_score, rating_point_id, national_grade_id, diploma_id, course_title)
VALUES (1, 3, 90, 1, 1, 1, 'Охорона праці в галузі / Labour Safety in Field');
INSERT INTO educational_component (educational_component_type_id, credit, national_score, rating_point_id, national_grade_id, diploma_id, course_title)
VALUES (2, 1, 90, 1, 1, 1, 'Моделювання ІТ інновацій / IT Innovations Modeling');
INSERT INTO educational_component (educational_component_type_id, credit, national_score, rating_point_id, national_grade_id, diploma_id, course_title)
VALUES (3, 9, 90, 1, 1, 1, 'Науково-дослідна практика / Research Practical Training');
INSERT INTO educational_component (educational_component_type_id, credit, national_score, rating_point_id, national_grade_id, diploma_id, course_title)
VALUES (4, 21, 95, 1, 1, 1, 'Магістерська робота / Master’s Thesis');