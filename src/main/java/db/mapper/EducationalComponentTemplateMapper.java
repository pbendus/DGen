package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.EducationalComponentTemplate;

@Service
public class EducationalComponentTemplateMapper
    extends
    Mapper<db.entities.EducationalComponentTemplate, ui.models.EducationalComponentTemplate> {

  private EducationalComponentTypeMapper educationalComponentTypeMapper;

  @Autowired
  public EducationalComponentTemplateMapper(
      EducationalComponentTypeMapper educationalComponentTypeMapper,
      MainFieldMapper mainFieldMapper) {
    this.educationalComponentTypeMapper = educationalComponentTypeMapper;
  }

  @Override
  public EducationalComponentTemplate map(db.entities.EducationalComponentTemplate value) {
    final EducationalComponentTemplate educationalComponentTemplate =
        new EducationalComponentTemplate();
    educationalComponentTemplate.setId(value.getId());
    educationalComponentTemplate.setCourseTitle(value.getCourseTitle());
    educationalComponentTemplate.setCredits(value.getCredits());
    educationalComponentTemplate.setEducationalComponentType(
        educationalComponentTypeMapper.map(value.getEducationalComponentType()));
    return educationalComponentTemplate;
  }

  @Override
  public db.entities.EducationalComponentTemplate reverseMap(EducationalComponentTemplate value) {
    final db.entities.EducationalComponentTemplate educationalComponentTemplate =
        new db.entities.EducationalComponentTemplate();
    educationalComponentTemplate.setId(value.getId());
    educationalComponentTemplate.setCourseTitle(value.getCourseTitle());
    educationalComponentTemplate.setCredits(value.getCredits());
    educationalComponentTemplate.setEducationalComponentType(
        educationalComponentTypeMapper.reverseMap(value.getEducationalComponentType()));
    return educationalComponentTemplate;
  }
}
