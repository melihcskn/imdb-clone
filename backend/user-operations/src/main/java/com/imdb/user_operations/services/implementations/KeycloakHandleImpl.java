package com.imdb.user_operations.services.implementations;

import com.imdb.user_operations.services.interfaces.KeycloakHandleService;
import com.imdb.user_operations.services.interfaces.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KeycloakHandleImpl implements KeycloakHandleService {

    UserService userService;
    private final String userId = "userId";
    private final String operationType = "operationType";
    private final String authDetails = "authDetails";
    private final String representation = "representation";


    KeycloakHandleImpl(UserService userService) {
        this.userService = userService;
    }

    public void HandleKeycloakMessage(String keycloakMessage) {
        try {
            JSONObject jsonMessage = new JSONObject(keycloakMessage);
            if (jsonMessage.has(authDetails)) {

                JSONObject jsonAuthDetails = jsonMessage.getJSONObject(authDetails);

                if (jsonAuthDetails.has(userId) && jsonMessage.has(operationType)) {
                    switch (jsonMessage.getString(operationType)) {
                        case "DELETE":
                            userService.deleteKeycloakUser(jsonAuthDetails.getString(userId));
                            break;
                        case "CREATE":
                            //TODO Access user password from keycloak
                            /*
                            if (jsonMessage.has(representation)) {
                                JSONObject jsonUserInfo = new JSONObject(jsonMessage.getString(representation));
                                jsonUserInfo.put("keycloakId", jsonAuthDetails.getString(userId));
                                System.out.println(jsonUserInfo.toMap().get("keycloakId"));
                                userService.createKeycloakUser(jsonUserInfo.toMap());
                            }*/
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
