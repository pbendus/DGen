package db.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.EducationalComponentType;

@Service
public class EducationalComponentTypeMapper
        extends Mapper<db.entities.EducationalComponentType, EducationalComponentType> {

    @Autowired
    public EducationalComponentTypeMapper() {
    }

    @Override
    public EducationalComponentType map(db.entities.EducationalComponentType value) {
        final EducationalComponentType educationalComponentType = new EducationalComponentType();
        educationalComponentType.setId(value.getId());
        educationalComponentType.setName(value.getName());
        return educationalComponentType;
    }

    @Override
    public db.entities.EducationalComponentType reverseMap(EducationalComponentType value) {
        final db.entities.EducationalComponentType educationalComponentType =
                new db.entities.EducationalComponentType();
        educationalComponentType.setId(value.getId());
        educationalComponentType.setName(value.getName());
        return educationalComponentType;
    }
}
