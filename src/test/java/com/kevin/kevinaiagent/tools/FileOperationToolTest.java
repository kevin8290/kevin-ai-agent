package com.kevin.kevinaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileOperationToolTest {

    @Test
    void readFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String content = fileOperationTool.readFile("编程导航.txt");
        Assertions.assertNotNull(content);
    }

    @Test
    void writeFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String content = "测试写入文件";
        String fileName = "编程导航.txt";
        String result = fileOperationTool.writeFile(fileName, content);
        Assertions.assertNotNull(content);
    }
}