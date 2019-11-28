package ru.delivery.process;

import ru.delivery.dto.DeliveryTask;

abstract public class AbstractProcess {

    /**
     * Проверка, была ли уже выполнена данная задача.
     * @param task - переменные процесса
     * @return true - задача была выполнена, false иначе
     */
    public abstract boolean isSent(DeliveryTask task);

    /**
     * Выполняет данную задачу.
     * @param task - переменные процесса
     * @return Результат выполнения задачи
     */
    public abstract boolean execute(DeliveryTask task);
}
