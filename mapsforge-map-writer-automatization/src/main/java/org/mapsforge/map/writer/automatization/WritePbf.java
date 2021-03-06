/*
 * Copyright 2010, 2011, 2012 mapsforge.org
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.11 at 03:25:14 PM MEZ 
//

package org.mapsforge.map.writer.automatization;

import java.io.File;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for write-pbf complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="write-pbf">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mapsforge.org/mapsforge-preprocessing-conf}sink">
 *       &lt;attribute name="omit-metadata" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *        &lt;attribute name="compress" default="none">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="none"/>
 *             &lt;enumeration value="deflate"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "write-pbf")
public class WritePbf extends Sink {

	/**
	 * This parameter defines if the osm meta data should be transfered into the written pbf file or if the are
	 * filtered.
	 */
	@XmlAttribute(name = "omit-metadata")
	private Boolean omitMetadata;

	/**
	 * This parameter defines if the deflate or no compression method should use for writing the pbf file.
	 */
	@XmlAttribute
	private String compress;

	/**
	 * Gets the value of the omitMetadata property.
	 * 
	 * @return possible object is {@link Boolean }
	 */
	public boolean isOmitMetadata() {
		if (this.omitMetadata == null) {
			return false;
		}
		return this.omitMetadata;
	}

	/**
	 * Sets the value of the omitMetadata property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 */
	public void setOmitMetadata(Boolean value) {
		this.omitMetadata = value;
	}

	/**
	 * Gets the value of the compress property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCompress() {
		if (this.compress == null) {
			return "none";
		}
		return this.compress;
	}

	/**
	 * Sets the value of the compress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setCompress(String value) {
		this.compress = value;
	}

	@Override
	public String generate(List<String> md5List, String absoluteWorkingDirPath, String absoluteOutputDirPath) {

		final StringBuilder sb = new StringBuilder();

		// check output file
		File outputFile = null;
		outputFile = FileOperation.createWriteFile(absoluteOutputDirPath, getFile());

		if (outputFile == null) {
			throw new RuntimeException("An unexpected error occured. File is null.");
		}

		// check md5
		if (isMd5()) {
			md5List.add(outputFile.getAbsolutePath());
		}

		// generate osmosis call
		sb.append("--wb").append(" ");
		sb.append("file=").append(outputFile.getAbsolutePath()).append(" ");

		if (this.compress != null) {
			sb.append("compress=").append("deflate").append(" ");
		}
		if (this.omitMetadata != null) {
			sb.append("omitmetadata=").append(this.omitMetadata).append(" ");
		}

		return sb.toString();
	}
}
