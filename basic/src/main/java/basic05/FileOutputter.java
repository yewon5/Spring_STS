package basic05;

import java.io.FileWriter;

//인사말을 파일에 저장하는 클래스
public class FileOutputter implements Outputter {
	private String filePath;
	
	public void setFilePath(String path) {
		filePath = path;
	}
	
	@Override
	public void output(String msg) throws Exception { //Outputter 인터페이스에 똑같이 throws 처리해준다
		FileWriter writer = new FileWriter(filePath);
		writer.write(msg);
		writer.close();
	}
}
