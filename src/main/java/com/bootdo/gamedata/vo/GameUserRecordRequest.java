package com.bootdo.gamedata.vo;

import com.bootdo.gamedata.enums.RecordType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameUserRecordRequest {

    private Integer userId;
    private String openId;
    private Integer businessId;
    private RecordType recordType;
}
