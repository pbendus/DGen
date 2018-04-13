package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ProfessionalStatus;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalStatusService
    extends BaseServiceImpl<ProfessionalStatus> {
  public ProfessionalStatusService(
      Dao<ProfessionalStatus, Integer> dao) {
    super(dao);
  }
}
