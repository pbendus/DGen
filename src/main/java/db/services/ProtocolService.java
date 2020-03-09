package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Protocol;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ProtocolService
        extends BaseServiceImpl<Protocol> {
    public ProtocolService(
            Dao<Protocol, Integer> dao) {
        super(dao);
    }

    public Protocol getByName(String name) throws SQLException {
        return getDao().queryForEq("name_en", name).get(0);
    }
}
