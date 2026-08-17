package com.kevin.kevinaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebScrapingToolTest {

    @Test
    void scrapeWebPage() {
         String url = "https://www.baidu.com";
         WebScrapingTool webScrapingTool = new WebScrapingTool();
         String result = webScrapingTool.scrapeWebPage(url);
         Assertions.assertNotNull(result);
    }
}