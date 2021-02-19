package com.kai.checkcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.checkcode.config.security.component.JwtTokenUtil;
import com.kai.checkcode.mapper.AdminMapper;
import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.service.IAdminService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {


    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null!=userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        System.out.println(token);

        return RespBean.success(map);
    }
}
