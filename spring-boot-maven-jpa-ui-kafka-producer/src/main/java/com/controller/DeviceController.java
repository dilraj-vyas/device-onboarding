package com.controller;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Device;
import com.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	private KafkaTemplate<String, Device> kafkaTemplate;

	@GetMapping("deviceRegistration")
	public String showLoginPage(Model model) {
		model.addAttribute("deviceForm", new Device());
		return "deviceRegistration";
	}

	@RequestMapping(value = "/deviceRegistration", method = RequestMethod.POST)
	public String deviceRegistration(
			@ModelAttribute("deviceForm") Device deviceForm, Model model) {

		if (deviceForm != null) {
			String topic = "kafka-app";
			kafkaTemplate.send(new ProducerRecord<String, Device>(topic,
					deviceForm));

			model.addAttribute("msg", "Device Added Successfully to kafka");
		} else
			model.addAttribute("msg", "There is a problem inserting device");
		return "home";
	}

	@RequestMapping(value = "/devices")
	public ModelAndView getDevices() {

		List<Device> devices = deviceService.getAllDevices();

		ModelAndView model = new ModelAndView("devices");
		model.addObject("devices", devices);

		return model;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/deviceApi")
	public void saveAlldevice(@RequestBody List<Device> devices) {
		String topic = "kafka-app";
		for (Device device : devices) {
			kafkaTemplate
					.send(new ProducerRecord<String, Device>(topic, device));
		}

		// deviceService.addAllDevice(devices);
	}
}
