package ru.delivery.process;

// TODO придумать получше имя
public class ProcessDto {

    private String name;
    private TypeProcess type;
    private ExecutionProcessMode mode;
    private AbstractProcess process;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeProcess getType() {
        return type;
    }

    public void setType(TypeProcess type) {
        this.type = type;
    }

    public ExecutionProcessMode getMode() {
        return mode;
    }

    public void setMode(ExecutionProcessMode mode) {
        this.mode = mode;
    }

    public AbstractProcess getProcess() {
        return process;
    }

    public void setProcess(AbstractProcess process) {
        this.process = process;
    }

    public enum TypeProcess {
        ASYNC, SYNC
    }

    public enum ExecutionProcessMode {
        SINGLE, PARALLEL
    }
}
