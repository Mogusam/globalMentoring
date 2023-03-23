package org.home.service;

import com.google.auto.service.AutoService;

@AutoService(IService.class)
public class ServiceImpl implements IService {
    @Override
    public String getInfo(String name) {
        return "Hello " + name;
    }
}
