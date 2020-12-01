package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.repositories.GeoLocationRepository;

@Service
public class GeoLocationService {

	@Autowired
	private GeoLocationRepository geoLocationRepository;

	public GeoLocation save(GeoLocation g) {
		return this.geoLocationRepository.save(g);
	}

	public boolean deleteById(Long id) {
		boolean exists = this.geoLocationRepository.existsById(id);

		if (exists) {
			this.geoLocationRepository.deleteById(id);
		}
		return exists;
	}

	public boolean delete(GeoLocation g) {
		boolean exists = this.geoLocationRepository.existsById(g.getLocationId());

		if (exists) {
			this.geoLocationRepository.delete(g);
		}
		return exists;
	}

	public ArrayList<GeoLocation> findAll() {
		return (ArrayList<GeoLocation>) this.geoLocationRepository.findAll();
	}
	
	public Page<GeoLocation> findAll(Pageable pageable) {
		return (Page<GeoLocation>) this.geoLocationRepository.findAll(pageable);
	}

	public GeoLocation findById(Long id) {
		Optional<GeoLocation> found = this.geoLocationRepository.findById(id);

		if (found.isPresent()) {
			GeoLocation geoLocation = found.get();
			return geoLocation;
		} else
			return null;
	}
	
	public GeoLocation update(GeoLocation changedGeoLocation, long id) {
		Optional<GeoLocation> found = this.geoLocationRepository.findById(id);
		
		if(found.isPresent()) {
			GeoLocation oldGeoLocation = found.get();
			oldGeoLocation.setLatitude(changedGeoLocation.getLatitude());
			oldGeoLocation.setLongitude(changedGeoLocation.getLongitude());
			oldGeoLocation.setPlace(changedGeoLocation.getPlace());
			return this.geoLocationRepository.save(oldGeoLocation);
		} else
			return null;
	}

}
