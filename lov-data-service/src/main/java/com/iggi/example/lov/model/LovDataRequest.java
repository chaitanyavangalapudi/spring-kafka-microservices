package com.iggi.example.lov.model;

import javax.annotation.Nullable;

import org.immutables.value.Value;

@Value.Immutable
public interface LovDataRequest {

    @Nullable
    public String getLovType();

}
