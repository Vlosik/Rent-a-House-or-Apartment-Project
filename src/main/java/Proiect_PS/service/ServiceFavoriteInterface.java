package Proiect_PS.service;

import Proiect_PS.dto.FavoriteData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;

import java.util.List;

public interface ServiceFavoriteInterface {
    Favorite insertFavorite(FavoriteData favoriteData);
    Favorite deleteFavorite(FavoriteData favoriteData);
    List<Property> getFavoriteByUser(UserUsernameData userUsernameData);
}
