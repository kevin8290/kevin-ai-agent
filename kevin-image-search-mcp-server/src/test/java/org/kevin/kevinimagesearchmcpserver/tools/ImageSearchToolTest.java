package org.kevin.kevinimagesearchmcpserver.tools;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageSearchToolTest
{
    @Resource
    private ImageSearchTool imageSearchTool;
    @Test
    void imageSearchToolTest(){
        String searched = imageSearchTool.searchImage("公园");
        Assertions.assertNotNull(searched);
    }
}
