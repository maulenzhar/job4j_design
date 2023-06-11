package ru.job4j.ood.srp.hw.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.formatter.XmlReportDateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.model.EmployeeXml;
import ru.job4j.ood.srp.hw.report.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SerializeToXmlTest {
    @Test
    public void whenSerializeToXml() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Dima", now, now, 101);
        DateTimeParser<Calendar> parser = new XmlReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        SerializeReport engine = new SerializeToXml(JAXBContext.newInstance(EmployeeXml.class), parser);
        final String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                +
                "<employees>\n"
                +
                "    <employee>\n"
                +
                "        <fired>"
                + parser.parse(worker1.getFired())
                + "</fired>\n"
                +
                "        <hired>"
                + parser.parse(worker1.getHired())
                + "</hired>\n"
                +
                "        <name>Ivan</name>\n"
                +
                "        <salary>100.0</salary>\n"
                +
                "    </employee>\n"
                +
                "    <employee>\n"
                +
                "        <fired>"
                + parser.parse(worker2.getFired())
                + "</fired>\n"
                +
                "        <hired>"
                + parser.parse(worker1.getHired())
                + "</hired>\n"
                +
                "        <name>Dima</name>\n"
                +
                "        <salary>101.0</salary>\n"
                +
                "    </employee>\n"
                +
                "</employees>\n";

        assertThat(engine.serialize(store.findBy(em -> true))).isEqualTo(expect.toString());
    }
}