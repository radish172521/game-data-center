package com.bootdo.gamedata.vo;

import com.bootdo.gamedata.enums.RecordType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameUserRecordRequest {

    private Integer userId;
    private String openId;
    private Integer businessId;
    private RecordType recordType;
}
