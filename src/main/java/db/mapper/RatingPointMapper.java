package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.RatingPoint;

@Service
public class RatingPointMapper
    extends Mapper<db.entities.RatingPoint, RatingPoint> {

  @Autowired
  public RatingPointMapper() {
  }

  @Override public RatingPoint map(db.entities.RatingPoint value) {
    final RatingPoint ratingPoint = new RatingPoint();
    ratingPoint.setId(value.getId());
    ratingPoint.setName(value.getName());
    ratingPoint.setMinNationalScore(value.getMinNationalScore());
    ratingPoint.setMaxNationalScore(value.getMaxNationalScore());
    return ratingPoint;
  }

  @Override public db.entities.RatingPoint reverseMap(RatingPoint value) {
    final db.entities.RatingPoint ratingPoint = new db.entities.RatingPoint();
    ratingPoint.setId(value.getId());
    ratingPoint.setName(value.getName());
    ratingPoint.setMinNationalScore(value.getMinNationalScore());
    ratingPoint.setMaxNationalScore(value.getMaxNationalScore());
    return ratingPoint;
  }
}
