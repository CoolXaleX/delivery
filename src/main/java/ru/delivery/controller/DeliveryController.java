package ru.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.delivery.dto.BaseResponse;
import ru.delivery.dto.MainEntry;

@ComponentScan
@RestController
@RequestMapping("/send")
public class DeliveryController {

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(true, 1);
    }

    @GetMapping("/test")
    public BaseResponse pay(@RequestBody MainEntry request) {
        return null;
//        return registerTaskCgService.registerTask(request);
    }

}
