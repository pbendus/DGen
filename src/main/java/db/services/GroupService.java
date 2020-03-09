package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends BaseServiceImpl<Group> {
    public GroupService(
            Dao<Group, Integer> dao) {
        super(dao);
    }
}
