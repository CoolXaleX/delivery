package ru.sbrf.delivery.dto;

public class DeliveryDocument {
    private String documentGuid;
    private String fileLink;
    private String ecmFolder;
    private String ecmFile;
    private String oadSent;

    public String getDocumentGuid() {
        return documentGuid;
    }

    public void setDocumentGuid(String documentGuid) {
        this.documentGuid = documentGuid;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getEcmFolder() {
        return ecmFolder;
    }

    public void setEcmFolder(String ecmFolder) {
        this.ecmFolder = ecmFolder;
    }

    public String getEcmFile() {
        return ecmFile;
    }

    public void setEcmFile(String ecmFile) {
        this.ecmFile = ecmFile;
    }

    public String getOadSent() {
        return oadSent;
    }

    public void setOadSent(String oadSent) {
        this.oadSent = oadSent;
    }
}
