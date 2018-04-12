package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.PreviousDocument;
import db.entities.ProfessionalStatus;

public class ProfessionalStatusService
    extends BaseServiceImpl<ProfessionalStatus> {
  public ProfessionalStatusService(
      Dao<ProfessionalStatus, Integer> dao) {
    super(dao);
  }
}
