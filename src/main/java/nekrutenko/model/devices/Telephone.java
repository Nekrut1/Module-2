package nekrutenko.model.devices;

public class Telephone extends Device {
    private String model;

    public Telephone(String series, String screenType, int price, String model) {
        super(series, screenType, price);
        this.model = model;
        setDeviceType(DeviceType.TELEPHONE);

    }

    @Override
    public String toString() {
        return "Telephone{" +
                "series='" + getSeries() + '\'' +
                ", screenType='" + getScreenType() + '\'' +
                ", price=" + getPrice() +
                ", deviceType=" + getDeviceType() +
                ", model=" + model +
                '}';
    }
}
