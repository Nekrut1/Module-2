package nekrutenko.model.devices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Television extends Device {
    private int diagonal;
    private String country;

    public Television(String series, String screenType, int price, int diagonal, String country) {
        super(series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
        setDeviceType(DeviceType.TELEVISION);
    }

    @Override
    public String toString() {
        return "Television{" +
                "series='" + getSeries() + '\'' +
                ", screenType='" + getScreenType() + '\'' +
                ", price=" + getPrice() +
                ", deviceType=" + getDeviceType() +
                ", diagonal=" + diagonal +
                ", country=" + country +
                '}';
    }
}
