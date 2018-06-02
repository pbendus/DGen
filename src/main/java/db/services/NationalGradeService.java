package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.NationalGrade;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NationalGradeService extends BaseServiceImpl<NationalGrade> {
    public NationalGradeService(
            Dao<NationalGrade, Integer> dao) {
        super(dao);
    }

    public NationalGrade getNationalGradeByScore(int nationalScore) throws SQLException {
        final List<NationalGrade> ratingPoints = getAll();

        for (NationalGrade nationalGrade :
                ratingPoints) {
            if (nationalGrade.getMinNationalScore() <= nationalScore
                    && nationalGrade.getMaxNationalScore() >= nationalScore) {
                return nationalGrade;
            }
        }

        return null;
    }
}
