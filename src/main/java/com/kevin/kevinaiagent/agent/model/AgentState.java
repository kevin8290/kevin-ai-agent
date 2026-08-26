package com.kevin.kevinaiagent.agent.model;

/**
 * 代理执行状态的枚举类
 */
public enum AgentState {

    /**
     * 空闲状态
     */
    IDE,

    /**
     * 运行中状态
     */
    RUNNING,


    /**
     * 已完成状态
     */
    FINISHED,

    /**
     * 错误状态
     */
    ERROR
}
