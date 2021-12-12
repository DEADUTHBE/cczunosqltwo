package com.example.searchapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BiliHotRecord {
    String _id;
    String title;
    String score;
    String text;
    String picUrl;
    String detailUrl;
    String rank;
    String timestamp;
}
