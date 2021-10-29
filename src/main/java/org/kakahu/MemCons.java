package org.kakahu;

import org.kakahu.bean.RoomInfo;
import org.kakahu.bean.UserBean;

import java.util.concurrent.ConcurrentHashMap;

public class MemCons {


    // 在线用户表
    public static ConcurrentHashMap<String, UserBean> userBeans = new ConcurrentHashMap<>();

    // 在线房间表
    public static ConcurrentHashMap<String, RoomInfo> rooms = new ConcurrentHashMap<>();

}
