package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Variable;
import org.springframework.stereotype.Service;

@Service
public class VariableService
        extends BaseServiceImpl<Variable> {
    public VariableService(Dao<Variable, Integer> dao) {
        super(dao);
    }
}
