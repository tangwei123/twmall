package com.xiao5.twmall.member;

import com.xiao5.twmall.member.entity.MemberEntity;
import com.xiao5.twmall.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TwmallMemberApplicationTests {


    @Autowired
    public MemberService memberService;

    @Test
    void contextLoads() {

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername("tangwei");

        memberService.save(memberEntity);
    }

}
