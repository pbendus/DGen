package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Protocol;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProtocolService
        extends BaseServiceImpl<Protocol> {
    public ProtocolService(
            Dao<Protocol, Integer> dao) {
        super(dao);
    }

    public Protocol getByName(String nameEN, String nameUK) throws SQLException {
        final Map<String, Object> values = new HashMap<>();
        values.put("name_en", nameEN);
        values.put("name_uk", nameUK);
        return getDao().queryForFieldValues(values).get(0);
    }
}
