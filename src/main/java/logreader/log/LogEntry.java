package logreader.log;

//Class that contains all entry types from the log
public class LogEntry {
    private String ipAddress;
    private String timestamp;
    private String httpMethod;
    private String url;
    private int statusCode;
    private int responseSize;

    public LogEntry() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }
}
