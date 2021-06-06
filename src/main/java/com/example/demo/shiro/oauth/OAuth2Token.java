package com.example.demo.shiro.oauth;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author dwli
 * @email 68311897@qq.com
 * @date 2018-08-18 23:30
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
