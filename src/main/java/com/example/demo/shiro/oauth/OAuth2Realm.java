package com.example.demo.shiro.oauth;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.shiro.entity.UserToken;
import com.example.demo.shiro.service.ShiroService;
import java.util.Set;

/**
 * 认证
 *
 * @author dwli
 * @email 68311897@qq.com
 * @date 2018-08-18 23:30
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Realm.class);
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
   /* @Override
    public boolean supports(AuthenticationToken token) {
        // 定义该Realm可以处理哪个类型的token
        return token instanceof UsernamePasswordToken;
    }*/

    /**
     * 授权(验证权限时调用)奥设瑞z神
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	RocIdUser user = (RocIdUser)principals.getPrimaryPrincipal();
        String userId = user.getId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);
       /* Set<String> permsList = new HashSet<>();
        Iterator<String> iterator = permsSet.iterator();
        while(iterator.hasNext()) {
            String perm = iterator.next();
            if(perm.contains(";")) {
                String[] perms = perm.split(";");
                permsList.addAll(Arrays.asList(perms));
            }else {
                permsList.add(perm);
            }
        }*/
        LOGGER.info("permsList {}", permsSet);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)奥city k 神
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	//从token中获取信息,此token只是shiro用于身份验证的,并非前端传过来的token.
     
    	String accessToken = (String) authenticationToken.getPrincipal();
       
        //根据accessToken，查询用户信息
        UserToken tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        RocIdUser user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user != null && user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
