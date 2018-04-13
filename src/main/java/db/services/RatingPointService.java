package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.RatingPoint;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RatingPointService
    extends BaseServiceImpl<RatingPoint> {
  public RatingPointService(
      Dao<RatingPoint, Integer> dao) {
    super(dao);
  }

  public RatingPoint getRatingPointByScore(int nationalScore) throws SQLException {
    final List<RatingPoint> ratingPoints = getAll();

    for (RatingPoint ratingPoint :
        ratingPoints) {
      if (ratingPoint.getMinNationalScore() <= nationalScore
          && ratingPoint.getMaxNationalScore() >= nationalScore) {
        return ratingPoint;
      }
    }

    return null;
  }
}
