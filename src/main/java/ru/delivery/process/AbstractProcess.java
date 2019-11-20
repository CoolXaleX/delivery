package ru.delivery.process;

import ru.delivery.dto.DeliveryTask;

abstract public class AbstractProcess {

    private boolean isSent;

    public AbstractProcess(DeliveryTask task) {
        this.isSent = isCompletedTask(task);
    }

    protected abstract boolean isCompletedTask(DeliveryTask task);

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public abstract boolean execute(DeliveryTask task);
}
