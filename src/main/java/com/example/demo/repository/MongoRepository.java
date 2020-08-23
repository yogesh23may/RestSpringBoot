package com.example.demo.repository;

import java.util.List;

public interface MongoRepository<T> {

	public List<T> getAllObjects();

	public void saveObject(T object);

	public T getObject(String id);

	public void deleteObject(String id);

	public void createCollection();

	public void dropCollection();


}
