INSERT INTO field_of_study (name)
VALUES ('12 - Інформаційні технології / 12 - Information technologies');

INSERT INTO main_field (name)
VALUES ('121 - Інженерія програмного забезпечення / 121 - Software engineering');

INSERT INTO official_duration_of_programme (name)
VALUES
  ('1 рік 4 місяці за очною формою навчання (90 кредитів ЕКТС) / 1 year 4 months of full-time study (90 ECTS credits)');

INSERT INTO access_requirements (name)
VALUES (
  'Повна загальна середня освіта, вступ здійснюється за результатами ЗНО / Complete general secondary secondary education, on the results of the external independent examination.');

INSERT INTO access_requirements (name)
VALUES (
  'Початковий рівень вищої освіти, вступ здійснюється за результатами фахових вступних випробувань / The initial level of higher education, on the results of professional entrance examinations.');

INSERT INTO access_requirements (name)
VALUES (
  'Вступ здійснюється за результатами вступних випробувань / On the results of entrance examinations.');

INSERT INTO mode_of_study (name)
VALUES ('Очна / Full time');

INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES ('“Алгоритмічні моделі та методи поширення веб-базованих даних”',
        '“Algorithmic models and methods of web-based data distribution”');
INSERT INTO diploma_subject (subject_uk, subject_en)
VALUES ('“Додаток для створення додатів до диплому”',
        '“Software for creating diploma additions”');

INSERT INTO previous_document (name)
VALUES ('Диплом бакалавра / Bachelor’s Diploma B16 № 197185');
INSERT INTO previous_document (name)
VALUES ('Диплом бакалавра / Bachelor’s Diploma B17 № 197185');

INSERT INTO classification_system (name, criteria)
VALUES ('Диплом / Diploma',
        'Диплом засвідчує успішне виконання програми підготовки / Diploma certifies a successful academic performance');

INSERT INTO professional_status (name)
VALUES (
  'Робота на таких посадах: професіонал в галузі програмування, розробник комп’ютерних програм, програміст (бази даних), викладач вищого навчального закладу / Qualified for the following jobs: information and communications technology professional, software and applications developer, database and network professional, university tutor');

INSERT INTO protocol (name_uk, name_en)
VALUES ('(протокол №2 від 20/12/2017)', '(protocol №2 dated 20/12/2017)');

insert into ects_credits (name) values ('1 кредит ЄКТС – 30 годин / 1 ECTS credits – 30 study hours');
insert into ects_credits (name) values ('1 кредит ЄКТС – 36 годин / 1 ECTS credits – 36 study hours');

INSERT INTO duration_of_training (name, mode_of_study_id)
VALUES (
  '1 рік і 4 місяці за очною формою навчання (90 кредитів ЄКТС) / 1 year and 4 months of full time study 01.09.2016 – 31.12.2017',
  1);

INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, diploma_subject_id, previous_document_id)
VALUES ('Баклицький', 'Юрій Володимирович', 'Baklytskyi', 'Yurii', '1995-02-25', 1, 1, 1);
INSERT INTO student (family_name, given_name, family_name_tr, given_name_tr, date_of_birth, protocol_id, diploma_subject_id, previous_document_id)
VALUES ('Депутат', 'Андрій Богданович', 'Deputat', 'Andrii', '1997-04-06', 1, 2, 2);

INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, mode_of_study_id, professional_status_id, classification_system_id, duration_of_training_id)
VALUES ('M17 № 086783', '555м/17', '555м/17', 1, '2017-12-31', 1, 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO diploma (number, registration_number, addition_registration_number, ects_credits_id, date_of_issue, student_id, main_field_id, field_of_study_id, official_duration_of_programme_id, access_requirements_id, mode_of_study_id, professional_status_id, classification_system_id, duration_of_training_id)
VALUES ('M18 № 086784', '556м/17', '556м/17', 2, '2017-12-31', 2, 1, 1, 1, 1, 1, 1, 1, 1);

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

INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Охорона праці в галузі / Labour Safety in Field');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 5, 1, 'Архітектура програмного забезпечення / Software Architecture');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 6, 1, 'Моделювання ІТ інновацій / IT Innovations Modeling');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 4, 1, 'Інноваційні підходи створення ІТ проектів / Innovative Approaches to IT Project Development ');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 5, 1, 'Проектування сховищ даних / Data Warehouse Design');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 5, 1, 'Інженерія вимог / Requirements Engineering');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 4, 1, 'Розроблення web-додатків в розподілених системах / Development of Web-Based Applications in Distributed Systems');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 4, 1, 'Системологія інженерії програмного забезпечення / Software Engineering Systemology');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 4, 1, 'Спецкурс керівника / Manager’s Special Course');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Формальні методи програмної інженерії / Formal Methods of Software Engineering');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Психологія та педагогіка вищої школи / Higher Education Psychology and Pedagogics');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Філософські проблеми наукового пізнання / Philosophical Problems of Scientific Cognition');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Логічний аналіз / Logic Analysis');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 4, 1, 'Формальні та програмні засоби побудови новітніх інтелектуальних додатків та застосувань / Formal and Software Tools of Innovative Intelligent Applications Arrangement');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (1, 3, 1, 'Моделювання процесів розробки програмного забезпечення / Modeling of Software Development Processes');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (2, 1, 1, 'Моделювання ІТ інновацій / IT Innovations Modeling');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (3, 9, 1, 'Науково-дослідна практика / Research Practical Training');
INSERT INTO educational_component_template (educational_component_type_id, credits, main_field_id, course_title) VALUES (4, 21, 1, 'Магістерська робота / Master’s Thesis');

INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (1, 90, 1, 1, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (2, 80, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (3, 84, 2, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (4, 84, 2, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (5, 80, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (6, 80, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (7, 85, 2, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (8, 80, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (9, 75, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (10, 80, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (11, 94, 1, 1, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (12, 90, 1, 1, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (13, 82, 2, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (14, 75, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (15, 75, 3, 2, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (16, 90, 1, 1, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (17, 90, 1, 1, 1);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (18, 95, 1, 1, 1);

INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (1, 90, 1, 1, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (2, 80, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (3, 84, 2, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (4, 84, 2, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (5, 80, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (6, 80, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (7, 85, 2, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (8, 80, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (9, 75, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (10, 80, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (11, 94, 1, 1, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (12, 90, 1, 1, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (13, 82, 2, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (14, 75, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (15, 75, 3, 2, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (16, 90, 1, 1, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (17, 90, 1, 1, 2);
INSERT INTO educational_component (educational_component_template_id, national_score, rating_point_id, national_grade_id, diploma_id) VALUES (18, 95, 1, 1, 2);
