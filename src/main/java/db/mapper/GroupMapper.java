package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.Group;

@Service
public class GroupMapper
    extends Mapper<db.entities.Group, Group> {

  @Autowired
  public GroupMapper() {
  }

  @Override public Group map(db.entities.Group value) {
    final Group group = new Group();
    group.setId(value.getId());
    group.setName(value.getName());
    return group;
  }

  @Override public db.entities.Group reverseMap(Group value) {
    final db.entities.Group group = new db.entities.Group();
    group.setId(value.getId());
    group.setName(value.getName());
    return group;
  }
}
