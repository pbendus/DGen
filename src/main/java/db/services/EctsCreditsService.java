package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EctsCredits;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class EctsCreditsService
    extends BaseServiceImpl<EctsCredits> {
  public EctsCreditsService(
      Dao<EctsCredits, Integer> dao) {
    super(dao);
  }

  public EctsCredits getByDurationOfStudy(int durationOfStudyId) throws SQLException {
    HashMap<String, Object> values = new HashMap<>();
    values.put("duration_of_study_id", durationOfStudyId);
    return getDao().queryForFieldValues(values).get(0);
  }
}
