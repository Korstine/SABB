package fr.sabb.application.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.utils.SabbConstantes;

@Component
public class EmergementListingAGBusiness {
	
	@Autowired
	private LicenseeService licenseeService;
	
	List<String> adressAlreadyRepresented;
	
	List<String> listParent;

	public void generateEmergementListing(LocalDate dateAG) throws IOException {
        FileWriter fw;
		fw = new FileWriter(String.format("C:/Users/flori/OneDrive/Basket/%s/Listing.csv",SabbConstantes.CURRENT_SEASON));
        BufferedWriter output = new BufferedWriter(fw);
        adressAlreadyRepresented = new ArrayList<>();
        listParent = new ArrayList<>();
        int count = 1;
        List<Licensee> listingAG = licenseeService.getAll().stream().filter(Licensee::isMainAssociation).filter(l -> this.filterByFamily(l, dateAG)).sorted((l1,l2) -> l1.getName().compareTo(l2.getName())).collect(Collectors.toList());
        
        for(Licensee l : listingAG) {
        	try {
				output.write(l.toStringForListingAG(count++, listParent.contains(l.getNumLicensee())));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        output.flush();
        output.close();
        fw.close();
	}
	
	private boolean filterByFamily(Licensee l, LocalDate dateAG) {
		LocalDate dateOfBirth = getDateOfBirth(l);
		if(Period.between(dateOfBirth, dateAG).getYears() >= 16) {
			return true;
		}
		
		if(adressAlreadyRepresented.contains(l.getAdress().toUpperCase())) {
			return false;
		}
		listParent.add(l.getNumLicensee());
		adressAlreadyRepresented.add(l.getAdress().toUpperCase());
		return true;
	}

	private LocalDate getDateOfBirth(Licensee l) {
		if(l.getDateOfBirth() == null) {
			return LocalDate.of(getYearFromLicenceNumber(l.getNumLicensee()), 1, 1);
		} else {
			return l.getDateOfBirth().toLocalDateTime().toLocalDate();
		}
		
	}
	
	private int getYearFromLicenceNumber(String numLicensee) {
		int year = Integer.parseInt(numLicensee.substring(2, 4));
		return year + (year > 30 ? 1900 : 2000);
	}
	
	

}
