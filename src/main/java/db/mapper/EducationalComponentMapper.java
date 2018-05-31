package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ui.models.EducationalComponent;

@Service
@Lazy
public class EducationalComponentMapper
    extends Mapper<db.entities.EducationalComponent, ui.models.EducationalComponent> {

  private DiplomaMapper diplomaMapper;
  private EducationalComponentTemplateMapper educationalComponentTemplateMapper;
  private NationalGradeMapper nationalGradeMapper;
  private RatingPointMapper ratingPointMapper;

  @Autowired
  public EducationalComponentMapper(DiplomaMapper diplomaMapper,
      EducationalComponentTemplateMapper educationalComponentTemplateMapper,
      NationalGradeMapper nationalGradeMapper, RatingPointMapper ratingPointMapper) {
    this.diplomaMapper = diplomaMapper;
    this.educationalComponentTemplateMapper = educationalComponentTemplateMapper;
    this.nationalGradeMapper = nationalGradeMapper;
    this.ratingPointMapper = ratingPointMapper;
  }

  @Override public EducationalComponent map(db.entities.EducationalComponent value) {
    final EducationalComponent educationalComponent = new EducationalComponent();
    educationalComponent.setId(value.getId());
    educationalComponent.setDiploma(diplomaMapper.map(value.getDiploma()));
    educationalComponent.setEducationalComponentTemplate(
        educationalComponentTemplateMapper.map(value.getEducationalComponentTemplate()));
    educationalComponent.setNationalGrade(nationalGradeMapper.map(value.getNationalGrade()));
    educationalComponent.setNationalScore(value.getNationalScore());
    educationalComponent.setRatingPoint(ratingPointMapper.map(value.getRatingPoint()));
    educationalComponent.setCredits(value.getEducationalComponentTemplate().getCredits());
    educationalComponent.setCourseTitle(value.getEducationalComponentTemplate().getCourseTitle());
    educationalComponent.setEducationalComponentType(
        value.getEducationalComponentTemplate().getEducationalComponentType().getName());
    return educationalComponent;
  }

  @Override public db.entities.EducationalComponent reverseMap(EducationalComponent value) {
    final db.entities.EducationalComponent educationalComponent =
        new db.entities.EducationalComponent();
    educationalComponent.setId(value.getId());
    educationalComponent.setDiploma(diplomaMapper.reverseMap(value.getDiploma()));
    educationalComponent.setEducationalComponentTemplate(
        educationalComponentTemplateMapper.reverseMap(value.getEducationalComponentTemplate()));
    educationalComponent.setNationalGrade(nationalGradeMapper.reverseMap(value.getNationalGrade()));
    educationalComponent.setNationalScore(value.getNationalScore());
    educationalComponent.setRatingPoint(ratingPointMapper.reverseMap(value.getRatingPoint()));
    return educationalComponent;
  }
}
