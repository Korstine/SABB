package fr.sabb.application.business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Official;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.service.official.OfficialService;
import fr.sabb.application.service.transport.TransportService;
import fr.sabb.application.utils.SheetUtils;

@Component
public class PlanningSheetGeneratorBusiness {

	@Autowired
	private MatchService matchService;

	@Autowired
	private TransportService transportService;

	@Autowired
	private OfficialService offcialService;

	public void generateSABBWeeklySheet(LocalDate weeklyDate) {

		InputStream wrappedStream = null;

		String fileName = createFileName(weeklyDate);

		try {
			wrappedStream = POIFSFileSystem.createNonClosingInputStream(new FileInputStream(
					"C:/Users/flori/OneDrive/Basket/Saison 18-19/Convoc/Template SABB Planning Weekend 18-19.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(wrappedStream);

			if (null != workbook) {
				XSSFSheet sheet = workbook.getSheetAt(0);

				Date date = Date.valueOf(weeklyDate);
				Calendar calWeekly = Calendar.getInstance();
				calWeekly.setTime(date);
				matchService
						.getAllMatchForCurrentSeasonByWeekOfYear(calWeekly.get(Calendar.YEAR),
								calWeekly.get(Calendar.WEEK_OF_YEAR))
						.forEach(m -> fillSheetForSABBMatch(m, workbook, sheet));

				workbook.setSheetName(0, fileName);
				workbook.write(new FileOutputStream(
						"C:/Users/flori/OneDrive/Basket/Saison 18-19/Convoc/SABB " + fileName + ".xlsx"));
			}

			if (null != wrappedStream) {
				wrappedStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void generateCTCWeeklySheet(LocalDate weeklyDate) {

		InputStream wrappedStream = null;

		String fileName = createFileName(weeklyDate);

		try {
			wrappedStream = POIFSFileSystem.createNonClosingInputStream(new FileInputStream(
					"C:/Users/flori/OneDrive/Basket/Saison 18-19/Convoc/Template CTC Planning Weekend 18-19.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(wrappedStream);

			if (null != workbook) {
				XSSFSheet sheet = workbook.getSheetAt(0);

				setCellValuePlanning(workbook, sheet, "Date", getLiteralDate(weeklyDate));
				Date date = Date.valueOf(weeklyDate);
				Calendar calWeekly = Calendar.getInstance();
				calWeekly.setTime(date);
				matchService
						.getAllCTCMatchForCurrentSeasonByWeekOfYear(calWeekly.get(Calendar.YEAR),
								calWeekly.get(Calendar.WEEK_OF_YEAR))
						.forEach(m -> setLineValueCTCPlanning(m, workbook, sheet));

				workbook.setSheetName(0, fileName);
				workbook.write(new FileOutputStream(
						"C:/Users/flori/OneDrive/Basket/Saison 18-19/Convoc/CTC " + fileName + ".xlsx"));
			}

			if (null != wrappedStream) {
				wrappedStream.close();
			}
			wrappedStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillSheetForSABBMatch(Match match, XSSFWorkbook workbook, XSSFSheet sheet) {
		if (!match.getTeam().getAssociation().isMain()) {
			return;
		}
		Transport transport = transportService.getTransportOrBarByMatch(match);
		Official official = offcialService.getOfficialFromMatch(match);

		try {
			setLineValuePlanning(match, workbook, sheet, transport, official);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	static void setCellValuePlanning(XSSFWorkbook workbook, XSSFSheet sheet, String cellName, String data) {
		XSSFName name = workbook.getName(cellName);

		if (name != null) {
			CellRangeAddress cellRangeAddress = CellRangeAddress.valueOf(name.getRefersToFormula());

			if (cellRangeAddress != null) {
				XSSFCell cell = sheet.getRow(cellRangeAddress.getFirstRow()).getCell(cellRangeAddress.getFirstColumn());

				if (cell != null) {
					cell.setCellValue(data);
				}
			}
		}
	}

	/**
	 * Fonction utilisée pour le remplissage d'une rencontre dans le fichier excel
	 */
	static void setLineValuePlanning(Match match, XSSFWorkbook workbook, XSSFSheet sheet, Transport transport,
			Official official) {
		int indexRow = getCellRowPlanning(workbook, match.getTeam().getExcelReference());
		XSSFRow row = sheet.getRow(indexRow);

		if (null != row) {

			if (match.isHome()) {
				row.getCell(1).setCellValue("Saint André");
				row.getCell(2).setCellValue(match.getOpponent());

				row.getCell(4).setCellValue(SheetUtils.toHeureFormat(match.getMatchDate()));

				row.getCell(6).setCellValue("-");

				if (official != null) {
					row.getCell(7).setCellValue(String.format("%s - %s",
							SheetUtils.formatFullLicenseeString(official.getLicenseeReferee1()),
							SheetUtils.formatFullLicenseeString(official.getLicenseeReferee2())));
							
					
					row.getCell(8).setCellValue(String.format("%s - %s",
							SheetUtils.formatFullLicenseeString(official.getLicenseeTable1()),
							SheetUtils.formatFullLicenseeString(official.getLicenseeTable2())));
					
				}

				if (transport != null) {
					row.getCell(9).setCellValue(getTransportCellContent(transport));
				}

			} else {
				row.getCell(1).setCellValue(match.getOpponent());
				row.getCell(2).setCellValue("-");

				row.getCell(4).setCellValue("Départ : " + SheetUtils.toHeureFormat(match.getMatchDate()) + " Match : "
						+ SheetUtils.toHeureFormat(match.getMatchDate()));
				if (transport != null) {
					row.getCell(6).setCellValue(getTransportCellContent(transport));
				}
				row.getCell(9).setCellValue("-");

			}

			row.getCell(3).setCellValue(SheetUtils.getDateToString(match.getMatchDate()));
		}
	}

	private static String getTransportCellContent(Transport transport) {
		return String.format("%s - %s - %s", getTransportLicenseeName(transport.getLicensee1()),
				getTransportLicenseeName(transport.getLicensee2()), getTransportLicenseeName(transport.getLicensee3()));
	}

	private static String getTransportLicenseeName(Licensee licensee) {
		if (licensee == null) {
			return "";
		}
		return licensee.getName();
	}

	/**
	 * Fonction utilisée pour le remplissage d'une rencontre dans le fichier excel
	 */
	static void setLineValueCTCPlanning(Match match, XSSFWorkbook workbook, XSSFSheet sheet) {
		int indexRow = getCellRowPlanning(workbook, match.getTeam().getExcelReferenceCtc());
		XSSFRow row = sheet.getRow(indexRow);

		if (null != row) {

			if (match.isHome()) {
				row.getCell(1).setCellValue(match.getTeam().getAssociation().getName());
				row.getCell(2).setCellValue(match.getOpponent());
			} else {
				row.getCell(1).setCellValue(match.getOpponent());
				row.getCell(2).setCellValue("-");
			}

			row.getCell(4).setCellValue(SheetUtils.toHeureFormat(match.getMatchDate()));
			row.getCell(3).setCellValue(SheetUtils.getDateToString(match.getMatchDate()));
		}
	}

	static int getCellRowPlanning(XSSFWorkbook workbook, String cellName) {
		int result = 0;
		if (cellName != null) {

			System.out.println(cellName);
			CellRangeAddress cellRangeAddress = CellRangeAddress.valueOf(cellName);

			if (cellRangeAddress != null) {
				result = cellRangeAddress.getFirstRow();
			}
		}
		return result;
	}

	private static String createFileName(LocalDate weeklyDate) {
		String name = "Planning Weekend ";
		return String.format("%s_%s_%s_%s", name, weeklyDate.getDayOfMonth(), weeklyDate.getMonth(),
				weeklyDate.getYear());
	}

	private static String getLiteralDate(LocalDate weeklyDate) {
		SimpleDateFormat sDF = new SimpleDateFormat("EEE dd MMM yyyy");
		return sDF.format(Date.valueOf(weeklyDate));
	}
}
