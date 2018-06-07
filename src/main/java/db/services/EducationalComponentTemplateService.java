package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponentTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class EducationalComponentTemplateService
        extends BaseServiceImpl<EducationalComponentTemplate> {
    public EducationalComponentTemplateService(
            Dao<EducationalComponentTemplate, Integer> dao) {
        super(dao);
    }

    @Override
    public EducationalComponentTemplate getByName(String name) throws SQLException {
        return getDao().queryForEq("course_title", name).get(0);
    }
}
