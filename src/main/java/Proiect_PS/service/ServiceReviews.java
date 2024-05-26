package Proiect_PS.service;

import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.ReviewData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;
import Proiect_PS.model.Reviews;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryReviews;
import Proiect_PS.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceReviews implements ServiceReviewsInterface{
    private RepositoryReviews repositoryReviews;
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;

    public ServiceReviews(RepositoryReviews repositoryReviews,RepositoryProperty repositoryProperty,RepositoryUser repositoryUser){
        this.repositoryProperty = repositoryProperty;
        this.repositoryReviews = repositoryReviews;
        this.repositoryUser = repositoryUser;
    }
    @Override
    public Reviews insertReview(ReviewData reviewData) {
        Reviews reviews = new Reviews();
        User user = this.repositoryUser.findByUsername(reviewData.getUsername());
        Property property = this.repositoryProperty.findByTitle(reviewData.getPropertyTitle());
        if(property != null && user != null){
            reviews.setUser(user);
            reviews.setMessage(reviewData.getMessage());
            reviews.setRating(reviewData.getRating());
            reviews.setProperty(property);
        }
        return this.repositoryReviews.save(reviews);
    }

    @Override
    public Reviews deleteReview(ReviewData reviewData) {
        Reviews reviews = new Reviews();
        User user = this.repositoryUser.findByUsername(reviewData.getUsername());
        Property property = this.repositoryProperty.findByTitle(reviewData.getPropertyTitle());
        if(property != null && user != null){
            reviews.setUser(user);
            reviews.setMessage(reviewData.getMessage());
            reviews.setRating(reviewData.getRating());
            reviews.setProperty(property);
        }
        this.repositoryReviews.save(reviews);
        return reviews;
    }

    @Override
    public List<Reviews> getReviewsByProperty(PropertyTitleData propertyTitleData) {
        List<Reviews> reviewsList = new ArrayList<>();
        Property property = this.repositoryProperty.findByTitle(propertyTitleData.getTitle());
        if(property != null){
            List<Reviews> reviews = this.repositoryReviews.findAll();
            for(Reviews review : reviews){
                if(review.getProperty().getTitle().equals(property.getTitle())){
                    reviewsList.add(review);
                }
            }
        }
        return reviewsList;
    }

}
