package lab4.service;

import java.util.Collection;

public interface CollectionOperations {
    <T> T getLastElement(Collection<T> collection);
}