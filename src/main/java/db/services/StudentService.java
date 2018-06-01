package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Group;
import db.entities.Student;
import java.sql.SQLException;
import java.util.HashMap;

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

  public Student getByFullName(String familyName, String givenName) throws SQLException {
    HashMap<String, Object> values = new HashMap<>();
    values.put("family_name", familyName);
    values.put("given_name", givenName);
    return getDao().queryForFieldValues(values).get(0);
  }
}
