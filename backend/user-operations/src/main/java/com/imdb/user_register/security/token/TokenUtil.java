package com.imdb.user_register.security.token;

import org.json.JSONObject;

import java.util.Base64;

public class TokenUtil {

    String accessTokenName = "access_token";

    private final Base64.Decoder decoder;

    public TokenUtil(){
        decoder = Base64.getUrlDecoder();
    }

    public String getHeader (String token){
        return new String(decoder.decode(token.split("\\.")[0]));
    }

    public String getPayload (String token){
        return new String(decoder.decode(token.split("\\.")[1]));
    }

    public String getSignature (String token){
        return new String(decoder.decode(token.split("\\.")[2]));
    }

    public String getAccessToken (String response){
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.has(accessTokenName)) {
            return jsonObject.get(accessTokenName).toString();
        }
        return null;
    }

    public String getUserKeycloakId (String jwtToken){
        if(jwtToken != null) {
            JSONObject jsonObject = new JSONObject(getPayload(jwtToken));
            return jsonObject.getString("sub");
        }
        return null;
    }
}
