package io.github.preps.service.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A PrepaymentEntry.
 */
@Entity
@Table(name = "prepayment_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "prepaymententry")
public class PrepaymentEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^\\d{10,16}$")
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @NotNull
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @NotNull
    @Column(name = "prepayment_id", nullable = false)
    private String prepaymentId;

    @NotNull
    @Column(name = "prepayment_date", nullable = false)
    private LocalDate prepaymentDate;

    @Column(name = "particulars")
    private String particulars;

    @NotNull
    @Pattern(regexp = "^[0-9]{3}$")
    @Column(name = "service_outlet", nullable = false)
    private String serviceOutlet;

    @NotNull
    @Column(name = "prepayment_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal prepaymentAmount;

    @Column(name = "months")
    private Integer months;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @OneToMany(mappedBy = "prepaymentEntry")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AmortizationEntry> amortizationEntries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public PrepaymentEntry accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public PrepaymentEntry accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getPrepaymentId() {
        return prepaymentId;
    }

    public void setPrepaymentId(String prepaymentId) {
        this.prepaymentId = prepaymentId;
    }

    public PrepaymentEntry prepaymentId(String prepaymentId) {
        this.prepaymentId = prepaymentId;
        return this;
    }

    public LocalDate getPrepaymentDate() {
        return prepaymentDate;
    }

    public void setPrepaymentDate(LocalDate prepaymentDate) {
        this.prepaymentDate = prepaymentDate;
    }

    public PrepaymentEntry prepaymentDate(LocalDate prepaymentDate) {
        this.prepaymentDate = prepaymentDate;
        return this;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public PrepaymentEntry particulars(String particulars) {
        this.particulars = particulars;
        return this;
    }

    public String getServiceOutlet() {
        return serviceOutlet;
    }

    public void setServiceOutlet(String serviceOutlet) {
        this.serviceOutlet = serviceOutlet;
    }

    public PrepaymentEntry serviceOutlet(String serviceOutlet) {
        this.serviceOutlet = serviceOutlet;
        return this;
    }

    public BigDecimal getPrepaymentAmount() {
        return prepaymentAmount;
    }

    public void setPrepaymentAmount(BigDecimal prepaymentAmount) {
        this.prepaymentAmount = prepaymentAmount;
    }

    public PrepaymentEntry prepaymentAmount(BigDecimal prepaymentAmount) {
        this.prepaymentAmount = prepaymentAmount;
        return this;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public PrepaymentEntry months(Integer months) {
        this.months = months;
        return this;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public PrepaymentEntry supplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public PrepaymentEntry invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public Set<AmortizationEntry> getAmortizationEntries() {
        return amortizationEntries;
    }

    public void setAmortizationEntries(Set<AmortizationEntry> amortizationEntries) {
        this.amortizationEntries = amortizationEntries;
    }

    public PrepaymentEntry amortizationEntries(Set<AmortizationEntry> amortizationEntries) {
        this.amortizationEntries = amortizationEntries;
        return this;
    }

    public PrepaymentEntry addAmortizationEntry(AmortizationEntry amortizationEntry) {
        this.amortizationEntries.add(amortizationEntry);
        amortizationEntry.setPrepaymentEntry(this);
        return this;
    }

    public PrepaymentEntry removeAmortizationEntry(AmortizationEntry amortizationEntry) {
        this.amortizationEntries.remove(amortizationEntry);
        amortizationEntry.setPrepaymentEntry(null);
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrepaymentEntry prepaymentEntry = (PrepaymentEntry) o;
        if (prepaymentEntry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prepaymentEntry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrepaymentEntry{" + "id=" + getId() + ", accountNumber='" + getAccountNumber() + "'" + ", accountName='" + getAccountName() + "'" + ", prepaymentId='" + getPrepaymentId() + "'" +
            ", prepaymentDate='" + getPrepaymentDate() + "'" + ", particulars='" + getParticulars() + "'" + ", serviceOutlet='" + getServiceOutlet() + "'" + ", prepaymentAmount=" +
            getPrepaymentAmount() + ", months=" + getMonths() + ", supplierName='" + getSupplierName() + "'" + ", invoiceNumber='" + getInvoiceNumber() + "'" + "}";
    }
}
