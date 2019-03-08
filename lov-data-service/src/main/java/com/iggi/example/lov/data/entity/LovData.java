package com.iggi.example.lov.data.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "lov_data")
@IdClass(PrimaryKeyLovTypeAndLovDbValue.class)
public class LovData implements Serializable {

    private static final long serialVersionUID = -2587370752246526839L;

    @Column(name = "id", length = 20, unique = true)
    @GeneratedValue
    private BigInteger id;

    @Id
    @Column(name = "lov_type")
    private String lovType;

    @Column(name = "lov_display_value", length = 100)
    private String lovDisplayValue;

    @Id
    @Column(name = "lov_db_value", length = 20)
    private String lovDbValue;

    @Column(name = "sequence_number", length = 11)
    private Integer sequenceNumber;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date_time")
    private LocalDateTime lastUpdatedDateTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLovType() {
        return lovType;
    }

    public void setLovType(String lovType) {
        this.lovType = lovType;
    }

    public String getLovDisplayValue() {
        return lovDisplayValue;
    }

    public void setLovDisplayValue(String lovDisplayValue) {
        this.lovDisplayValue = lovDisplayValue;
    }

    public String getLovDbValue() {
        return lovDbValue;
    }

    public void setLovDbValue(String lovDbValue) {
        this.lovDbValue = lovDbValue;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public LovData(BigInteger id, String lovType, String lovDisplayValue, String lovDbValue, Integer sequenceNumber) {
        super();
        this.id = id;
        this.lovType = lovType;
        this.lovDisplayValue = lovDisplayValue;
        this.lovDbValue = lovDbValue;
        this.sequenceNumber = sequenceNumber;
    }

    public LovData(BigInteger id, String lovType, String lovDisplayValue, String lovDbValue, Integer sequenceNumber,
            String createdBy, LocalDateTime createdDateTime, String lastUpdatedBy, LocalDateTime lastUpdatedDateTime) {
        super();
        this.id = id;
        this.lovType = lovType;
        this.lovDisplayValue = lovDisplayValue;
        this.lovDbValue = lovDbValue;
        this.sequenceNumber = sequenceNumber;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    @Override
    public String toString() {
        return "LovData [id=" + id + ", lovType=" + lovType + ", lovDisplayValue=" + lovDisplayValue + ", lovDbValue="
                + lovDbValue + ", sequenceNumber=" + sequenceNumber + ", createdBy=" + createdBy + ", createdDateTime="
                + createdDateTime + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDateTime=" + lastUpdatedDateTime
                + "]";
    }

    public LovData() {
        super();
    }
}
