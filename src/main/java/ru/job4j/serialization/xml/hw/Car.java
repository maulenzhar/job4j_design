package ru.job4j.serialization.xml.hw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.xml.Contact;
import ru.job4j.serialization.xml.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean isSport;

    @XmlAttribute
    private int year;

    private String name;
    private FuelType fuelType;

    @XmlElementWrapper(name = "formerOwners")
    @XmlElement(name = "formerOwner")
    private  String[] formerOwners;

    public Car() { }

    public Car(boolean isSport, int year, String name, FuelType fuelType, String... formerOwners) {
        this.isSport = isSport;
        this.year = year;
        this.name = name;
        this.fuelType = fuelType;
        this.formerOwners = formerOwners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isSport=" + isSport
                + ", year=" + year
                + ", name='" + name + '\''
                + ", fuelType=" + fuelType
                + ", formerOwners=" + Arrays.toString(formerOwners)
                + '}';
    }

    @XmlRootElement(name = "fuelType")
    public static class FuelType {
        @XmlAttribute
        private String type;

        public FuelType() {
        }

        public FuelType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "FuelType{"
                    + "type='" + type + '\''
                    + '}';
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        final Car car = new Car(false, 2003, "Toyota Camry", new FuelType("Petrol"),
                "Patrick", "Spongebob", "Squidward");

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Car.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
