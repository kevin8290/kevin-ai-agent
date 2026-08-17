package com.kevin.kevinaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebSearchToolTest {

    @Value("${search-api.api-key}")
    private String searchApiKey;


    @Test
    void search() {
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        String result = webSearchTool.search("程序员鱼皮编程导航 codefather.com");
        Assertions.assertNotNull(result);
    }
}