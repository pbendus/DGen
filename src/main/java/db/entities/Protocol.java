package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "protocol")
public class Protocol {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "name_uk")
    private String nameUK;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "name_en")
    private String nameEN;

    public Protocol() {
    }

    public Protocol(int id, String nameUK, String nameEN) {
        this.id = id;
        this.nameUK = nameUK;
        this.nameEN = nameEN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUK() {
        return nameUK;
    }

    public void setNameUK(String nameUK) {
        this.nameUK = nameUK;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }
}
