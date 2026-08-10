package com.kevin.kevinaiagent.demo.invoke;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HttpAiInvoke {

    public static void main(String[] args) {
        // 替换为你的 workspaceId
        String workspaceId = "ws-bofs8rzy60auwvtm";
        String apiKey = TestApiKey.API_KEY;
        if (StrUtil.isBlank(apiKey)) {
            throw new RuntimeException("环境变量DASHSCOPE_API_KEY未配置");
        }

        String url = StrUtil.format("https://{}.cn-beijing.maas.aliyuncs.com/api/v1/services/aigc/multimodal-generation/generation", workspaceId);

        // 构建请求json，完全对齐curl的data结构
        JSONObject body = JSONUtil.createObj();
        body.set("model", "qwen3.8-max");

        JSONObject inputObj = JSONUtil.createObj();
        // messages数组
        inputObj.set("messages", JSONUtil.createArray()
                .put(JSONUtil.createObj()
                        .set("role", "system")
                        .set("content", JSONUtil.createArray()
                                .put(JSONUtil.createObj().set("text", "You are a helpful assistant."))
                        ))
                .put(JSONUtil.createObj()
                        .set("role", "user")
                        .set("content", JSONUtil.createArray()
                                .put(JSONUtil.createObj().set("text", "你是谁？"))
                        ))
        );
        body.set("input", inputObj);

        JSONObject parameters = JSONUtil.createObj();
        parameters.set("result_format", "message");
        body.set("parameters", parameters);


        try (HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .execute()) {

            int status = response.getStatus();
            String respBody = response.body();
            System.out.println("http status:" + status);
            System.out.println("response:" + respBody);
        }
    }
}