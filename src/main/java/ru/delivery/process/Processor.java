package ru.delivery.process;

import org.springframework.beans.factory.annotation.Autowired;
import ru.delivery.dto.DeliveryTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class Processor {

    private ProcessModel processModel;
    private ExecutorService executorService;
    private ProcessDto.TypeProcess currentType;

    @Autowired
    public Processor(ProcessModel processModel, ExecutorService executorService, ProcessDto.TypeProcess currentType) {
        this.processModel = processModel;
        this.executorService = executorService;
        this.currentType = currentType;
    }

    public boolean process(DeliveryTask task) throws ExecutionException, InterruptedException {
        List<ProcessDto> processes = processModel.getProcessList(task);
        for (ProcessDto dto : processes) {
            // Выполняем только те задачи, в каком режиме мы запущены
            if (dto.getType() != currentType) continue;
            // TODO Придумать механизм параллельного выполнения задач
            if (!dto.getProcess().execute(task)) return false;
        }
        return true;
    }
}
