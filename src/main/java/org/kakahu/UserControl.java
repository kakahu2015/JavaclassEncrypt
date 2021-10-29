package org.kakahu;


import org.kakahu.bean.RoomInfo;
import org.kakahu.bean.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserControl {

    @RequestMapping("/")
    public String index() {
        return "welcome to my webRTC demo-new";
    }


    @RequestMapping("/roomList")
    public List<RoomInfo> roomList() {
        ConcurrentHashMap<String, RoomInfo> rooms = MemCons.rooms;
        Collection<RoomInfo> values = rooms.values();
        ArrayList<RoomInfo> objects = new ArrayList<>();
        values.forEach(roomInfo -> {
            if (roomInfo.getMaxSize() > 2) {
                objects.add(roomInfo);
            }
        });
        return objects;
    }

    @RequestMapping("/userList")
    public List<UserBean> userList() {
        ConcurrentHashMap<String, UserBean> userBeans = MemCons.userBeans;
        Collection<UserBean> values = userBeans.values();
        return new ArrayList<>(values);
    }

}
