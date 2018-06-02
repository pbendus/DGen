package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationSystemMapper
        extends Mapper<db.entities.ClassificationSystem, ui.models.ClassificationSystem> {

    @Autowired
    public ClassificationSystemMapper() {
    }

    @Override
    public ui.models.ClassificationSystem map(db.entities.ClassificationSystem value) {
        final ui.models.ClassificationSystem classificationSystem =
                new ui.models.ClassificationSystem();
        classificationSystem.setId(value.getId());
        classificationSystem.setName(value.getName());
        classificationSystem.setCriteria(value.getCriteria());
        return classificationSystem;
    }

    @Override
    public db.entities.ClassificationSystem reverseMap(ui.models.ClassificationSystem value) {
        final db.entities.ClassificationSystem classificationSystem =
                new db.entities.ClassificationSystem();
        classificationSystem.setId(value.getId());
        classificationSystem.setName(value.getName());
        classificationSystem.setCriteria(value.getCriteria());
        return classificationSystem;
    }
}
