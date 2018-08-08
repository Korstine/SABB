package fr.sabb.application.ui.screen.business.licensee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import fr.sabb.application.service.licensee.LicenseeService;

public class CSVLicenseeUploader  implements Receiver, SucceededListener {
    public FileReader fileReader;
    private LicenseeService licenseeService;
    
    public CSVLicenseeUploader(LicenseeService licenseeService) {
    	super();
    	this.licenseeService = licenseeService;
    }

    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        try(FileReader fileReader = new FileReader(filename)){
        	this.fileReader = fileReader;
        	
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
    }

    public void uploadSucceeded(SucceededEvent event) {
      //  this.licenseeService.fillDBWithCsvFile(fileReader);
    }
}
