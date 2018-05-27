package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.models.EducationalComponentTemplate;

@Service
public class EducationalComponentTemplateMapper
    extends
    Mapper<db.entities.EducationalComponentTemplate, ui.models.EducationalComponentTemplate> {

  private EducationalComponentTypeMapper educationalComponentTypeMapper;
  private MainFieldMapper mainFieldMapper;

  @Autowired
  public EducationalComponentTemplateMapper(
      EducationalComponentTypeMapper educationalComponentTypeMapper,
      MainFieldMapper mainFieldMapper) {
    this.educationalComponentTypeMapper = educationalComponentTypeMapper;
    this.mainFieldMapper = mainFieldMapper;
  }

  @Override
  public EducationalComponentTemplate map(db.entities.EducationalComponentTemplate value) {
    final EducationalComponentTemplate educationalComponentTemplate =
        new EducationalComponentTemplate();
    educationalComponentTemplate.setId(value.getId());
    educationalComponentTemplate.setCourseTitle(value.getCourseTitle());
    educationalComponentTemplate.setCredit(value.getCredit());
    educationalComponentTemplate.setEducationalComponentType(
        educationalComponentTypeMapper.map(value.getEducationalComponentType()));
    educationalComponentTemplate.setMainField(mainFieldMapper.map(value.getMainField()));
    return educationalComponentTemplate;
  }

  @Override
  public db.entities.EducationalComponentTemplate reverseMap(EducationalComponentTemplate value) {
    final db.entities.EducationalComponentTemplate educationalComponentTemplate =
        new db.entities.EducationalComponentTemplate();
    educationalComponentTemplate.setId(value.getId());
    educationalComponentTemplate.setCourseTitle(value.getCourseTitle());
    educationalComponentTemplate.setCredit(value.getCredit());
    educationalComponentTemplate.setEducationalComponentType(
        educationalComponentTypeMapper.reverseMap(value.getEducationalComponentType()));
    educationalComponentTemplate.setMainField(mainFieldMapper.reverseMap(value.getMainField()));
    return educationalComponentTemplate;
  }
}
