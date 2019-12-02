package ru.delivery.process;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.delivery.dto.DeliveryTask;

import java.util.*;

public class ProcessModel {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger log = LoggerFactory.getLogger(ProcessModel.class);

    private Map<String, List<String>> processRoad = new HashMap<>();

    private Gson gson = new Gson();

    public ProcessModel() {
        processRoad.put("main_v1", Arrays.asList(
                "{\"name\":\"EcmProcess\", \"type\":\"ASYNC\", \"mode\":\"SINGLE\"}",
                "{\"name\":\"TfsProcess\", \"type\":\"ASYNC\", \"mode\":\"SINGLE\"}"
        ));
        processRoad.put("oad_v1", Arrays.asList(
                "{\"name\":\"EcmProcess\", \"type\":\"SYNC\", \"mode\":\"SINGLE\"}",
                "{\"name\":\"TfsProcess\", \"type\":\"SYNC\", \"mode\":\"PARALLEL\"}",
                "{\"name\":\"OadProcess\", \"type\":\"SYNC\", \"mode\":\"PARALLEL\"}"
        ));
    }

    public List<ProcessDto> getProcessList(DeliveryTask task) {
        List<ProcessDto> result = new ArrayList<>();
        String currentRoad = task.getProcessName() + "_" + task.getProcessVersion();

        for(String processJson : processRoad.get(currentRoad)) {
            ProcessDto processDto = convert(processJson);
            if (processDto == null) continue;
            AbstractProcess process = getClass(processDto.getName());
            if (process == null) continue;
            processDto.setProcess(process);
            if (!process.isSent(task)) result.add(processDto);
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

    private AbstractProcess getClass(String className) {
        try {
            return (AbstractProcess) applicationContext.getBean(className);
        } catch (Exception e) {
            log.error("Cant create class {}", className, e);
            return null;
        }
    }

}
