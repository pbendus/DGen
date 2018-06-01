package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DurationOfTraining;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class DurationOfTrainingService extends BaseServiceImpl<DurationOfTraining> {
  public DurationOfTrainingService(
      Dao<DurationOfTraining, Integer> dao) {
    super(dao);
  }

  public DurationOfTraining getByModeAndDurationOfStudy(int modeOfStudyId, int durationOfStudyId)
      throws SQLException {
    final HashMap<String, Object> values = new HashMap<>();
    values.put("mode_of_study_id", modeOfStudyId);
    values.put("duration_of_study_id", durationOfStudyId);
    return getDao().queryForFieldValues(values).get(0);
  }

  public DurationOfTraining getByName(String name) throws SQLException {
    return getDao().queryForEq("name", name).get(0);
  }
}
