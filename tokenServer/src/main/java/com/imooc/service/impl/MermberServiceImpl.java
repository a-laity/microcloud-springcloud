package com.imooc.service.impl;

import com.imooc.bean.Member;
import com.imooc.bean.MemberDTO;
import com.imooc.mapper.IActionDAO;
import com.imooc.mapper.IMemberDAO;
import com.imooc.mapper.IRoleDAO;
import com.imooc.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MermberServiceImpl implements IMemberService {
    @Autowired
    private IMemberDAO memberDAO;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private IActionDAO actionDAO;

    @Override
    public Map<String, Object> login(MemberDTO memberDTO) {
        Map<String, Object> result = new HashMap<>();
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(memberDTO.getMid())) {
            criteria.andEqualTo("mid", memberDTO.getMid());
        }
        Member member = memberDAO.selectOneByExample(example); // 查询用户数据
        // 用户信息为空、密码不相等或者用户状态被锁定
        if (member == null || !member.getPassword().equals(memberDTO.getPassword()) || member.getLocked().equals(1)) {
            result.put("status", false); // 登录失败
        } else {    // 一切正常，获取其他信息
            result.put("status", true); // 登录成功
            result.put("mid", member.getMid());
            result.put("name", member.getName());
            Map<String, Object> resource = new HashMap<>();
            resource.put("roles", this.roleDAO.findAllByMember(memberDTO.getMid()));
            resource.put("actions", this.actionDAO.findAllByMember(memberDTO.getMid()));
            result.put("resource", resource);
        }
        return result;
    }
}
