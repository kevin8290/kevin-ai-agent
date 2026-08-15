package com.kevin.kevinaiagent.rag;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;

/**
 * 创建上下文查询增强器的工厂
 */
public class LoveAppContextualQueryAugmenterFactory {

    public static ContextualQueryAugmenter createLoveAppContextualQueryAugmenter() {

        PromptTemplate emptyContextPromptTemplate = new PromptTemplate("你应该输入下面的内容：抱歉，我只能回答恋爱问题，别的没办法帮到您，" +
                "请联系我们的客服！");
        return ContextualQueryAugmenter.builder()
                .allowEmptyContext(false)
                .emptyContextPromptTemplate(emptyContextPromptTemplate)
                .build();
    }
}
