package com.alice.function.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wusm
 * @description
 * @date 2019/3/11 15:46
 */
public class RequestMessage<T> implements Serializable {



    @Valid
    @JSONField(name = "BUSI_INFO")
    @JsonProperty("BUSI_INFO")
    @NotNull(message="入参busiInfo不能为空")
    private T busiInfo;

    @NotNull
    public T getBusiInfo() {
        return busiInfo;
    }

    public void setBusiInfo(@NotNull T busiInfo) {
        this.busiInfo = busiInfo;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "busiInfo=" + busiInfo +
                '}';
    }
}
