package Proiect_PS.controller;

import Proiect_PS.dto.FavoriteData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;
import Proiect_PS.service.ServiceFavorite;
import Proiect_PS.service.ServiceFavoriteInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/favorite")
public class ControllerFavorite {
    private ServiceFavoriteInterface service;
    public ControllerFavorite(ServiceFavorite service){
        this.service = service;
    }
    @PostMapping("/insert")
    public Favorite insertFavorite(@RequestBody FavoriteData favoriteData){
        return this.service.insertFavorite(favoriteData);
    }
    @DeleteMapping("/delete")
    public Favorite deleteFavorite(@RequestBody FavoriteData favoriteData){
        return this.service.deleteFavorite(favoriteData);
    }
    @PostMapping("/getFavorite")
    public List<Property> getFavoriteByUser(@RequestBody UserUsernameData userUsernameData){
        return this.service.getFavoriteByUser(userUsernameData);
    }
}
