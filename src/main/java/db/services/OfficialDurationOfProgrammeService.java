package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.NationalGrade;
import db.entities.OfficialDurationOfProgramme;

public class OfficialDurationOfProgrammeService
    extends BaseServiceImpl<OfficialDurationOfProgramme> {
  public OfficialDurationOfProgrammeService(
      Dao<OfficialDurationOfProgramme, Integer> dao) {
    super(dao);
  }
}
