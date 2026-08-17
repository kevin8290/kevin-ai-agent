package com.kevin.kevinaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerminalOperationToolTest {

    @Test
    void excuteTerminalCommand() {
        TerminalOperationTool terminalOperationTool = new TerminalOperationTool();
        String realCommand = "dir D:\\sun";
        String[] cmd = {"cmd.exe", "/c", realCommand};
        String result = terminalOperationTool.excuteTerminalCommand(cmd);
        Assertions.assertNotNull(result);
    }
}