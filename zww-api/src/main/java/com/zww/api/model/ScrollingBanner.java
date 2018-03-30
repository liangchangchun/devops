package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.zww.api.util.StringUtils;


/**
 * 滚动实时横幅
 * Created by SUN on 2018/1/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrollingBanner implements Serializable {
    private static final long serialVersionUID = 1L;
    private String memberName;
    private Date time;
    private String dollName;

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
        return format.format(time);
    }

    public String getDollName() {
        if (StringUtils.isEmpty(dollName)) {
            return "练习娃娃";
        }
        return dollName;
    }

    public String getInterjections() {
        Random random = new Random();
        int result = random.nextInt(6);// 返回[0,10)集合中的整数，注意不包括10
        switch (result) {
            case 0:
                return "窝草!";
            case 1:
                return "天哪!";
            case 2:
                return "天了噜!";
            case 3:
                return "不会吧!";
            case 4:
                return "真的假的!";
            case 5:
                return "难以置信!";
            default:
                return "!!!!!";
        }
    }


    @Override
    public String toString() {
        String dollName = getDollName();
        int endIndex = dollName.indexOf("-");
        if (endIndex > 0) {
            dollName = dollName.substring(0, endIndex);
        }
        memberName = StringUtils.replaceBlank(memberName);
        if (StringUtils.isEmpty(memberName)) {
            return getInterjections() + "不愿意透露姓名的某大神抓中了一只" + dollName;
        }
        String s = getInterjections() + memberName + "抓中了一只" + dollName;
        return s;
    }
}
