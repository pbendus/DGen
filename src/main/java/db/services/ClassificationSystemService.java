package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ClassificationSystem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ClassificationSystemService extends BaseServiceImpl<ClassificationSystem> {
    public ClassificationSystemService(
            Dao<ClassificationSystem, Integer> dao) {
        super(dao);
    }

    public ClassificationSystem getByName(String name) throws SQLException {
        return getDao().queryForEq("name", name).get(0);
    }
}
