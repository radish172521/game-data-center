package com.bootdo.gamedata.qo;

import com.bootdo.gamedata.enums.RecordType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameUserRecordQo {
    private String businessName;
    private Integer gameUserName;
    private RecordType recordType;
}
