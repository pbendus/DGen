package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.AccessRequirements;
import db.entities.ArDos;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ArDosService extends BaseServiceImpl<ArDos> {

    public ArDosService(Dao<ArDos, Integer> dao) {
        super(dao);
    }

    public AccessRequirements getAccessRequirementsByDurationOfStudyId(int id) throws SQLException {
        return getDao().queryForEq("duration_of_study_id", id).get(0).getAccessRequirements();
    }
}
