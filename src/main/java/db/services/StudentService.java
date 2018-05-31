package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Group;
import db.entities.Student;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class StudentService
    extends BaseServiceImpl<Student> {
  public StudentService(
      Dao<Student, Integer> dao) {
    super(dao);
  }

  public Group getGroupByStudentId(int studentId) throws SQLException {
    return getDao().queryForEq("id", studentId).get(0).getGroup();
  }
}
