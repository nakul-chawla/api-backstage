package com.abg.ibc.garage.TeamsNotifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private String title;
    private String id;
    private String url;

    private String tag;

}
