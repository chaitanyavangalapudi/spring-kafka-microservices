package com.iggi.example.lov.data.entity;

import java.io.Serializable;

import java.math.BigInteger;

import javax.persistence.Column;

public class PrimaryKeyLovTypeAndLovDbValue implements Serializable {

    private static final long serialVersionUID = 5743096993729767611L;

    @Column(name = "id", length = 20, unique = true)
    private BigInteger id;

    @Column(name = "lov_type")
    private String lovType;

    @Column(name = "lov_db_value", length = 20)
    private String lovDbValue;

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

    public String getLovDbValue() {
        return lovDbValue;
    }

    public void setLovDbValue(String lovDbValue) {
        this.lovDbValue = lovDbValue;
    }

    public PrimaryKeyLovTypeAndLovDbValue(BigInteger id, String lovType, String lovDbValue) {
        super();
        this.id = id;
        this.lovType = lovType;
        this.lovDbValue = lovDbValue;
    }

    public PrimaryKeyLovTypeAndLovDbValue() {
        super();

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PrimaryKeyLovTypeAndLovDbValue that = (PrimaryKeyLovTypeAndLovDbValue) object;

        if (!lovType.equals(that.lovType)) {
            return false;
        }
        return lovDbValue.equals(that.lovDbValue);
    }

    @Override
    public int hashCode() {
        int result = lovType.hashCode();
        result = 31 * result + lovDbValue.hashCode();
        return result;
    }

}
