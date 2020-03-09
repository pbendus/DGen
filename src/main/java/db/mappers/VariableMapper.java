package db.mappers;

import org.springframework.stereotype.Service;
import ui.models.Variable;

@Service
public class VariableMapper
        extends Mapper<db.entities.Variable, ui.models.Variable> {
    @Override
    public Variable map(db.entities.Variable value) {
        final ui.models.Variable variable = new ui.models.Variable();
        variable.setId(value.getId());
        variable.setVariable(value.getVariable());
        variable.setDescription(value.getDescription());
        return variable;
    }

    @Override
    public db.entities.Variable reverseMap(Variable value) {
        final db.entities.Variable variable = new db.entities.Variable();
        variable.setId(value.getId());
        variable.setVariable(value.getVariable());
        variable.setDescription(value.getDescription());
        return variable;
    }
}
