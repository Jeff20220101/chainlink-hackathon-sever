package com.scope.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 11:11
 * Description: TestController
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}