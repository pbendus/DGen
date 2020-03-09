package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "classification_system")
public class ClassificationSystem {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
    private String name;

    @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
    private String criteria;

    public ClassificationSystem() {
    }

    public ClassificationSystem(int id, String name, String criteria) {
        this.id = id;
        this.name = name;
        this.criteria = criteria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
