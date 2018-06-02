package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponentTemplate;
import org.springframework.stereotype.Service;

@Service
public class EducationalComponentTemplateService
        extends BaseServiceImpl<EducationalComponentTemplate> {
    public EducationalComponentTemplateService(
            Dao<EducationalComponentTemplate, Integer> dao) {
        super(dao);
    }
}
