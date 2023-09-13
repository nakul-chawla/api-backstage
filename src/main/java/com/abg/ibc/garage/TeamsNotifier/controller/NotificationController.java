package com.abg.ibc.garage.TeamsNotifier.controller;

import com.abg.ibc.garage.TeamsNotifier.model.NotificationRequest;
import com.abg.ibc.garage.TeamsNotifier.model.NotificationResponse;
import com.abg.ibc.garage.TeamsNotifier.util.ChannelIdProvider;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.ChatMessage;
import com.microsoft.graph.models.ItemBody;
import com.microsoft.graph.models.Team;
import com.microsoft.graph.requests.ChannelCollectionRequest;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.TeamCollectionRequest;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;

@RestController
//@RequestMapping("/notify")
public class NotificationController {

    @Value( "${teams.id}")
    private String teamsId;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenCredentialAuthProvider authProvider;

    @Autowired
    private ChannelIdProvider channelIdProvider;

    @PostMapping
    @CrossOrigin
    public ChatMessage notifyTeams(@RequestBody NotificationRequest notificationRequest) throws FileNotFoundException, ParseException {
        GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider( authProvider )
                .buildClient();

        ChatMessage chatMessage = new ChatMessage();
        ItemBody body = new ItemBody();
        body.content = "Hello World";
        chatMessage.body = body;

        String channelId = channelIdProvider.getChannelId("ascs");

        Team c = graphClient.teams(teamsId)
                .buildRequest().get();

        ChatMessage chatMessage1 = graphClient.teams(teamsId).channels(channelId).messages()
                .buildRequest()
                .post(chatMessage);



        System.out.println(c);

        return chatMessage1;

    }
}
