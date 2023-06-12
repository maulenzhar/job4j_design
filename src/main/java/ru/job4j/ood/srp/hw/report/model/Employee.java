package ru.job4j.ood.srp.hw.report.model;


import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;

import javax.xml.bind.annotation.*;
import java.util.Calendar;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

   public DateTimeParser<Calendar> dateTimeParser;
    public Employee() {
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary, DateTimeParser<Calendar> dateTimeParser) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
        this.dateTimeParser = dateTimeParser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalendarHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getCalendarFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @XmlElement
    public String getHired() {
        return dateTimeParser.parse(hired);
    }

    @XmlElement
    public String getFired() {
        return dateTimeParser.parse(hired);
    }
}
