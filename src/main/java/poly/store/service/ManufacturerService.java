package poly.store.service;

import java.util.List;

import poly.store.entity.Manufacturer;
import poly.store.model.ManufacturerModel;

public interface ManufacturerService {

	ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel);

	List<Manufacturer> findAll();

	ManufacturerModel getOneManufacturerById(Integer id);

	void delete(Integer id);

	ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel);

}
