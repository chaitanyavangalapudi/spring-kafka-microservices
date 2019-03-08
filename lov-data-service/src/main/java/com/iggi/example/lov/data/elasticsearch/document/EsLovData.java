package com.iggi.example.lov.data.elasticsearch.document;

import java.math.BigInteger;

import javax.annotation.Nullable;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(indexName = "lovdata-index", type = "lovdata", shards = 1, replicas = 0)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EsLovData {

    @Id
    private BigInteger id;

    @Nullable
    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true), otherFields = {
            @InnerField(suffix = "verbatim", type = FieldType.Keyword) })
    private String lovType;

    @Nullable
    private String lovDisplayValue;

    @Nullable
    private String lovDbValue;

    @Nullable
    private Integer sequenceNumber;

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

    public EsLovData(BigInteger id, String lovType, String lovDisplayValue, String lovDbValue, Integer sequenceNumber) {
        super();
        this.id = id;
        this.lovType = lovType;
        this.lovDisplayValue = lovDisplayValue;
        this.lovDbValue = lovDbValue;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString() {
        return "EsLovData [id=" + id + ", lovType=" + lovType + ", lovDisplayValue=" + lovDisplayValue + ", lovDbValue="
                + lovDbValue + ", sequenceNumber=" + sequenceNumber + "]";
    }

    public EsLovData() {

    }

}
