package ru.sbrf.delivery.process;

import ru.sbrf.delivery.dto.DeliveryTask;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class EcmProcess extends AbstractProcess {

    public EcmProcess(DeliveryTask task) {
        super(task);
    }

    @Override
    protected boolean isCompletedTask(DeliveryTask task) {
        return task.getDocumentList().stream()
                .noneMatch(doc -> isBlank(doc.getEcmFolder()));
    }
}
