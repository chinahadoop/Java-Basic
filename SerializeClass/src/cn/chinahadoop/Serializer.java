package cn.chinahadoop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Serializer {
	static byte[] INTTYPE = { 0 };
	static byte[] CHARTYPE = { 1 };

	public static void serialize(Object obj, String storeFile)
			throws IllegalArgumentException, IllegalAccessException, IOException {
		File file = new File(storeFile);
		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(file));
			String className = obj.getClass().getName();
			byte[] classNameBytes = className.getBytes();
			output.write(classNameBytes.length);
			output.write(classNameBytes);
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldType = field.getType().getName();
				if ((!"int".equals(fieldType)) && (!"char".equals(fieldType))) {
					System.err.println("Error! We only support serialization of int or char type.");
					return;
				}
				String fieldName = field.getName();
				byte[] fieldNameBytes = fieldName.getBytes();
				output.write(fieldNameBytes.length);
				output.write(fieldNameBytes);
				// Make the field can be read even if it is privateFs
				field.setAccessible(true);
				if ("int".equals(fieldType)) {
					output.write(field.getInt(obj));
				} else {
					output.write(field.getChar(obj));
				}

			}
		} catch (IOException e) {
			System.err.println("Got exception when serialize to the output file.");
			throw e;
		} finally {
			output.close();
		}
	}

	public static void serialize(Object obj) throws IOException, IllegalAccessException {
		serialize(obj, "storeFile");
	}

	public static Object deserialize(String storeFile)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Object inputObj = null;
		File file = new File(storeFile);
		InputStream input = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			int classNameLen = input.read();
			byte[] classNameByte = new byte[classNameLen];
			input.read(classNameByte);
			String className = new String(classNameByte);
			inputObj = Class.forName(className).newInstance();
			Field[] fields = Class.forName(className).getDeclaredFields();
			Map<String, Field> fieldMap = new HashMap<String, Field>();
			for (Field field : fields) {
				fieldMap.put(field.getName(), field);
			}
			int fieldNameLen = 0;
			while ((fieldNameLen = input.read()) != -1) {
				byte[] fieldNameBytes = new byte[fieldNameLen];
				input.read(fieldNameBytes);
				String fieldName = new String(fieldNameBytes);
				Field field = fieldMap.get(fieldName);
				// Handle the access property for private fields
				boolean fieldAccessChanged = false;
				if (!field.isAccessible()) {
					field.setAccessible(true);
					fieldAccessChanged = true;
				}
				if ("int".equals(field.getType().getTypeName())) {
					field.setInt(inputObj, input.read());
				} else {
					field.setChar(inputObj, (char) input.read());
				}
				if (fieldAccessChanged) {
					field.setAccessible(false);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("Got exception when deserialize from the input file.");
			throw e;
		} finally {
			input.close();
		}

		return inputObj;
	}

	public static Object deserialize()
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return deserialize("storeFile");
	}
}
