package com.ttProject.application.view.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class JSystemOutTextArea extends JTextArea {
	/** SerialID */
	private static final long serialVersionUID = -362870041567248243L;
	private TextAreaOutputStream outStream;
	public JSystemOutTextArea() {
		super();
		outStream = new TextAreaOutputStream(this);
		// 自動的にoutStreamをセットしておく。
		outStream.setSystemOut();
	}
	public TextAreaOutputStream getOutStream() {
		return outStream;
	}
	public void flush() {
		this.append(outStream.toString());
		outStream.reset();
	}
	public PrintStream getSystemOut() {
		return outStream.getSystemOut();
	}
	

	private class TextAreaOutputStream extends ByteArrayOutputStream {
		private PrintStream outStream;
		private JSystemOutTextArea textarea;
		public TextAreaOutputStream(JSystemOutTextArea textarea) {
			super();
			this.textarea = textarea;
		}
		public void setSystemOut() {
			outStream = System.out;
			System.setOut(new PrintStream(this));
		}
		public PrintStream getSystemOut() {
			return outStream;
		}
		@Override
		public synchronized void write(byte[] b, int off, int len) {
			outStream.write(b, off, len);
			super.write(b, off, len);
			textarea.flush();
		}
		@Override
		public void write(byte[] b) throws IOException {
			outStream.write(b);
			super.write(b);
			textarea.flush();
		}
		@Override
		public synchronized void write(int b) {
			outStream.write(b);
			super.write(b);
			textarea.flush();
		}
	}
}
