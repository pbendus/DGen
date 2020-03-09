package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.OfficialDurationOfProgramme;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class OfficialDurationOfProgrammeService
        extends BaseServiceImpl<OfficialDurationOfProgramme> {
    public OfficialDurationOfProgrammeService(
            Dao<OfficialDurationOfProgramme, Integer> dao) {
        super(dao);
    }

    public OfficialDurationOfProgramme getByModeAndDurationOfStudy(int modeOfStudyId,
                                                                   int durationOfStudyId) throws SQLException {
        HashMap<String, Object> values = new HashMap<>();
        values.put("mode_of_study_id", modeOfStudyId);
        values.put("duration_of_study_id", durationOfStudyId);
        return getDao().queryForFieldValues(values).get(0);
    }
}
