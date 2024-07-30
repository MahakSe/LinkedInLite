package Entity;

public class GeoLoc {
    private GeoLoc parent;
    private String address;
    private LocationType locationType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoLoc getParent() {
        return parent;
    }

    public void setParent(GeoLoc parent) {
        this.parent = parent;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }
}
