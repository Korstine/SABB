package fr.sabb.application.business;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.service.transport.TransportService;
import fr.sabb.application.utils.SabbConstantes;
import fr.sabb.application.utils.SheetUtils;

@Component
public class TransportSheetGeneratorBusiness {

	@Autowired
	private LicenseeService licenseeService;

	@Autowired
	private MatchService matchService;

	@Autowired
	private TransportService transportService;

	public void generateTransportSheet(Team team) throws ValidationException {
		InputStream wrappedStream = null;

		String fileName = team.getName();

		// Récupération de l'equipe en base
		try {
			wrappedStream = POIFSFileSystem.createNonClosingInputStream(new FileInputStream(
					String.format("C:/Users/flori/OneDrive/Basket/%s/Transport/Template Feuille Transport.xlsx",SabbConstantes.CURRENT_SEASON)));
			XSSFWorkbook workbook = new XSSFWorkbook(wrappedStream);

			if (null != workbook) {
				XSSFSheet sheet = workbook.getSheetAt(0);

				// Ajout des licenciés
				licenseeService.getAllByTeam(team).stream().sorted(Comparator.comparing(Licensee::getName)).forEach(l -> addLineValueTransportLicencie(workbook, sheet, l));

				// Ajout des rencontres
				matchService.getAllMatchByTeam(team).stream()
						.sorted((m1, m2) -> m1.getMatchDate().compareTo(m2.getMatchDate()))
						.forEach(m -> addLineValueTransportRencontre(workbook, sheet, m,
								transportService.getTransportOrBarByMatch(m)));

				workbook.setSheetName(0, fileName);
				workbook.write(new FileOutputStream(
						String.format("C:/Users/flori/OneDrive/Basket/%s/Transport/%s.xlsx",SabbConstantes.CURRENT_SEASON, fileName)));
			}

			if (null != wrappedStream) {
				wrappedStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Fonction utilisée pour le remplissage d'un licencie dans le fichier excel
	 * 
	 * @param transport
	 * @param isRetour
	 */
	static void addLineValueTransportRencontre(XSSFWorkbook workbook, XSSFSheet sheet, Match match,
			Transport transport) {
		boolean retour = false;
		if (retour) {
			int bottom = getCellRow(workbook, "Rencontre");
			sheet.shiftRows(bottom, bottom + 100, 1, true, false);
			XSSFRow newRow = sheet.createRow(bottom);
			XSSFRow bottomRow = sheet.getRow(bottom + 1);
			newRow.setHeightInPoints(bottomRow.getHeightInPoints());
			newRow.createCell(0).setCellValue("Retour");
			XSSFCellStyle cellStyle = (XSSFCellStyle) bottomRow.getCell(0).getCellStyle();
			sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), 0, 5));
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		}
		int bottom = getCellRow(workbook, "Rencontre");
		sheet.shiftRows(bottom, bottom + 100, 1, true, false);
		XSSFRow newRow = sheet.createRow(bottom);
		XSSFRow bottomRow = sheet.getRow(bottom + 1);
		newRow.setHeightInPoints(bottomRow.getHeightInPoints());

		if ((null != bottomRow) && (null != newRow)) {
			newRow.createCell(0).setCellValue(SheetUtils.getDateToString(match.getMatchDate()) + " "
					+ SheetUtils.toHeureFormat(match.getMatchDate()));

			if (match.isHome()) {
				newRow.createCell(1).setCellValue("SAINT ANDRE BASKET BALL");

				newRow.createCell(2).setCellValue(match.getOpponent());
			} else {

				newRow.createCell(1).setCellValue(match.getOpponent());

				newRow.createCell(2).setCellValue("");
			}

			if (transport != null) {

				newRow.createCell(3)
						.setCellValue(String.format("%s - %s - %s", SheetUtils.formatLicenseeString(transport.getLicensee1()),
								SheetUtils.formatLicenseeString(transport.getLicensee2()),
								SheetUtils.formatLicenseeString(transport.getLicensee3())));
			}
			sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), 3, 5));

			XSSFCellStyle cellStyle0 = (XSSFCellStyle) bottomRow.getCell(0).getCellStyle();
			if (!match.isHome()) {
				cellStyle0.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
				cellStyle0.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			} else {
				cellStyle0.setFillPattern(FillPatternType.NO_FILL);
			}
			newRow.getCell(0).setCellStyle(cellStyle0);
			XSSFCellStyle cellStyle1 = (XSSFCellStyle) bottomRow.getCell(1).getCellStyle();
			if (!match.isHome()) {
				cellStyle1.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
				cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			} else {
				cellStyle1.setFillPattern(FillPatternType.NO_FILL);
			}
			newRow.getCell(1).setCellStyle(cellStyle1);
			XSSFCellStyle cellStyle2 = (XSSFCellStyle) bottomRow.getCell(2).getCellStyle();
			if (!match.isHome()) {
				cellStyle2.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
				cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			} else {
				cellStyle2.setFillPattern(FillPatternType.NO_FILL);
			}
			newRow.getCell(2).setCellStyle(cellStyle2);
			XSSFCellStyle cellStyle3 = (XSSFCellStyle) bottomRow.getCell(3).getCellStyle();
			if (!match.isHome()) {
				cellStyle3.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
				cellStyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			} else {
				cellStyle3.setFillPattern(FillPatternType.NO_FILL);
			}
			if (newRow.getCell(3) != null) {
				newRow.getCell(3).setCellStyle(cellStyle3);
			}

		}
	}

	static int getCellRow(XSSFWorkbook workbook, String cellName) {
		int result = 0;
		XSSFName name = workbook.getName(cellName);

		if (name != null) {
			CellRangeAddress cellRangeAddress = CellRangeAddress.valueOf(name.getRefersToFormula());

			if (cellRangeAddress != null) {
				result = cellRangeAddress.getFirstRow();
			}
		}

		return result;
	}

	private static void addLineValueTransportLicencie(XSSFWorkbook workbook, XSSFSheet sheet, Licensee licensee) {
		int bottom = getCellRow(workbook, "LICENCIE");
		sheet.shiftRows(bottom, bottom + 100, 1, true, false);
		XSSFRow newRow = sheet.createRow(bottom);
		XSSFRow bottomRow = sheet.getRow(bottom + 1);
		newRow.setHeightInPoints(bottomRow.getHeightInPoints());

		if ((null != bottomRow) && (null != newRow)) {
			newRow.createCell(0).setCellValue(licensee.getNumLicensee());

			newRow.createCell(1).setCellValue(licensee.toString());
			newRow.createCell(2).setCellValue(licensee.getPhone() == null ? "" : licensee.getPhone());

			newRow.createCell(3).setCellValue(licensee.getMail());

			sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), 3, 5));

			newRow.getCell(0).setCellStyle(bottomRow.getCell(0).getCellStyle());
			newRow.getCell(1).setCellStyle(bottomRow.getCell(1).getCellStyle());
			newRow.getCell(2).setCellStyle(bottomRow.getCell(2).getCellStyle());
			newRow.getCell(3).setCellStyle(bottomRow.getCell(3).getCellStyle());
		}
	}

}
