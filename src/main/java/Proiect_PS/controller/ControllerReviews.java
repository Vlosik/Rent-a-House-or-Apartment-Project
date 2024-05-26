package Proiect_PS.controller;

import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.ReviewData;
import Proiect_PS.model.Reviews;
import Proiect_PS.service.ServiceReviews;
import Proiect_PS.service.ServiceReviewsInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ControllerReviews {
    private ServiceReviewsInterface serviceReview;
    public ControllerReviews(ServiceReviews service){
        this.serviceReview = service;
    }
    @PostMapping("/insert")
    public Reviews insertReview(@RequestBody ReviewData reviewData){
        return this.serviceReview.insertReview(reviewData);
    }
    @DeleteMapping("/delete")
    public Reviews deleteReview(@RequestBody ReviewData reviewData){
        return this.serviceReview.deleteReview(reviewData);
    }
    @PostMapping("/getReviews")
    public List<Reviews> getReviewsByProperty(@RequestBody PropertyTitleData propertyTitleData){
        return this.serviceReview.getReviewsByProperty(propertyTitleData);
    }
}
