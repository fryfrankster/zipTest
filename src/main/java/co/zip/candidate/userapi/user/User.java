package co.zip.candidate.userapi.user;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Positive
    @Column(name = "monthly_salary", nullable = false)
    private BigDecimal monthlySalary;

    @NotNull
    @Positive
    @Column(name = "monthly_expenses", nullable = false)
    private BigDecimal monthlyExpenses;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", monthlySalary=" + monthlySalary +
                ", monthlyExpenses=" + monthlyExpenses +
                '}';
    }
}
