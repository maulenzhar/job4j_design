package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.model.EmployeeXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

public class SerializeToXml implements SerializeReport {
    public JAXBContext context = null;
    private final DateTimeParser<Calendar> dateTimeParser;

    public SerializeToXml(JAXBContext context, DateTimeParser<Calendar> dateTimeParser) {
        this.context = context;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String serialize(List<Employee> employees) {
        Marshaller marshaller = null;
        String xml = "";

        try {
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new EmployeeXml(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
