package com.example.demo.login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.shiro.oauth.OAuth2Token;
import com.example.demo.shiro.service.UserTokenService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserTokenService userTokenService;
	@ResponseBody
	@RequestMapping("/login")
	public String login(RocIdUser user) {
		 String token = null;
		 // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
      //  UsernamePasswordToken tokens = new UsernamePasswordToken(user.getId(), user.getPassWord());
        token  = userTokenService.createToken(user.getId());
        OAuth2Token tokens = new OAuth2Token(token);
        // 执行认证登陆
        // 执行认证登陆
        try {
            subject.login(tokens);
          
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return token;
        } else {
           
            return "登录失败";
        }

	}
}
