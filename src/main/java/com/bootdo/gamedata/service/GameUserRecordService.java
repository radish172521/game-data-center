package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameUserRecordDO;
import com.bootdo.gamedata.qo.GameUserRecordQo;
import com.bootdo.gamedata.vo.GameUserRecordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameUserRecordService {

    /**
     * 添加游戏用户任务记录
     * @param gameUserRecordRequest
     */
    void addGameRecord(GameUserRecordRequest gameUserRecordRequest);

    GameUserRecordDO getById(Integer id);

    Page<GameUserRecordDO> findPage(GameUserRecordQo qo, Pageable pageable);

    void delete(Integer taskId);

}
