package ru.delivery.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.delivery.dto.DeliveryTask;

import java.lang.reflect.Constructor;
import java.util.*;

public class ProcessModel {

    private static final Logger log = LoggerFactory.getLogger(ProcessModel.class);

    private Map<String, List<String>> processRoad = new HashMap<>();
    private static final String PACKAGE = "ru.delivery.process.";

    public ProcessModel() {
        processRoad.put("main_v1", Arrays.asList("EcmProcess", "TfsProcess"));
        processRoad.put("oad_v1", Arrays.asList("EcmProcess", "TfsProcess", "OadProcess"));
    }

    public List<AbstractProcess> getProcessList(DeliveryTask task) {
        List<AbstractProcess> result = new ArrayList<>();
        String currentRoad = task.getProcessName() + "_" + task.getProcessVersion();
        for(String className : processRoad.get(currentRoad)) {
            try {
                AbstractProcess process = (AbstractProcess)Class.forName(PACKAGE + className)
                        .getConstructor(DeliveryTask.class)
                        .newInstance(task);
                if (!process.isSent()) result.add(process);
            } catch (Exception e) {
                log.error("Cant create class {}", className, e);
            }
        }
        return result;
    }
}
