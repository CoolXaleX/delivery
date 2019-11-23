package ru.delivery.process;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.delivery.dto.DeliveryTask;

import java.util.*;

public class ProcessModel {

    private static final Logger log = LoggerFactory.getLogger(ProcessModel.class);

    private Map<String, List<String>> processRoad = new HashMap<>();
    private static final String PACKAGE = "ru.delivery.process.";

    private Gson gson = new Gson();

    public ProcessModel() {
        processRoad.put("main_v1", Arrays.asList(
                "{\"name\":\"EcmProcess\", \"type\":\"ASYNC\", \"mode\":\"SINGLE\"}",
                "{\"name\":\"TfsProcess\", \"type\":\"ASYNC\", \"mode\":\"SINGLE\"}"
        ));
        processRoad.put("oad_v1", Arrays.asList(
                "{\"name\":\"EcmProcess\", \"type\":\"SYNC\", \"mode\":\"SINGLE\"}",
                "{\"name\":\"TfsProcess\", \"type\":\"SYNC\", \"mode\":\"SINGLE\"}",
                "{\"name\":\"OadProcess\", \"type\":\"ASYNC\", \"mode\":\"SINGLE\"}"
        ));
    }

    public List<ProcessDto> getProcessList(DeliveryTask task) {
        List<ProcessDto> result = new ArrayList<>();
        String currentRoad = task.getProcessName() + "_" + task.getProcessVersion();

        for(String processJson : processRoad.get(currentRoad)) {
            ProcessDto processDto = convert(processJson);
            if (processDto == null) continue;
            AbstractProcess process = getClass(processDto.getName(), task);
            if (process == null) continue;
            processDto.setProcess(process);
            if (!process.isSent()) result.add(processDto);
        }
        return result;
    }

    private ProcessDto convert(String process) {
        try {
            return gson.fromJson(process, ProcessDto.class);
        } catch (Exception e) {
            log.error("Cant parse json string: {} to ProcessDto", process, e);
            return null;
        }
    }

    private AbstractProcess getClass(String className, DeliveryTask task) {
        try {
            // TODO нужно вынести в конвертер
            return (AbstractProcess)Class.forName(PACKAGE + className)
                    .getConstructor(DeliveryTask.class)
                    .newInstance(task);
        } catch (Exception e) {
            log.error("Cant create class {}", className, e);
            return null;
        }
    }

}
