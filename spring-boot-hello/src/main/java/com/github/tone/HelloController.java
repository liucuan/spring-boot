package com.github.tone;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jenny on 2017/5/24.
 */
@RestController
public class HelloController {

    private static final Map<String, String> codeMap = new HashMap() {{
        put("val.xmlbean.name.1", "00001");
        put("val.xmlbean.name.2", "00002");
    }};
    private static final Map<String, String> descMap = new HashMap() {{
        put("val.xmlbean.name.1", "名字不能为空");
        put("val.xmlbean.name.2", "名字长度5-10个字符");
    }};

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/valJson")
    public Object valJson(@RequestBody(required = false) JsonBean jsonBean) {
        if (jsonBean == null) {
            JsonBean jb = new JsonBean();
            jb.setDate(new Date());
            jb.setId(98);
            jb.setLocalDateTime(LocalDateTime.now());
            jb.setName("tone");
            return jb;
        }
        return jsonBean;
    }

    @PostMapping(value = "/valXml", produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
    public Object valXml(@RequestBody(required = false) @Validated XmlBean xmlBean,
        BindingResult bindingResult) {
        if (xmlBean == null) {
            XmlBean jb = new XmlBean();
            jb.setDate(new Date());
            jb.setId(98);
            jb.setName("tone");
            return jb;
        }
        if (bindingResult.hasErrors()) {
            FieldError errorMsg = (FieldError) bindingResult.getAllErrors().get(0);
            return new XmlResult(
                new XmlError(errorMsg.getField(), codeMap.get(errorMsg.getDefaultMessage()),
                    descMap.get(errorMsg.getDefaultMessage())));
        }
        return xmlBean;
    }

    @Token(save = true)
    @RequestMapping("/savetoken")
    public String getToken(HttpServletRequest request, HttpServletResponse response) {
        return (String) request.getSession().getAttribute("token");
    }

    @Token(remove = true)
    @RequestMapping("/removetoken")
    public String removeToken(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }
}
