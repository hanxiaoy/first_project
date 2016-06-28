package org.shiro.chapter12web.credentials;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class MySimpleByteSource extends SimpleByteSource implements Serializable {

	private static final long serialVersionUID = 6719887406937801006L;

	public MySimpleByteSource(byte[] bytes) {
		super(bytes);
	}

	public MySimpleByteSource(ByteSource source) {
		super(source);
	}

	public MySimpleByteSource(char[] chars) {
		super(chars);
	}

	public MySimpleByteSource(File file) {
		super(file);
	}

	public MySimpleByteSource(InputStream stream) {
		super(stream);
	}

	public MySimpleByteSource(String string) {
		super(string);
	}

	
	public static ByteSource bytes(String string) {
        return new SimpleByteSource(string);
    }
	
}
