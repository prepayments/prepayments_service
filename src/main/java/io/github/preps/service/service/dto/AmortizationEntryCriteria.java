package io.github.preps.service.service.dto;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the AmortizationEntry entity. This class is used in AmortizationEntryResource to receive all the possible filtering options from the Http GET request parameters. For example the
 * following could be a valid requests:
 * <code> /amortization-entries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use fix type specific filters.
 */
public class AmortizationEntryCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter amortizationDate;

    private BigDecimalFilter amortizationAmount;

    private StringFilter particulars;

    private BooleanFilter posted;

    private StringFilter serviceOutlet;

    private StringFilter accountNumber;

    private StringFilter accountName;

    private LongFilter prepaymentEntryId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getAmortizationDate() {
        return amortizationDate;
    }

    public void setAmortizationDate(LocalDateFilter amortizationDate) {
        this.amortizationDate = amortizationDate;
    }

    public BigDecimalFilter getAmortizationAmount() {
        return amortizationAmount;
    }

    public void setAmortizationAmount(BigDecimalFilter amortizationAmount) {
        this.amortizationAmount = amortizationAmount;
    }

    public StringFilter getParticulars() {
        return particulars;
    }

    public void setParticulars(StringFilter particulars) {
        this.particulars = particulars;
    }

    public BooleanFilter getPosted() {
        return posted;
    }

    public void setPosted(BooleanFilter posted) {
        this.posted = posted;
    }

    public StringFilter getServiceOutlet() {
        return serviceOutlet;
    }

    public void setServiceOutlet(StringFilter serviceOutlet) {
        this.serviceOutlet = serviceOutlet;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringFilter getAccountName() {
        return accountName;
    }

    public void setAccountName(StringFilter accountName) {
        this.accountName = accountName;
    }

    public LongFilter getPrepaymentEntryId() {
        return prepaymentEntryId;
    }

    public void setPrepaymentEntryId(LongFilter prepaymentEntryId) {
        this.prepaymentEntryId = prepaymentEntryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AmortizationEntryCriteria that = (AmortizationEntryCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(amortizationDate, that.amortizationDate) && Objects.equals(amortizationAmount, that.amortizationAmount) &&
            Objects.equals(particulars, that.particulars) && Objects.equals(posted, that.posted) && Objects.equals(serviceOutlet, that.serviceOutlet) &&
            Objects.equals(accountNumber, that.accountNumber) && Objects.equals(accountName, that.accountName) && Objects.equals(prepaymentEntryId, that.prepaymentEntryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amortizationDate, amortizationAmount, particulars, posted, serviceOutlet, accountNumber, accountName, prepaymentEntryId);
    }

    @Override
    public String toString() {
        return "AmortizationEntryCriteria{" + (id != null ? "id=" + id + ", " : "") + (amortizationDate != null ? "amortizationDate=" + amortizationDate + ", " : "") +
            (amortizationAmount != null ? "amortizationAmount=" + amortizationAmount + ", " : "") + (particulars != null ? "particulars=" + particulars + ", " : "") +
            (posted != null ? "posted=" + posted + ", " : "") + (serviceOutlet != null ? "serviceOutlet=" + serviceOutlet + ", " : "") +
            (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") + (accountName != null ? "accountName=" + accountName + ", " : "") +
            (prepaymentEntryId != null ? "prepaymentEntryId=" + prepaymentEntryId + ", " : "") + "}";
    }

}
