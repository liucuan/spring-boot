package com.tone.socket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhaoxiang.liu
 * @date 2019/6/6
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMessage {

    private String loginUserNum;

    private String content;
}
