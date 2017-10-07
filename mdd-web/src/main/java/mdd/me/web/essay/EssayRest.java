package mdd.me.web.essay;

import mdd.me.core.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 猫大东 on 2017/8/14.\
 * 评论
 */
@RestController
@RequestMapping("essay")
public class EssayRest {
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseVo add(){
        return new ResponseVo();
    }

}
