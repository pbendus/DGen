package db.services;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;

@Service
public class ClearDBService {
    public void dropTables() {
        Connection c = null;
        Statement statement = null;
        String sql;
    }
}
