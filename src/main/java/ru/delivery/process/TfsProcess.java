package ru.delivery.process;

import ru.delivery.dto.DeliveryTask;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class TfsProcess extends AbstractProcess {

    @Override
    public boolean isSent(DeliveryTask task) {
        return task.getDocumentList().stream()
                .noneMatch(doc -> isBlank(doc.getEcmFile()));
    }

    @Override
    public boolean execute(DeliveryTask task) {
        return true;
    }
}
