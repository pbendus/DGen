package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.PreviousDocument;
import org.springframework.stereotype.Service;

@Service
public class PreviousDocumentService
        extends BaseServiceImpl<PreviousDocument> {
    public PreviousDocumentService(
            Dao<PreviousDocument, Integer> dao) {
        super(dao);
    }
}
