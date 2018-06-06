INSERT INTO field_of_study (name)
VALUES ('12 - Інформаційні технології / 12 - Information technologies');
-----------------------------------------------------------------------------------------------------
INSERT INTO main_field (name)
VALUES ('121 - Інженерія програмного забезпечення / 121 - Software engineering'),
  ('6.050103 - Програмна інженерія');
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
INSERT INTO ar_dos (access_requirements_id, duration_of_study_id)
VALUES (
  1, 1
);
INSERT INTO ar_dos (access_requirements_id, duration_of_study_id)
VALUES (
  2, 2
);
INSERT INTO ar_dos (access_requirements_id, duration_of_study_id)
VALUES (
  2, 3
);
INSERT INTO ar_dos (access_requirements_id, duration_of_study_id)
VALUES (
  3, NULL
);
------------------------------------------------------------------------------------------------------
INSERT INTO classification_system (name, criteria)
VALUES ('Диплом / Diploma',
        'Диплом засвідчує успішне виконання програми підготовки / Diploma certifies a successful academic performance');
INSERT INTO classification_system (name, criteria)
VALUES ('Диплом з відзнакою/ Diploma with Honors',
        'не менше як 75 відсотків відмінних оцінок з усіх навчальних дисциплін та практичної підготовки, з оцінками "добре" з інших дисциплін та з оцінками "відмінно" за результатами державної атестації / I 75% of ’’excellent” grades for academic courses (disciplines) and internships, 25% of “good” grades in other academic courses (disciplines), only “excellent” grades for State certification.');
-----------------------------------------------------------------------------------------------------
INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (1, '1 кредит ЄКТС – 36 годин / 1 ECTS credits – 36 study hours');
INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (2, '1 кредит ЄКТС – 30 годин / 1 ECTS credits – 30 study hours');
INSERT INTO ects_credits (duration_of_study_id, name)
VALUES (3, '1 кредит ЄКТС – 30 годин / 1 ECTS credits – 30 study hours');
-----------------------------------------------------------------------------------------------------
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('A', 90, 100);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('B', 82, 89);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('C', 75, 81);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('D', 67, 74);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('E', 60, 66);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('FX', 35, 59);
INSERT INTO rating_point (name, min_national_score, max_national_score) VALUES ('F', 0, 34);
-----------------------------------------------------------------------------------------------------
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Відмінно / Excellent', 90, 100);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Добре / Good', 75, 89);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Задовільно / Satisfactory', 60, 74);
INSERT INTO national_grade (name, min_national_score, max_national_score) VALUES ('Незадовільно / Fail', 0, 59);
-----------------------------------------------------------------------------------------------------
INSERT INTO educational_component_type (name) VALUES ('Дисципліни / Courses');
INSERT INTO educational_component_type (name) VALUES ('Курсові роботи / Research projects');
INSERT INTO educational_component_type (name) VALUES ('Практика / Internship');
INSERT INTO educational_component_type (name) VALUES ('Державна атестація / State attestation');
-----------------------------------------------------------------------------------------------------
insert into "group" (name) values ('ПІт-16-3');
insert into "group" (name) values ('ПІт-16-4');
insert into "group" (name) values ('ПІ-14-1');
insert into "group" (name) values ('ПІз-14-1');
insert into "group" (name) values ('ПІт-15-2');

insert into variables (variable, description) values
  ('diploma', 'Диплом'),
  ('reg', 'Реєстраційний №'),
  ('add_reg', 'Реєстраційний № додатка до диплому '),
  ('date_of_issue', 'Дата видачі'),
  ('date_of_issue_add', 'Дата видачі(додатково)'),
  ('family_name', 'Прізвище'),
  ('given_name', 'Ім’я та по батькові'),
  ('given_name_tr', 'Прізвище(транслітерація)'),
  ('family_name_tr', 'Ім’я та по батькові(транслітерація)'),
  ('date_of_birth', 'Дата народження '),
  ('main_field', 'Напрям підготовки/Спеціальність'),
  ('field_of_study', 'Галузь знань'),
  ('official_duration_of_programme', 'Офіційна тривалість програми'),
  ('access_requirements', 'Вимоги до вступу '),
  ('mode_of_study', 'Форма навчання '),
  ('credits_gained', 'Всього кредитів ЄКТС'),
  ('classification_system', 'Класифікація присвоєної кваліфікації'),
  ('classification_system_description', 'Критерія'),
  ('duration_of_training', 'Строки навчання у кожному ЗВО '),
  ('information_on_certification', 'Інформація про атестацію '),
  ('previous_document', 'Попередній документ'),
  ('date', 'Дата'),
  ('ects_credits', 'Кредит ЄКТС'),

  ('component_n', '№ Дисципліни'),
  ('component_title', 'Назва Дисципліни'),
  ('component_credits', 'Кредити Дисципліни'),
  ('component_score', 'Бали за національною шкалою Дисципліни'),
  ('component_rating_point', 'Рейтинг ЄКТС Дисципліни'),
  ('component_national_grade', 'Оцінка за національною шкалою Дисципліни'),

  ('research_n', '№ Курсової роботи'),
  ('research_title', 'Назва Курсової роботи'),
  ('research_credits', 'Кредити Курсової роботи'),
  ('research_score', 'Бали за національною шкалою Курсової роботи'),
  ('research_rating_point', 'Рейтинг ЄКТС Курсової роботи'),
  ('research_national_grade', 'Оцінка за національною шкалою Курсової роботи'),

  ('internship_n', '№ Практики'),
  ('internship_title', 'Назва Практики'),
  ('internship_credits', 'Кредити Практики'),
  ('internship_score', 'Бали за національною шкалою Практики'),
  ('internship_rating_point', 'Рейтинг ЄКТС Практики'),
  ('internship_national_grade', 'Оцінка за національною шкалою Практики'),

  ('attestation_n', '№ Державного екзамену'),
  ('attestation_title', 'Назва Державного екзамену'),
  ('attestation_credits', 'Кредити Державного екзамену'),
  ('attestation_score', 'Бали за національною шкалою Державного екзамену'),
  ('attestation_rating_point', 'Рейтинг ЄКТС Державного екзамену'),
  ('attestation_national_grade', 'Оцінка за національною шкалою Державного екзамену');