/*package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.model.Device;
import com.repository.DeviceRepository;

@Service
public class KafkaConsumerDeviceService {

	@Autowired
	private DeviceRepository deviceRepository;
	
	@KafkaListener(topics = "${kakfa.topic.hello}")
	public void saveDevice(Device device) {
		System.out.println("device Service Called ");
	deviceRepository.save(device);
	}

}
*/