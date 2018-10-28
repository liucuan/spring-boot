package com.github.tone;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class XmlError {

    @JacksonXmlProperty(localName = "field")
    private String fieldName;
    @JacksonXmlProperty(localName = "errorCode")
    private String errorCode;
    @JacksonXmlProperty(localName = "errorDesc")
    private String errorMsg;

    public XmlError(String fieldName, String errorCode, String errorMsg) {
        this.fieldName = fieldName;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
