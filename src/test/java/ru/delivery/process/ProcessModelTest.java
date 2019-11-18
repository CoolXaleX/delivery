package ru.delivery.process;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.delivery.dto.DeliveryDocument;
import ru.delivery.dto.DeliveryTask;

import java.util.List;

import static java.util.Arrays.asList;

public class ProcessModelTest {

    private ProcessModel instance = new ProcessModel();

    @Test
    public void testGetProcessList() {
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setProcessName("main");
        deliveryTask.setProcessVersion("v1");
        DeliveryDocument doc = new DeliveryDocument();
        doc.setEcmFolder("someFolder");
        deliveryTask.setDocumentList(asList(new DeliveryDocument(), doc));
        List<AbstractProcess> processes = instance.getProcessList(deliveryTask);
        Assert.assertTrue(processes.get(0) instanceof EcmProcess);
        Assert.assertTrue(processes.get(1) instanceof TfsProcess);
    }

    @Test
    public void testGetProcessList_onlyTfs() {
        DeliveryTask deliveryTask = new DeliveryTask();
        deliveryTask.setProcessName("main");
        deliveryTask.setProcessVersion("v1");
        DeliveryDocument doc = new DeliveryDocument();
        doc.setEcmFolder("someFolder");
        deliveryTask.setDocumentList(asList(doc, doc));
        List<AbstractProcess> processes = instance.getProcessList(deliveryTask);
        Assert.assertTrue(processes.get(0) instanceof TfsProcess);
    }
}