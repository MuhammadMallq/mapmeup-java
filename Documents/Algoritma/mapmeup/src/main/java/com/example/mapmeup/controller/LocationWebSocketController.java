package com.example.mapmeup.controller;

import com.example.mapmeup.model.Location;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationWebSocketController {

    @MessageMapping("/updateLocation")
    @SendTo("/topic/locations")
    public Location sendLocation(Location location) throws Exception {
        return location;
    }
}
