package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.OfficialDurationOfProgramme;
import db.entities.PreviousDocument;

public class PreviousDocumentService
    extends BaseServiceImpl<PreviousDocument> {
  public PreviousDocumentService(
      Dao<PreviousDocument, Integer> dao) {
    super(dao);
  }
}
