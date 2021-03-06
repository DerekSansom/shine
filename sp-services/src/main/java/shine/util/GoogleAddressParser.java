package shine.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class GoogleAddressParser extends DefaultHandler {

	private static final int AREA1 = 1;
	private static final int AREA2 = 2;
	private static final int AREA3 = 3;
	private static final int COUNTRY = 4;
	private SAXParserFactory spf = SAXParserFactory.newInstance();
	private InputStream xml;
	private String characters = "";
	private String longname, shortname;
//	private String country, countrycode, adminarea1, adminarea2, adminarea3;
//	private double lat, lng;
	LocDetails locDetails;
	private int type;

	private static Logger log = LoggerFactory.getLogger(GoogleAddressParser.class);
	private boolean inLocation;
	int addressCnt;

	public GoogleAddressParser(InputStream xml) {
		this.xml = xml;
	}

	public void parse() {

		/* Get the XMLReader of the SAXParser we created. */
		XMLReader xr;
		try {
			locDetails = new LocDetails();
			xr = getXmlReader();
			xr.setContentHandler(this);
			xr.parse(new InputSource(xml));

		} catch (ParserConfigurationException e) {
			log("parsing failed", e);
		} catch (SAXException e) {
			log("parsing failed", e);
		} catch (IOException e) {
			log("parsing failed", e);
		}
	}

	private void log(String string, Exception e) {
		log.error(string, e);
	}

	protected XMLReader getXmlReader() throws ParserConfigurationException,
			SAXException {
		SAXParser sp = spf.newSAXParser();
		return sp.getXMLReader();

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (addressCnt > 3) {
			return;
		}
		String str = new String(ch, start, length);
		characters += str;

	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (addressCnt > 3) {
			return;
		}
		characters = "";
		if ("result".equals(localName) || "result".equals(name)) {
			if (addressCnt > 3) {
				return;
			}
		} else if ("location".equals(localName) || "location".equals(name)) {
			inLocation = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		if (addressCnt > 3) {
			return;
		}
		if ("long_name".equals(localName) || "long_name".equals(name)) {
			longname = characters;
		} else if ("short_name".equals(localName) || "short_name".equals(name)) {
			shortname = characters;
		} else if ("type".equals(localName) || "type".equals(name)) {
			if ("administrative_area_level_1".equals(characters)) {
				type = AREA1;
			} else if ("administrative_area_level_2".equals(characters)) {
				type = AREA2;
			} else if ("administrative_area_level_3".equals(characters)) {
				type = AREA3;
			} else if ("country".equals(characters)) {
				type = COUNTRY;
			}
		} else if ("address_component".equals(localName) || "address_component".equals(name)) {
			switch (type) {
			case AREA1:
				if (locDetails.adminarea1 == null) {
					locDetails.adminarea1 = longname;
					addressCnt++;
				}
				break;
			case AREA2:
				if (locDetails.adminarea2 == null) {
					locDetails.adminarea2 = longname;
					addressCnt++;
				}
				break;
			case AREA3:
				if (locDetails.adminarea3 == null) {
					locDetails.adminarea3 = longname;
					addressCnt++;
				}
				break;
			case COUNTRY:
				if (locDetails.country == null) {
					locDetails.country = longname;
					locDetails.countrycode = shortname;
					addressCnt++;
				}
				break;
			default:
				break;
			}
			type = 0;
		} else if ("location".equals(localName) || "location".equals(name)) {
			inLocation = false;
		} else if (inLocation && ("lat".equals(localName) || "lat".equals(name))) {
			locDetails.lat = Double.parseDouble(characters);
		} else if (inLocation && ("lng".equals(localName) || "lng".equals(name))) {
			locDetails.lng = Double.parseDouble(characters);
		}
	}

	public class LocDetails {

		private String country, countrycode, adminarea1, adminarea2, adminarea3;
		private double lat, lng;

		public String getCountry() {
			return country;
		}

		public String getCountrycode() {
			return countrycode;
		}

		public String getAdminarea1() {
			return adminarea1;
		}

		public String getAdminarea2() {
			return adminarea2;
		}

		public String getAdminarea3() {
			return adminarea3;
		}

		public double getLat() {
			return lat;
		}

		public double getLng() {
			return lng;
		}

		public String toString() {
			return country + "," + countrycode + "," + adminarea1 + "," + adminarea2 + "," + adminarea3 + "," + lat
					+ "," + lng;
		}

	}

	public LocDetails getLocDetails() {
		return locDetails;
	}

}
