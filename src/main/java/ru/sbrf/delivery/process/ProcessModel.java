package ru.sbrf.delivery.process;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessModel {

    private Map<String, List<Class>> processRoad = new HashMap<>();

    public ProcessModel() {
        processRoad.put("main_v1", Arrays.asList(EcmProcess.class, TfsProcess.class));
        processRoad.put("oad_v1", Arrays.asList(EcmProcess.class, TfsProcess.class, OadProcess.class));
    }}
