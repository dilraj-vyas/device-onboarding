package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.model.Device;
import com.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;

	@KafkaListener(topics = "${kakfa.topic.hello}")
	public void addDevice(List<Device> devices) {
	System.out.println("Device Service Called");
		deviceRepository.save(devices);
	}

	public void addAllDevice(List<Device> devices) {
		deviceRepository.save(devices);
	}
	
	public void deleteDevice(int id) {
		deviceRepository.delete(id);
	}

	public List<Device> getAllDevices() {
		
		List<Device> devices = new ArrayList<Device>();
		Iterable<Device> itr = deviceRepository.findAll();
		for (Device device : itr) {
			devices.add(device);
		}
		return devices;
	}

	public Device getDevice(int id) {
		 return deviceRepository.findOne(id);
	}

	public void updateDevice(Device device) {
		 deviceRepository.save(device);
	}
}
