package com.bootdo.gamedata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "advlist")
public class AdvListController {

    @RequestMapping(value = "test")
    public String test(){
        return "[{\"aid\":5105,\"adid\":1708,\"id\":5105,\"title\":\"消消方块传奇\",\"description\":\"自由组合的俄罗斯方块，你没玩过的全新体验\",\"icon\":\"https://cdn-xyx.raink.com.cn/box_weixin/xxfkcq23.gif\",\"image\":\"https://cdn-xyx.raink.com.cn/box_weixin/xxfkcq23.gif\",\"button\":\"玩一玩\",\"coins\":100,\"count\":\"\",\"appid\":\"wxa58b231eadce8a74\",\"page\":\"\",\"category\":2,\"banner\":\"\",\"ad_image\":\"\",\"plan_number\":\"\",\"table\":30,\"start_time\":\"\",\"end_time\":\"\",\"platform\":\"\",\"sex\":3,\"auto_adid\":null},{\"aid\":5049,\"adid\":1712,\"id\":5049,\"title\":\"更多游戏\",\"description\":\"好玩123游戏中心\",\"icon\":\"https://cdn-xyx.raink.com.cn/ad/images/i_5049_4.png\",\"image\":\"https://cdn-xyx.raink.com.cn/ad/images/i_5049_4.png\",\"button\":\"玩一玩\",\"coins\":100,\"count\":\"\",\"appid\":\"wx37d3d0dc7eb63354\",\"page\":\"/pages/index/index\",\"category\":2,\"banner\":\"\",\"ad_image\":\"\",\"plan_number\":\"\",\"table\":30,\"start_time\":\"\",\"end_time\":\"\",\"platform\":\"android\",\"sex\":3,\"auto_adid\":null},{\"aid\":5085,\"adid\":1714,\"id\":5085,\"title\":\"六角经典版\",\"description\":\"玩游戏，得高分，百万现金红包等你拿！！\",\"icon\":\"5085_201901171350125946.png\",\"image\":\"5085_201901171350125946.png\",\"button\":\"消一消\",\"coins\":100,\"count\":\"\",\"appid\":\"wxf07eb11fd77152b0\",\"page\":\"\",\"category\":2,\"banner\":\"\",\"ad_image\":\"\",\"plan_number\":\"\",\"table\":30,\"start_time\":\"\",\"end_time\":\"\",\"platform\":\"\",\"sex\":3,\"auto_adid\":null},{\"aid\":5086,\"adid\":2057,\"id\":5086,\"title\":\"黑洞也疯狂\",\"description\":\"多玩家实时作战，猥琐发育，吞掉对手，绝地求生。\",\"icon\":\"https://cdn-xyx.raink.com.cn/hdyfk/_20181031162420.png\",\"image\":\"https://cdn-xyx.raink.com.cn/hdyfk/_20181031162420.png\",\"button\":\"玩一玩\",\"coins\":100,\"count\":\"\",\"appid\":\"wx64b6cdbd6e151ccf\",\"page\":\"\",\"category\":2,\"banner\":\"\",\"ad_image\":\"\",\"plan_number\":\"\",\"table\":30,\"start_time\":\"\",\"end_time\":\"\",\"platform\":\"\",\"sex\":3,\"auto_adid\":null}]";
    }

    @RequestMapping(value= "version")
    public String test1(){
        return "{\"city\":\"\",\"status1Online\":\"0\",\"status2Online\":\"0\",\"codeVerOnline\":\"0.0.9\",\"status1Develop\":\"1\",\"status2Develop\":\"1\",\"codeVerDevelop\":\"0.1.0\",\"bannerrefresh\":\"2\",\"sharetime\":\"3\",\"secondsharechance1\":\"50\",\"secondsharechance2\":\"70\",\"gasstation_share\":\"10\",\"carom_share\":\"5\",\"box_share\":\"5\",\"tips_share\":\"999\",\"again_share\":\"999\",\"Resurrection_share\":\"999\",\"link_enable_level\":\"1\",\"ClosebuttonOnline\":\"0\",\"ClosebuttonDevelop\":\"0\",\"share_chance\":\"90\",\"share_chance_rp\":\"90\",\"ver\":\"adv4.6.json\"}";
    }
}
