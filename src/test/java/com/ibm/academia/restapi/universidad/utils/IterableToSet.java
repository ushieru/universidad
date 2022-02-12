package com.ibm.academia.restapi.universidad.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IterableToSet {
    public static <T> Set<T> parse(Iterable<T> iterable) {
        return new HashSet<T>((Collection<T>) iterable);
    }
}
