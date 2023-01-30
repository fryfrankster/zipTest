package co.zip.candidate.userapi.user;

import java.math.BigDecimal;

public class UserDTO {
    private long id;
    private String name;
    private String email;
    private BigDecimal monthlySalary;
    private BigDecimal monthlyExpenses;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String email, BigDecimal monthlySalary, BigDecimal monthlyExpenses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.monthlySalary = monthlySalary;
        this.monthlyExpenses = monthlyExpenses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public BigDecimal getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(BigDecimal monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }
}
