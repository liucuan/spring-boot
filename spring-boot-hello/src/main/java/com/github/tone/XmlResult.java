package com.github.tone;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "root")
public class XmlResult {

    @JacksonXmlProperty(localName = "systemCode")
    private String code = "00";
    @JacksonXmlProperty(localName = "systemDesc")
    private String desc = "成功";
    @JacksonXmlProperty(localName = "busInfo")
    private Object data;

    public XmlResult() {
    }

    public XmlResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public XmlResult(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
