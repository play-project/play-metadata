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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.ow2.play.metadata.api.Data;
import org.ow2.play.metadata.api.Metadata;
import org.ow2.play.metadata.api.Resource;

/**
 * @author chamerling
 * 
 */
public class GsonMetadataSerializerTest extends TestCase {

	public void testList() throws Exception {
		GsonMetadataSerializer serializer = new GsonMetadataSerializer();

		Resource r = new Resource("stream",
				"http://streams.event-processing.org/ids/FacebookStatusFeed");

		List<Metadata> meta = new ArrayList<Metadata>();
		meta.add(new Metadata("http://purl.org/dc/elements/1.1/title",
				new Data("literal", "Facebook Status Feed")));
		meta.add(new Metadata("http://purl.org/dc/elements/1.1/description",
				new Data("literal", "A stream of Facebook Wall updates.")));

		org.apache.commons.io.output.ByteArrayOutputStream bos = new org.apache.commons.io.output.ByteArrayOutputStream();
		serializer.write(r, meta, bos);
		 
		String out = bos.toString();
		assertNotNull(out);
		assertTrue(out.length() > 0);
		assertTrue(out.indexOf("http://streams.event-processing.org/ids/FacebookStatusFeed#stream") == 2);
		
		System.out.println(out);
	}

}
