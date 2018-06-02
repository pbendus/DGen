package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.AccessRequirements;
import org.springframework.stereotype.Service;

@Service
public class AccessRequirementsService extends BaseServiceImpl<AccessRequirements> {
    public AccessRequirementsService(
            Dao<AccessRequirements, Integer> dao) {
        super(dao);
    }
}
