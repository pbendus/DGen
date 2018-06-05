package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.PreviousDocument;

@Service
public class PreviousDocumentMapper
        extends Mapper<db.entities.PreviousDocument, PreviousDocument> {

    @Autowired
    public PreviousDocumentMapper() {
    }

    @Override
    public PreviousDocument map(db.entities.PreviousDocument value) {
        final PreviousDocument previousDocument = new PreviousDocument();
        previousDocument.setId(value.getId());
        previousDocument.setNameUk(value.getNameUk());
        return previousDocument;
    }

    @Override
    public db.entities.PreviousDocument reverseMap(PreviousDocument value) {
        final db.entities.PreviousDocument previousDocument = new db.entities.PreviousDocument();
        previousDocument.setId(value.getId());
        previousDocument.setNameUk(value.getNameUk());
        return previousDocument;
    }
}
