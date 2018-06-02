package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.FieldOfStudy;
import org.springframework.stereotype.Service;

@Service
public class FieldOfStudyService extends BaseServiceImpl<FieldOfStudy> {
    public FieldOfStudyService(
            Dao<FieldOfStudy, Integer> dao) {
        super(dao);
    }
}
