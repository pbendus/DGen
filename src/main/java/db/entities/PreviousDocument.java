package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "previous_document")
public class PreviousDocument {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true)
    private String name;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "name_en")
    private String nameEN;

    public PreviousDocument() {
    }

    public PreviousDocument(int id, String name, String nameEN) {
        this.id = id;
        this.name = name;
        this.nameEN = nameEN;
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

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }
}
