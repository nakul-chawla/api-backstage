package com.abg.ibc.garage.TeamsNotifier.util;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChannelIdProvider {

    @Value("${generic.channel.id}")
    private String genericChannelId;

    @Value("${tag.map.file.path}")
    private String tagMapFilePath;

    private JSONObject jsonObject;
    public String getChannelId(String tag) throws FileNotFoundException, ParseException {
        if (jsonObject == null) {
            getJsonMap();
        }
        return jsonObject.containsKey(tag) ? jsonObject.getAsString(tag) : genericChannelId;
    }

    public void getJsonMap() throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser();
        jsonObject =  (JSONObject) parser.parse(new FileReader(tagMapFilePath));
    }
}
