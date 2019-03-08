package com.iggi.example.lov.data.projection;

import javax.annotation.Nullable;

import org.immutables.value.Value;

@Value.Immutable
public interface LovDataProjection {

    @Nullable
    String getLovType();

    @Nullable
    String getLovDisplayValue();

    @Nullable
    String getLovDbValue();

}
