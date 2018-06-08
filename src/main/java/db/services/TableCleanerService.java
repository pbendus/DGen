package db.services;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TableCleanerService {

    private ConnectionSource connectionSource;

    @Autowired
    public TableCleanerService(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    @SafeVarargs
    public final void clearTables(Class... classes) throws SQLException {
        for (Class clazz :
                classes) {
            TableUtils.clearTable(connectionSource, clazz);
        }
    }

    public void clearAllExeptStaticData() throws SQLException {
        clearTables(Diploma.class, DiplomaSubject.class, EducationalComponent.class, EducationalComponentTemplate.class,
                Group.class, PreviousDocument.class, Protocol.class, Student.class);
    }
}
