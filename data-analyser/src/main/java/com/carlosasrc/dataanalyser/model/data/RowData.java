package com.carlosasrc.dataanalyser.model.data;

public abstract class RowData {
    public boolean isClass(Class<?> classIdentity) {
        return this.getClass().equals(classIdentity);
    }
}
