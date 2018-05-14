package db.services;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.configuration.DataSourceConfig;
import db.entities.AccessRequirements;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, TestingDataSourceConfig.class})
public class AccessRequirementsServiceTest {

  @Autowired
  private AccessRequirementsService accessRequirementsService;

  @Autowired
  private ConnectionSource connectionSource;

  private AccessRequirements accessRequirements;

  @Before
  public void setUp() throws Exception {
    TableUtils.createTable(connectionSource, AccessRequirements.class);
    accessRequirements = new AccessRequirements();
    accessRequirements.setName(
        "Ступінь бакалавра (ОКР - спеціаліста). За результатами вступних випробувань");
  }

  @After
  public void tearDown() throws Exception {
    TableUtils.dropTable(connectionSource, AccessRequirements.class, true);
  }

  @Test
  public void testGetById() {

  }

  @Test
  public void testGetAll() {
  }

  @Test
  public void testCreate() throws SQLException {
    assertEquals(1, accessRequirementsService.create(accessRequirements));
  }

  @Test
  public void testUpdate() {
  }

  @Test
  public void testDelete() {
  }

  @Test
  public void testGetDao() throws SQLException {
    assertEquals(DaoManager.createDao(connectionSource, AccessRequirements.class),
        accessRequirementsService.getDao());
  }
}