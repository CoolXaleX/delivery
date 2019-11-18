package ru.delivery.dto;

import java.util.List;

public class DeliveryTask {
    private String deliveryGuid;
    private List<DeliveryDocument> documentList;
    private String processName;
    private String processVersion;

    public String getDeliveryGuid() {
        return deliveryGuid;
    }

    public void setDeliveryGuid(String deliveryGuid) {
        this.deliveryGuid = deliveryGuid;
    }

    public List<DeliveryDocument> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<DeliveryDocument> documentList) {
        this.documentList = documentList;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessVersion() {
        return processVersion;
    }

    public void setProcessVersion(String processVersion) {
        this.processVersion = processVersion;
    }
}
