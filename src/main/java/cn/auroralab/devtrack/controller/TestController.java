package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dao.TaskDAO;
import cn.auroralab.devtrack.dao.VCodeDAO;
import cn.auroralab.devtrack.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    public final VCodeDAO vCodeDAO;
    @Autowired
    private TaskDAO taskDAO;

    public TestController(VCodeDAO vCodeDAO) {
        this.vCodeDAO = vCodeDAO;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test2")
    @SkipTokenVerification
    public Object test2() {
        return taskDAO.selectById("123456");
    }

    @GetMapping("/getTestToken")
    @SkipTokenVerification
    public String getTestToken() {
        return JwtUtils.generate("92b06bf834d648dd94f1c288fd0c1c0e", "alpha_test");
    }
}
