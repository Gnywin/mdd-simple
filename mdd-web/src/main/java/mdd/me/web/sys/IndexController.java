package mdd.me.web.sys;

import mdd.me.core.annotation.CheckToken;
import mdd.me.core.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/4/23.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    @CheckToken(value = false)
    @LoginRequired(value = false)
    public String index(){
        return "index";
    };
}
