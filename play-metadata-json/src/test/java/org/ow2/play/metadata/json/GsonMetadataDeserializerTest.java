/**
 *
 * Copyright (c) 2012, PetalsLink
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA 
 *
 */
package org.ow2.play.metadata.json;

import java.util.List;

import junit.framework.TestCase;

import org.ow2.play.metadata.api.MetaResource;

/**
 * @author chamerling
 * 
 */
public class GsonMetadataDeserializerTest extends TestCase {

	public void testDeserializeFromResource() throws Exception {
		GsonMetadataDeserializer deserializer = new GsonMetadataDeserializer();
		List<MetaResource> result = deserializer
				.read(GsonMetadataDeserializerTest.class
						.getResourceAsStream("/metadata-1resource.rdf.json"));

		assertEquals(1, result.size());
	}

	public void testDeserializeFromResource2() throws Exception {
		GsonMetadataDeserializer deserializer = new GsonMetadataDeserializer();
		List<MetaResource> result = deserializer
				.read(GsonMetadataDeserializerTest.class
						.getResourceAsStream("/metadata-2resources.rdf.json"));

		assertEquals(2, result.size());
	}

}
