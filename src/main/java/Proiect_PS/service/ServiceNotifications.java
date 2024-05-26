package Proiect_PS.service;

import Proiect_PS.dto.NotificationData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.*;
import Proiect_PS.repository.RepositoryNotifications;
import Proiect_PS.repository.RepositoryReviews;
import Proiect_PS.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceNotifications implements ServiceNotificationsInterface{
    private RepositoryNotifications repositoryNotifications;
    private RepositoryUser repositoryUser;

    public ServiceNotifications(RepositoryNotifications repositoryNotifications,RepositoryUser repositoryUser){
        this.repositoryNotifications = repositoryNotifications;
        this.repositoryUser = repositoryUser;
    }
    @Override
    public Notifications insertNotification(NotificationData notificationData) {
        Notifications notifications = new Notifications();
        User user = this.repositoryUser.findByUsername(notificationData.getUsername());
        if(user != null){
            notifications.setUser(user);
            notifications.setMessage(notificationData.getMessage());
        }
        return this.repositoryNotifications.save(notifications);
    }

    @Override
    public List<Notifications> findAllByUser(UserUsernameData userUsernameData) {
        List<Notifications> notifications = new ArrayList<>();
        User user = this.repositoryUser.findByUsername(userUsernameData.getUsername());
        if(user != null){
            List<Notifications> notificationsList = this.repositoryNotifications.findAll();
            for(Notifications notifications1 : notificationsList){
                if(notifications1.getUser().getUsername().equals(user.getUsername())){
                    notifications.add(notifications1);
                }
            }
        }
        return notifications;
    }
}
