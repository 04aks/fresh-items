package io.github.aks;

import io.github.aks.transport.HttpTransport;

public class Main {
    public static void main(String[] args) {
        HttpTransport transport = new HttpTransport("https://pitpanda.rocks/api/players");
        String response = transport.get("/DanTDM", null);
        System.out.println(response);
    }
}