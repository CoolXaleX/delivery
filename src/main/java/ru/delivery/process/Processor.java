package ru.delivery.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.delivery.dto.DeliveryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static ru.delivery.process.ProcessDto.ExecutionProcessMode.PARALLEL;
import static ru.delivery.process.ProcessDto.ExecutionProcessMode.SINGLE;

@Component
public class Processor {

    private ProcessModel processModel;
    private ExecutorService executorService;
    private ProcessDto.TypeProcess currentType;

    @Autowired
    public Processor(ProcessModel processModel) {
        this.processModel = processModel;
        this.currentType = ProcessDto.TypeProcess.SYNC;
    }

    public Processor(ProcessModel processModel, ExecutorService executorService, ProcessDto.TypeProcess currentType) {
        this.processModel = processModel;
        this.executorService = executorService;
        this.currentType = currentType;
    }

    public boolean process(DeliveryTask task) throws InterruptedException {
        List<ProcessDto> processes = processModel.getProcessList(task);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Boolean>> parallelTask = new ArrayList<>();

        for (ProcessDto dto : processes) {
            // Выполняем только те задачи, в каком режиме мы запущены
            if (dto.getType() != currentType) continue;

            if (dto.getMode() == SINGLE) {
                if (!dto.getProcess().execute(task)) return false;
            } else if (dto.getMode() == PARALLEL) {
                parallelTask.add(executorService.submit(() -> dto.getProcess().execute(task)));
            }
        }
        if (!parallelTask.isEmpty()) {
            return parallelTask.stream().allMatch(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    return false;
                }
            });
        }
        return true;
    }
}
