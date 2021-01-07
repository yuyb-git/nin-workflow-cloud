package cn.netinnet.ninzuul.authentication;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JSON Web Token
 */
public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
