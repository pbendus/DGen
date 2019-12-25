update educational_component
set national_grade_id = 4
where national_score < 60;
update educational_component
set national_grade_id = 3
where national_score > 59
  AND national_score < 75;
update educational_component
set national_grade_id = 2
where national_score > 74
  AND national_score < 90;
update educational_component
set national_grade_id = 1
where national_score > 89;

update educational_component
set rating_point_id = 1
where national_score > 89;
update educational_component
set rating_point_id = 2
where national_score > 81
  AND national_score < 90;
update educational_component
set rating_point_id = 3
where national_score > 74
  AND national_score < 82;
update educational_component
set rating_point_id = 4
where national_score > 66
  AND national_score < 75;
update educational_component
set rating_point_id = 5
where national_score > 59
  AND national_score < 67;
update educational_component
set rating_point_id = 6
where national_score > 34
  AND national_score < 60;
update educational_component
set rating_point_id = 7
where national_score < 34;
