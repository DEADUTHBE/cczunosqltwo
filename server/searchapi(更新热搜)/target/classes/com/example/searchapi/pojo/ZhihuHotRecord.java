package com.example.searchapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZhihuHotRecord {
    String _id;
    String word;
    String num;
    String text;
    String PicUrl;
    String detailUrl;
    String rank;
    String timestamp;
}
