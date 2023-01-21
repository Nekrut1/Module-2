package nekrutenko.model.devices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {
    private String series;
    private String screenType;
    private int price;
    private DeviceType deviceType;

    public Device(String series, String screenType, int price) {
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }
}
