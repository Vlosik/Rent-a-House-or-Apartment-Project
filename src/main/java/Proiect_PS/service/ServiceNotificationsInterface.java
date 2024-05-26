package Proiect_PS.service;

import Proiect_PS.dto.NotificationData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Notifications;

import java.util.List;

public interface ServiceNotificationsInterface {
    Notifications insertNotification(NotificationData notificationData);
    List<Notifications> findAllByUser(UserUsernameData userUsernameData);

}
