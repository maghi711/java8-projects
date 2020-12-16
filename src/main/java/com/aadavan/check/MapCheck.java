package com.aadavan.check;

import java.util.HashMap;
import java.util.Map;

public class MapCheck {
    private Map<String, String> model = constructModel();

    private Map<String, String> constructModel() {
        Map<String, String> temp = new HashMap<>();
        temp.put("enabled", "false");
        temp.put("url", "");
        return temp;
    }

    public Map<String, String> getModel() {
        return model;
    }

    public void setModel(Map<String, String> model) {
        this.model = model;
    }

    public static void main(String[] args) {
        MapCheck check = new MapCheck();
        System.out.println("check = " + check.getModel());
    }
}
