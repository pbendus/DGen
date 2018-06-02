package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.MainField;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MainFieldService extends BaseServiceImpl<MainField> {
    public MainFieldService(
            Dao<MainField, Integer> dao) {
        super(dao);
    }

    public MainField getByName(String name) throws SQLException {
        return getDao().queryForEq("name", name).get(0);
    }
}
