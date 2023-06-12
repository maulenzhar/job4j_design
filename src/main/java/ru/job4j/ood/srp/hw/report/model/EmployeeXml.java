package ru.job4j.ood.srp.hw.report.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXml {
    public List<Employee> employee;

    public EmployeeXml() {
    }

    public EmployeeXml(List<Employee> employee) {
        this.employee = employee;
    }
}
