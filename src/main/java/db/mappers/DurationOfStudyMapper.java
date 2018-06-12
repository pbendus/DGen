package db.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.DurationOfStudy;

@Service
public class DurationOfStudyMapper
        extends Mapper<db.entities.DurationOfStudy, DurationOfStudy> {

    @Autowired
    public DurationOfStudyMapper() {
    }

    @Override
    public DurationOfStudy map(db.entities.DurationOfStudy value) {
        final DurationOfStudy durationOfStudy = new DurationOfStudy();
        durationOfStudy.setId(value.getId());
        durationOfStudy.setValue(value.getValue());
        return durationOfStudy;
    }

    @Override
    public db.entities.DurationOfStudy reverseMap(DurationOfStudy value) {
        final db.entities.DurationOfStudy durationOfStudy = new db.entities.DurationOfStudy();
        durationOfStudy.setId(value.getId());
        durationOfStudy.setValue(value.getValue());
        return durationOfStudy;
    }
}
