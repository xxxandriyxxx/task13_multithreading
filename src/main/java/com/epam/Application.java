package com.epam;

import com.epam.ping_pong.PingPong;

public class Application {

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        pingPong.show(10000);
    }
    
}
