package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Diploma;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DiplomaService extends BaseServiceImpl<Diploma> {
  public DiplomaService(
      Dao<Diploma, Integer> dao) {
    super(dao);
  }

  public Diploma getByStudentId(int studentId) throws SQLException {
    final List<Diploma> diplomas = getDao().queryForEq("student_id", studentId);

    return diplomas.size() == 1 ? diplomas.get(0) : null;
  }
}
