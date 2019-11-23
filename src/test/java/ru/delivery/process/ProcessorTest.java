package ru.delivery.process;

import org.testng.annotations.Test;
import ru.delivery.dto.DeliveryDocument;
import ru.delivery.dto.DeliveryTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;
import static ru.delivery.process.ProcessDto.TypeProcess.ASYNC;
import static ru.delivery.process.ProcessDto.TypeProcess.SYNC;

public class ProcessorTest {

    // TODO замокать
    private ProcessModel model = new ProcessModel();
    private Processor syncInstance = new Processor(model, new ForkJoinPool(), SYNC);
    private Processor asyncInstance = new Processor(model, new ForkJoinPool(), ASYNC);

    @Test
    public void testProcessSync() throws ExecutionException, InterruptedException {
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setProcessName("oad");
        deliveryTask.setProcessVersion("v1");
        DeliveryDocument doc = new DeliveryDocument();
        doc.setEcmFolder("someFolder");
        deliveryTask.setDocumentList(asList(new DeliveryDocument(), doc));
        boolean result = syncInstance.process(deliveryTask);
        assertTrue(result);
    }

    @Test
    public void testProcessAsync() throws ExecutionException, InterruptedException {
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setProcessName("main");
        deliveryTask.setProcessVersion("v1");
        DeliveryDocument doc = new DeliveryDocument();
        doc.setEcmFolder("someFolder");
        deliveryTask.setDocumentList(asList(new DeliveryDocument(), doc));
        boolean result = asyncInstance.process(deliveryTask);
        assertTrue(result);
    }
}