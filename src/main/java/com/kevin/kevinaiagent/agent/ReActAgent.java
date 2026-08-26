package com.kevin.kevinaiagent.agent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * ReAct (Reasoning and Acting) 模式的代理抽象类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public abstract class ReActAgent extends BaseAgent{

    /**
     * 处理当前状态并决定下一步决定
     * @return 是否需要执行行动，true 需要执行  false 不需要执行
     */
    public abstract boolean think();

    /**
     * 执行决定的行动
     * @return 行动执行结果
     */
    public abstract String act();

    @Override
    public String step(){
        try {
            //先思考
            boolean shouldAct = think();
            if(!shouldAct){
                return "思考完成 - 无需执行";
            }
            //再执行
            return act();
        } catch (Exception e) {
            e.printStackTrace();
            return "步骤执行失败：" + e.getMessage();
        }
    }
}
