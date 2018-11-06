package com.example.demo.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.JsonResult;
import com.example.demo.security.model.Authority;
import com.example.demo.security.model.User;
import com.example.demo.security.JwtAuthenticationRequest;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.security.JwtUser;
import com.example.demo.security.repository.AuthorityRepository;
import com.example.demo.security.repository.UserRepository;
import com.example.demo.security.service.JwtAuthenticationResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @ApiOperation(value = "登录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "int", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "int", required = true, paramType = "form"),
    })
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public JSONObject createAuthenticationToken(@RequestParam String username, @RequestParam String password, Device device) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", userDetails);

        // Return the token
        return JsonResult.success(result);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public JSONObject login(HttpServletRequest request) {
        String userName = "wwy121";
        String password = "123456";

        User user = new User();
        user.setUsername(userName);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setFirstname("wang");
        user.setLastname("weiye");
        user.setEmail("wwyknight@163.com");
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());

        Authority authority = authorityRepository.findOne(1L);

        user.setAuthorities(Arrays.asList(authority));

        userRepository.save(user);

        return JsonResult.success("注册成功", null);
    }
}
