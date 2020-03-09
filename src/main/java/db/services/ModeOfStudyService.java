package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ModeOfStudy;
import org.springframework.stereotype.Service;

@Service
public class ModeOfStudyService extends BaseServiceImpl<ModeOfStudy> {
    public ModeOfStudyService(
            Dao<ModeOfStudy, Integer> dao) {
        super(dao);
    }
}
