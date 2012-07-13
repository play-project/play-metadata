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
package org.ow2.play.metadata.mongodb;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import junit.framework.TestCase;

import org.ow2.play.metadata.api.Data;
import org.ow2.play.metadata.api.Metadata;
import org.ow2.play.metadata.api.MetadataException;
import org.ow2.play.metadata.api.Resource;

import com.mongodb.DBObject;

/**
 * @author chamerling
 * 
 */
public class MongoMetadataServiceImplTest extends TestCase {

	public void testInitialized() {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		try {
			mongoMetadataServiceImpl.addMetadata(null, null);
			fail();
		} catch (MetadataException e) {
		}
	}

	public void testInit() {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.init(new Properties());
	}

	public void testAddMetadata() {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		try {
			mongoMetadataServiceImpl.addMetadata(new Resource("foo",
					"http://bar"), new Metadata("dsburl", new Data("url",
					"http://localhost")));
		} catch (MetadataException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testFindFirstResource() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		try {
			mongoMetadataServiceImpl.addMetadata(new Resource("foo",
					"http://bar"), new Metadata("dsburl", new Data("url",
					"http://localhost")));

			DBObject r = mongoMetadataServiceImpl.findFirst(new Resource("foo",
					"http://bar"));
			assertNotNull(r);

			DBObject resourceObject = (DBObject) r.get("resource");
			assertNotNull(resourceObject);

			Object name = resourceObject.get("name");

			assertNotNull(name);
			assertTrue(name instanceof String);
			assertEquals("foo", (String) name);

			Object url = resourceObject.get("url");
			assertNotNull(url);
			assertTrue(url instanceof String);
			assertEquals("http://bar", (String) url);

			System.out.println(r);
		} catch (MetadataException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testFindAllResource() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		String name = UUID.randomUUID().toString();

		try {

			for (int i = 0; i < 2; i++) {
				mongoMetadataServiceImpl.addMetadata(new Resource(name,
						"http://bar"), new Metadata("dsburl", new Data("url",
						"http://localhost")));

			}

			List<DBObject> r = mongoMetadataServiceImpl.findAll(new Resource(
					name, "http://bar"));
			assertNotNull(r);
			assertEquals(1, r.size());

			System.out.println(r);
		} catch (MetadataException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testRemove() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		long entries = mongoMetadataServiceImpl.getDbCollection().count();
		System.out.println("NB of entries : " + entries);

		// add an entry, check that nb entries is higher, than remove it and
		// check that nb entries is equal to the initial one. This assumes that
		// we are the only one to use the collection at this time...

		String name = UUID.randomUUID().toString();
		mongoMetadataServiceImpl.addMetadata(new Resource(name, "http://bar"),
				new Metadata("dsburl", new Data("url", "http://localhost")));

		long newNb = mongoMetadataServiceImpl.getDbCollection().count();

		assertTrue(newNb > entries);

		// TODO
	}

	public void testAddMetadataToexistingResource() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		String name = UUID.randomUUID().toString();
		Resource r = new Resource(name, "http://bar");

		mongoMetadataServiceImpl.addMetadata(r, new Metadata("dsburl",
				new Data("url", "http://localhost")));

		DBObject dbo = mongoMetadataServiceImpl.findFirst(r);
		String dbos = dbo.toString();

		// add meta and check that the object has been updated

		mongoMetadataServiceImpl.addMetadata(r, new Metadata("added", new Data(
				"literal", "true")));

		DBObject dboo = mongoMetadataServiceImpl.findFirst(r);
		String dboss = dboo.toString();

		assertTrue(mongoMetadataServiceImpl.findAll(r).size() == 1);

		System.out.println(dbos);
		System.out.println(dboss);

		// TODO : Check items directly...
		assertTrue(dboss.length() > dbos.length());

	}

	public void testGetMetadata() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		String name = UUID.randomUUID().toString();
		Resource r = new Resource(name, "http://bar");

		mongoMetadataServiceImpl.addMetadata(r, new Metadata("dsburl",
				new Data("url", "http://localhost")));
		mongoMetadataServiceImpl.addMetadata(r, new Metadata("dsburl",
				new Data("literal", "true")));

		List<Metadata> list = mongoMetadataServiceImpl.getMetaData(r);
		assertTrue(list.size() == 2);

		assertEquals("dsburl", list.get(0).getName());
		assertEquals("dsburl", list.get(0).getName());
		assertTrue(list.get(0).getData().size() == 1);
		assertTrue(list.get(1).getData().size() == 1);
	}

	public void testGetMetadataValue() throws Exception {
		MongoMetadataServiceImpl mongoMetadataServiceImpl = new MongoMetadataServiceImpl();
		mongoMetadataServiceImpl.setBsonAdapter(new BSONAdapterImpl());
		mongoMetadataServiceImpl.init(new Properties());

		String name = UUID.randomUUID().toString();
		Resource r = new Resource(name, "http://bar");

		mongoMetadataServiceImpl.addMetadata(r, new Metadata("1234567890",
				new Data("url", "http://localhost")));

		Metadata md = mongoMetadataServiceImpl
				.getMetadataValue(r, "1234567890");

		assertNotNull(md);

		assertEquals("1234567890", md.getName());
		assertTrue(md.getData() != null && md.getData().size() == 1);
		assertEquals(new Data("url", "http://localhost"), md.getData().get(0));

	}
}
