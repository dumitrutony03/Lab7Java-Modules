package org.example.models;

public interface Entitate<TId>
{
    void SetId(TId id);
    TId GetId();
}
