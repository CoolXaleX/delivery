package ru.delivery.process;

import org.springframework.beans.factory.annotation.Autowired;
import ru.delivery.dto.DeliveryTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class Processor {

    private ProcessModel processModel;
    private ExecutorService executorService;

    @Autowired
    public Processor(ProcessModel processModel, ExecutorService executorService) {
        this.processModel = processModel;
        this.executorService = executorService;
    }

    public boolean process(DeliveryTask task) throws ExecutionException, InterruptedException {
        List<AbstractProcess> processes = processModel.getProcessList(task);
        return executorService.submit(
                () -> processes.stream().allMatch(abstractProcess -> abstractProcess.execute(task))
        ).get();
    }
}
