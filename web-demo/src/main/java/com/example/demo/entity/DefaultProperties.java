package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 321 on 2017/8/11.
 */
@Data
@NoArgsConstructor
@Component
public class DefaultProperties {
    @Value("${com.default.title}")
    private String title;
    @Value("${com.default.description}")
    private String description;

    //省略getter settet方法

}
