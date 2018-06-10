package db.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.NationalGrade;

@Service
public class NationalGradeMapper
        extends Mapper<db.entities.NationalGrade, NationalGrade> {

    @Autowired
    public NationalGradeMapper() {
    }

    @Override
    public NationalGrade map(db.entities.NationalGrade value) {
        final NationalGrade nationalGrade = new NationalGrade();
        nationalGrade.setId(value.getId());
        nationalGrade.setName(value.getName());
        nationalGrade.setMinNationalScore(value.getMinNationalScore());
        nationalGrade.setMaxNationalScore(value.getMaxNationalScore());
        return nationalGrade;
    }

    @Override
    public db.entities.NationalGrade reverseMap(NationalGrade value) {
        final db.entities.NationalGrade nationalGrade = new db.entities.NationalGrade();
        nationalGrade.setId(value.getId());
        nationalGrade.setName(value.getName());
        nationalGrade.setMinNationalScore(value.getMinNationalScore());
        nationalGrade.setMaxNationalScore(value.getMaxNationalScore());
        return nationalGrade;
    }
}