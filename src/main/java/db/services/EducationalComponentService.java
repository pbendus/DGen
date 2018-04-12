package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponent;
import java.sql.SQLException;

public class EducationalComponentService extends BaseServiceImpl<EducationalComponent> {
  private RatingPointService ratingPointService;
  private NationalGradeService nationalGradeService;

  public EducationalComponentService(
      Dao<EducationalComponent, Integer> dao, RatingPointService ratingPointService,
      NationalGradeService nationalGradeService) {
    super(dao);
    this.ratingPointService = ratingPointService;
    this.nationalGradeService = nationalGradeService;
  }

  @Override public int create(EducationalComponent educationalComponent) throws SQLException {
    int nationalScore = educationalComponent.getNationalScore();
    educationalComponent.setRatingPoint(
        ratingPointService.getRatingPointByScore(nationalScore));
    educationalComponent.setNationalGrade(
        nationalGradeService.getNationalGradeByScore(nationalScore));
    return super.create(educationalComponent);
  }

  @Override public int update(EducationalComponent educationalComponent) throws SQLException {
    int nationalScore = educationalComponent.getNationalScore();
    educationalComponent.setRatingPoint(
        ratingPointService.getRatingPointByScore(nationalScore));
    educationalComponent.setNationalGrade(
        nationalGradeService.getNationalGradeByScore(nationalScore));
    return super.update(educationalComponent);
  }
}
