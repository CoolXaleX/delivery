package ru.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.delivery.converters.Converter;
import ru.delivery.dto.BaseResponse;
import ru.delivery.dto.MainEntry;
import ru.delivery.process.Processor;

import java.util.concurrent.ExecutionException;

@ComponentScan
@RestController
@RequestMapping("/send")
public class DeliveryController {

    @Autowired
    private Processor processor;

    @Autowired
    private Converter converter;

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(true, 1);
    }

    @GetMapping("/syncSend")
    public BaseResponse syncSend(@RequestBody MainEntry request) throws ExecutionException, InterruptedException {
        processor.process(converter.convert(request));
        return new BaseResponse(true, 0);
    }

}
