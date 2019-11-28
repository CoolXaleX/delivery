package ru.delivery.process;

import ru.delivery.dto.DeliveryTask;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class OadProcess extends AbstractProcess {

    @Override
    public boolean isSent(DeliveryTask task) {
        return task.getDocumentList().stream()
                .noneMatch(doc -> isBlank(doc.getOadSent()));
    }

    @Override
    public boolean execute(DeliveryTask task) {
        return true;
    }
}
