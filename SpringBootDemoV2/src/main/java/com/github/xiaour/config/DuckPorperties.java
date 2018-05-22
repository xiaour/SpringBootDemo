package com.github.xiaour.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/1 下午5:04
 */
@Configuration
@ConfigurationProperties(prefix="duck")
public class DuckPorperties{

    private String duckName;

    private int  totalCount;

    public String getDuckName() {
        return duckName;
    }

    public void setDuckName(String duckName) {
        this.duckName = duckName;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
