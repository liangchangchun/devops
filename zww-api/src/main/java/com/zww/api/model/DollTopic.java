package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DollTopic implements Serializable {

    private static final long serialVersionUID = -8301859233599255823L;
    private Integer id;
    private Integer dollId;         //娃娃机序号
    private String dollName;    //娃娃机名称
    private Integer roomType;   //房间类型
    private Integer topicType;   //主题类型
    private String topicName;   //主题名称
    private Integer groupid;       //房间分组
}
