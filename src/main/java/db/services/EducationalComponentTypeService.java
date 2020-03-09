package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponentType;
import org.springframework.stereotype.Service;

@Service
public class EducationalComponentTypeService extends BaseServiceImpl<EducationalComponentType> {
    public EducationalComponentTypeService(
            Dao<EducationalComponentType, Integer> dao) {
        super(dao);
    }
}
