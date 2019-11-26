package ru.delivery.converters;

import ru.delivery.dto.DeliveryDocument;
import ru.delivery.dto.DeliveryTask;
import ru.delivery.dto.Document;
import ru.delivery.dto.MainEntry;

import java.util.UUID;
import java.util.stream.Collectors;

public class Converter {

    public DeliveryTask convert(MainEntry entry) {
        DeliveryTask task = new DeliveryTask();
        task.setProcessName(entry.getProcessName());
        task.setDocumentList(entry.getDocuments().stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return task;
    }

    public DeliveryDocument convert(Document documents) {
        DeliveryDocument doc = new DeliveryDocument();
        doc.setDocumentGuid(UUID.randomUUID().toString());
        return doc;
    }


}
