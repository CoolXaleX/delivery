package ru.sbrf.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.delivery.cg.RegisterTaskCgService;
import ru.sbrf.delivery.dto.BaseResponse;
import ru.sbrf.delivery.dto.MainEntry;

@ComponentScan
@RestController
@RequestMapping("/send")
public class DeliveryController {

    @Autowired
    private RegisterTaskCgService registerTaskCgService;

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(true, 1);
    }

    @GetMapping("/test")
    public BaseResponse pay(@RequestBody MainEntry request) {

        return registerTaskCgService.registerTask(request);
    }

}
