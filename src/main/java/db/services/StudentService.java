package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Student;

public class StudentService
    extends BaseServiceImpl<Student> {
  public StudentService(
      Dao<Student, Integer> dao) {
    super(dao);
  }
}
