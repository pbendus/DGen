package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.OfficialDurationOfProgramme;
import org.springframework.stereotype.Service;

@Service
public class OfficialDurationOfProgrammeService
    extends BaseServiceImpl<OfficialDurationOfProgramme> {
  public OfficialDurationOfProgrammeService(
      Dao<OfficialDurationOfProgramme, Integer> dao) {
    super(dao);
  }
}
