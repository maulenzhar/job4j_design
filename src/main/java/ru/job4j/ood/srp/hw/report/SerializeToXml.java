package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.model.EmployeeXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class SerializeToXml implements SerializeReport {
    public JAXBContext context = null;

    public SerializeToXml(JAXBContext context) {
        this.context = context;
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
            for (Employee employee:employees) {
                marshaller.marshal(new EmployeeXml(employee.getName(), employee.getHired(), employee.getFired(), employee.getSalary()), writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
