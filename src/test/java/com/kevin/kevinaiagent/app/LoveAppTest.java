package com.kevin.kevinaiagent.app;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
@SpringBootTest
class LoveAppTest {
    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        //第一轮
        String message = "你好，我是一个单身男生，我喜欢一个女生，但是我不知道怎么和她交流，我该怎么做？";
        String response = loveApp.doChat(message, chatId);
        System.out.println(response);
        //第二轮
        message = "我喜欢她(小红)，但是我不知道怎么和她交流，我该怎么做？";
        response = loveApp.doChat(message, chatId);
        System.out.println(response);
        //第三轮
        message= "我的另一半叫什么？刚跟你说过，帮我回忆一下";
        response = loveApp.doChat(message, chatId);
        System.out.println(response);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是程序员鱼皮，我想让另一半（编程导航）更爱我，但我不知道该怎么做";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }
}