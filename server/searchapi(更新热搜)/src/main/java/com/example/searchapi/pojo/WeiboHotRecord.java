package com.example.searchapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeiboHotRecord {
    String _id;
    String word;
    String num;
    String text;
    String picUrl;
    String detailUrl;
    String rank;
    String timestamp;


}
