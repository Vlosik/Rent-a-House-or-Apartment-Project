package Proiect_PS.service;

import Proiect_PS.dto.FavoriteData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryFavorite;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceFavorite implements ServiceFavoriteInterface{
    private RepositoryUser repositoryUser;
    private RepositoryProperty repositoryProperty;
    private RepositoryFavorite repositoryFavorite;
    public ServiceFavorite(RepositoryFavorite repositoryFavorite,RepositoryUser repositoryUser,RepositoryProperty repositoryProperty){
        this.repositoryFavorite = repositoryFavorite;
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
    }
    @Override
    public Favorite insertFavorite(FavoriteData favoriteData) {
        Favorite favorite = new Favorite();
        User user = this.repositoryUser.findByUsername(favoriteData.getUser());
        Property property = this.repositoryProperty.findByTitle(favoriteData.getPropertyTitle());
        if(property != null && user != null){
            List<Favorite> favorites = this.repositoryFavorite.findByUser(user);
            int find = 0;
            for(Favorite favorite1 :favorites){
                if(favorite1.getProperty().getTitle().equals(favoriteData.getPropertyTitle())){
                    find = 1;
                }
            }
            if(find == 0){
                favorite.setUser(user);
                favorite.setProperty(property);
                return this.repositoryFavorite.save(favorite);
            }
        }
       return null;
    }

    @Override
    public Favorite deleteFavorite(FavoriteData favoriteData) {
        Favorite favorite = new Favorite();
        User user = this.repositoryUser.findByUsername(favoriteData.getUser());
        Property property = this.repositoryProperty.findByTitle(favoriteData.getPropertyTitle());
        if(property != null && user != null){
            List<Favorite> favorites = this.repositoryFavorite.findByUser(user);
            int find = 0;
            for(Favorite favorite1 :favorites){
                if(favorite1.getProperty().getTitle().equals(favoriteData.getPropertyTitle())){
                    find = 1;
                }
                if(find == 1){
                    this.repositoryFavorite.delete(favorite1);
                    return favorite1;

                }
            }
        }
        return null;
    }

    @Override
    public List<Property> getFavoriteByUser(UserUsernameData userUsernameData) {
        List<Property> properties = new ArrayList<>();
        User user = this.repositoryUser.findByUsername(userUsernameData.getUsername());
        if(user != null){
            List<Favorite> favorites = this.repositoryFavorite.findAll();
            for(Favorite favorite : favorites){
                System.out.println(favorite.getUser().getUsername());
                System.out.println(userUsernameData.getUsername());
                if(favorite.getUser().getUsername().equals(user.getUsername())){
                    properties.add(favorite.getProperty());
                }
            }
        }
        return properties;
    }
}
