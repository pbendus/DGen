package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.FieldOfStudy;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FieldOfStudyService extends BaseServiceImpl<FieldOfStudy> {
    public FieldOfStudyService(
            Dao<FieldOfStudy, Integer> dao) {
        super(dao);
    }

    public FieldOfStudy getByName(String name) throws SQLException {
        return getDao().queryForEq("name", name).get(0);
    }
}
