package com.kai.checkcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.checkcode.config.security.component.JwtTokenUtil;
import com.kai.checkcode.mapper.AdminMapper;
import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.service.IAdminService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import com.kai.checkcode.util.RespPageBean;
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
 * 服务实现类
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
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 更新登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 得到token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);

        return RespBean.success(map);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
    }

    @Override
    public RespPageBean getListAdmin(Integer currentPage, Integer size, Admin admin) {
        Page<Admin> adminPage = new Page<>(currentPage, size);
        IPage<Admin> iPage = adminMapper.getListAdmin(adminPage, admin);
        return new RespPageBean(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public RespBean updateAdmin(Admin admin) {
        String password = admin.getPassword();
        admin.setPassword(passwordEncoder.encode(password));
        if (adminMapper.updateById(admin) == 1) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.FAIL);
    }

    @Override
    public RespBean insertAdmin(Admin admin) {
        String password = admin.getPassword();
        admin.setPassword(passwordEncoder.encode(password));
        if (adminMapper.insert(admin) == 1) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.FAIL);
    }
}
