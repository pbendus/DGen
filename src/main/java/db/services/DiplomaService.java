package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Diploma;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DiplomaService extends BaseServiceImpl<Diploma> {
    public DiplomaService(
            Dao<Diploma, Integer> dao) {
        super(dao);
    }

    public Diploma getByStudentId(int studentId) throws SQLException {
        final List<Diploma> diplomas = getDao().queryForEq("student_id", studentId);
        if (diplomas.size() == 1) {
            LOGGER.info(
                    String.format("The diploma with student id{%d} exists in the storage", studentId));
            return diplomas.get(0);
        } else {
            LOGGER.info(String.format("The diploma with student id{%d} was not found", studentId));

            return null;
        }
    }

    public Diploma getByNumber(String number) throws SQLException {
        return getDao().queryForEq("number", number).get(0);
    }
}
