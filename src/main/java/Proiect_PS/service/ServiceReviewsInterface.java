package Proiect_PS.service;

import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.ReviewData;
import Proiect_PS.model.Reviews;

import java.util.List;

public interface ServiceReviewsInterface {

    Reviews insertReview(ReviewData reviewData);
    Reviews deleteReview(ReviewData reviewData);
    List<Reviews> getReviewsByProperty(PropertyTitleData propertyTitleData);
}
