package ru.sbrf.delivery.dto;

import java.util.List;

public class MainEntry {
    private String deliveryGuid;
    private List<Document> documents;

    public String getDeliveryGuid() {
        return deliveryGuid;
    }

    public void setDeliveryGuid(String deliveryGuid) {
        this.deliveryGuid = deliveryGuid;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
