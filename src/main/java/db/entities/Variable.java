package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "variable")
public class Variable {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true)
    private String variable;

    @DatabaseField(canBeNull = false, useGetSet = true)
    private String description;

    public Variable() {
    }

    public Variable(int id, String variable, String description) {
        this.id = id;
        this.variable = variable;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
