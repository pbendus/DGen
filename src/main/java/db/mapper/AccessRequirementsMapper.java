package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessRequirementsMapper
        extends Mapper<db.entities.AccessRequirements, ui.models.AccessRequirements> {

    @Autowired
    public AccessRequirementsMapper() {
    }

    @Override
    public ui.models.AccessRequirements map(db.entities.AccessRequirements value) {
        final ui.models.AccessRequirements accessRequirements = new ui.models.AccessRequirements();
        accessRequirements.setId(value.getId());
        accessRequirements.setName(value.getName());
        return accessRequirements;
    }

    @Override
    public db.entities.AccessRequirements reverseMap(ui.models.AccessRequirements value) {
        final db.entities.AccessRequirements accessRequirements = new db.entities.AccessRequirements();
        accessRequirements.setId(value.getId());
        accessRequirements.setName(value.getName());
        return accessRequirements;
    }
}
