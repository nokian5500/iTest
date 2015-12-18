package entities;

public class Browser {

    // Variables

    private String name;
    private String version;
    private String platform;

    // Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getPlatform() {
        return platform;
    }
}