package com.device.serialize;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.model.Device;

public class MyDeviceSerializerByteArray implements Serializer<Device> {
	private String encoding = "UTF8";

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public byte[] serialize(String topic, Device data) {
		Byte[][] byteArray=new Byte[10][10];
		int sizeOfdeviceName;
		int sizeOfDeviceDescription;
		int sizeOfDeviceIp;
		int sizeOfDeviceNodeName;
		int sizeOfTerminalIpAddres;

		byte[] serializedDeviceName;
		byte[] serializedDeviceDescription;
		byte[] serializedDeviceIp;
		byte[] serializedDeviceNodeName;
		byte[] serializedTerminalIpAddres;

		if (data != null) {
			try {
				int deviceId = data.getDeviceId();

				serializedDeviceName = data.getDeviceName().getBytes(encoding);
				
				
//				sizeOfdeviceName = serializedDeviceName.length;

				serializedDeviceDescription = data.getDeviceDescription()
						.toString().getBytes(encoding);
//				sizeOfDeviceDescription = serializedDeviceDescription.length;

				serializedDeviceIp = data.getDeviceIp().toString()
						.getBytes(encoding);
//				sizeOfDeviceIp = serializedDeviceIp.length;

				serializedDeviceNodeName = data.getNodeName()
						.getBytes(encoding);
//				sizeOfDeviceNodeName = serializedDeviceNodeName.length;

				serializedTerminalIpAddres = data.getTerminalIpAddres()
						.getBytes(encoding);
//				sizeOfTerminalIpAddres = serializedTerminalIpAddres.length;
				ByteBuffer buf = ByteBuffer.allocate(5000);
				buf.putInt(deviceId);
				buf.put(serializedDeviceName);
				buf.put(serializedDeviceDescription);
				buf.put(serializedDeviceIp);
				buf.put(serializedDeviceNodeName);
				buf.put(serializedTerminalIpAddres);
				
				
				return buf.array();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
