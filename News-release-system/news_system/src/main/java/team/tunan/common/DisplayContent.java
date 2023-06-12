package team.tunan.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Tunan
 * @version 1.0
 * @date 2021/4/18 20:59
 * @description 二级标题下展示的类型
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum DisplayContent {

    NEWS_LIST(0, "新闻列表"),

    NEWS(1, "新闻"),

    LINK(2, "链接");

    @EnumValue
    private Integer key;

    @JsonValue
    private String display;
}
