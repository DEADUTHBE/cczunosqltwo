package com.example.searchapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @ClassName User
 * @Description
 * @Author darkgreen
 * @Date 2021/11/15 14:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    String Username;
    String searchKey;
    String timeStamp;

    public User(String searchKey, String timeStamp) {
        this.searchKey = searchKey;
        this.timeStamp = timeStamp;
    }
}
