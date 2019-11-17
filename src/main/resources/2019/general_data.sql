VALUES ('12 - Інформаційні технології / 12 - Information technologies');
-----------------------------------------------------------------------------------------------------
INSERT INTO main_field (name)
VALUES ('121 - Інженерія програмного забезпечення / 121 - Software engineering'),
  ('6.050103 - Програмна інженерія');
-----------------------------------------------------------------------------------------------------
INSERT INTO mode_of_study (name)
VALUES ('Очна / Full-time');
INSERT INTO mode_of_study (name)
VALUES ('Заочна / Part-time');
-----------------------------------------------------------------------------------------------------
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 1,
   '1 рік 4 місяці за очною формою навчання (90 кредитів ЕКТС) / 1 year 4 months of full-time study (90 ECTS credits)');
INSERT INTO official_duration_of_programme (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 1,
   '1 рік 4 місяці заочною формою навчання (90 кредитів ЕКТС) / 1 year 4 months of full-time study (90 ECTS credits)');
-----------------------------------------------------------------------------------------------------
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (1, 1,
   '1 рік і 4 місяці за очною формою навчання (90 кредитів ЄКТС) / 1 year and 4 months of full time study 01.09.2018 – 31.12.2019');
INSERT INTO duration_of_training (mode_of_study_id, duration_of_study_id, name)
VALUES
  (2, 1,
   '1 рік і 4 місяці за очною формою навчання (90 кредитів ЄКТС) / 1 year and 4 months of full time study 01.09.2018 – 31.12.2019');
------------------------------------------------------------------------------------------------------
INSERT INTO access_requirements (name)
VALUES (
  'Ступінь бакалавра (ОКР - спеціаліста). За результатами вступних випробувань / Bachelor’s (Specialist’s) degrees in relative qualifications. On the results of specialty examinations');
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
insert into variable (variable, description) values
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