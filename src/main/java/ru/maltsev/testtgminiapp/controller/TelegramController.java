package ru.maltsev.testtgminiapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
public class TelegramController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/init")
    @ResponseBody
    public Map<String, Object> initData(@RequestBody Map<String, String> data) {
        return Map.of(
            "status", "success",
            "user", Map.of(
                    "id", data.get("id"),
                "first_name", data.get("first_name"),
                "last_name", data.get("last_name"),
                "username", data.get("username")
            )
        );
    }
} 