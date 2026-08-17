package com.kevin.kevinaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.kevin.kevinaiagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * 读写文件工具类
 */
public class FileOperationTool {

    private final String FILE_DIR = FileConstant.FILE_SAVE_PATH + "/file";

    @Tool(description = "Read content from a file")
    public String readFile(@ToolParam(description = "Name of a file to read") String filenName){
        String filePath = FILE_DIR + "/" + filenName;
        try {
            return FileUtil.readUtf8String(filePath);
        }catch (Exception e){
            return "Error reading file: "+e.getMessage();
        }
    }

    @Tool(description = "Write content to a file")
    public String writeFile(@ToolParam(description = "Name of a file to write") String filenName,
                            @ToolParam(description = "Content to write to the file") String content){
        String  filePath = FILE_DIR + "/" + filenName;
        //创建目录
        try {
            FileUtil.mkdir(filePath);
            FileUtil.writeUtf8String(content, filePath);
            return "File written successfully to "+filePath;
        } catch (Exception e) {
            return "Error writing to file: "+e.getMessage();
        }
    }
}
