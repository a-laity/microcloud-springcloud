package com.imooc.service;

import com.imooc.bean.MemberDTO;

import java.util.Map;

public interface IMemberService {
    // 用户登录完成之后所有的数据通过Map集合进行返回，而后会包含有如下的一些数据内容：
    // 1、key = status、value = 登录状态（true、false）；
    // 2、key = mid、value = 用户名；
    // 3、key = name、value = 姓名；
    // 4、key = resource、value = 授权信息
    // 4-1、key = roles、value = 用户拥有的全部角色
    // 4-2、key = roles、value = 用户拥有的全部的权限
    public Map<String, Object> login(MemberDTO memberDTO);
}
