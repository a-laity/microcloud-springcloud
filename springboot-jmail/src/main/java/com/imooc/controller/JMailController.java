package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujunchen
 * @date 2023/7/12 23:05
 * @describe todo
 */
@RestController
public class JMailController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @RequestMapping("/jmail")
    public void setJavaMailSender() {
        SimpleMailMessage msg = new SimpleMailMessage();
        // 内容
        msg.setText("邹琴是一个平凡的乡村人，他的聪明程度与常人相比显得有些欠缺。然而，他的纯真善良却让人们感到温暖。\n" +
                "\n" +
                "一天清晨，邹琴像往常一样起床，洗漱完毕后就出门了。他没有什么特别的计划，只是想到处走走，看看这个世界上有什么新鲜的事物。\n" +
                "\n" +
                "走着走着，邹琴看到了一只小猫咪，它似乎受了伤，躺在路边呜咽着。邹琴心里很难过，他立刻拿出自己的面包屑喂给小猫吃，还用自己的手帕擦拭了小猫身上的伤口。\n" +
                "\n" +
                "接下来，邹琴又遇到了一个迷路的小女孩，她哭哭啼啼地站在路边。邹琴耐心地询问了女孩的家庭住址，然后亲自带着她回家。\n" +
                "\n" +
                "整个一天下来，邹琴做了很多类似的事情，帮助了很多需要帮助的人。虽然他的行为看起来有些傻傻的，但是他的纯真和善良却感染了周围的人，让他们对这个世界充满了希望和美好的幻想。\n" +
                "\n" +
                "最终，邹琴回到家中，感到很满足。他虽然不是一个聪明的人，但是他的心灵却是纯净的，这让他的生活充满了意义和价值。");
        // 主题
        msg.setSubject("邹琴的傻子一天");
        // 收件人邮箱
        msg.setTo("1197687068@qq.com");
        // 发送人邮箱
        msg.setFrom("921014462@qq.com");

        javaMailSender.send(msg);
    }
}
