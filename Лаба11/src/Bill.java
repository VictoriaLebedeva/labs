import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;

public class Bill implements Serializable {

    Date date = new Date();

    private String company;
    private String service;
    private String unit = "unit";
    private double cost;
    private int volume;

    public String getCompany() {
        return company;
    }

    public String getService() {
        return service;
    }

    public double getCost() {
        return cost;
    }

    public int getVolume() {
        return volume;
    }

    public Bill(String company, String service, double cost, int volume) {
        assert (company == null || service == null || cost < 0 || volume < 0 );
        this.company = company;
        this.service = service;
        this.cost = cost;
        this.volume = volume;
    }

    public Object getFieldName(String fieldName)  throws IllegalAccessException, NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(this);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date = " + date +
                ", company = '" + company + '\'' +
                ", service = '" + service + '\'' +
                ", unit = '" + unit + '\'' +
                ", cost = " + cost +
                ", volume = " + volume +
                '}';
    }
}

class SortedByCompany implements Comparator<Bill> {
    @Override
    public int compare(Bill object1, Bill object2) {
        return object1.getCompany().compareTo(object2.getCompany());
    }
}

class SortedByService implements Comparator<Bill> {
    @Override
    public int compare(Bill object1, Bill object2) {
        return object1.getService().compareTo(object2.getService());
    }
}

class SortedByCost implements Comparator<Bill> {
    @Override
    public int compare(Bill object1, Bill object2) {
        return object1.getCost() > object2.getCost() ? 1 : object1.getCost() == object2.getCost()? 0 : -1 ;
    }
}

class SortedByVolume implements Comparator<Bill> {
    @Override
    public int compare(Bill object1, Bill object2) {
        return object1.getVolume() > object2.getVolume() ? 1 : object1.getVolume() == object2.getVolume()? 0 : -1 ;
    }
}


