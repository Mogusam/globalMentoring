package org.home.service;

import java.util.ServiceLoader;

public class ServiceLoaderDemo {

    public static void main(String[] args) {
        final ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for (IService service : services) {
            System.out.println("Response: " + service.getInfo("Bill"));
        }
    }
}
