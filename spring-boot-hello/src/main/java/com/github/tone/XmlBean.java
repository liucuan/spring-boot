package com.github.tone;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@JacksonXmlRootElement(localName = "XmlBean")
public class XmlBean {

    @JacksonXmlProperty(localName = "tid")
    private Integer id;
    @NotBlank(message = "val.xmlbean.name.1")
    @Size(min = 5, max = 10, message = "val.xmlbean.name.2")
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
