package Proiect_PS.controller;

import Proiect_PS.dto.NotificationData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Notifications;
import Proiect_PS.service.ServiceNotifications;
import Proiect_PS.service.ServiceNotificationsInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class ControllerNotifications {
    private ServiceNotificationsInterface service;
    public ControllerNotifications(ServiceNotifications service){
        this.service = service;
    }
    @PostMapping("/insert")
    public Notifications insertRevie(@RequestBody NotificationData notificationData){
        return this.service.insertNotification(notificationData);
    }
    @PostMapping("/getNotifications")
    public List<Notifications> getNotifications(@RequestBody UserUsernameData userUsernameData){
        return this.service.findAllByUser(userUsernameData);
    }
}
