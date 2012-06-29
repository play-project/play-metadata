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

import java.net.URL;
import java.util.List;

import org.ow2.play.metadata.api.Metadata;
import org.ow2.play.metadata.api.MetadataException;
import org.ow2.play.metadata.api.service.MetadataLoader;

/**
 * @author chamerling
 *
 */
public class JSONMetadataLoaderImpl implements MetadataLoader {

	/* (non-Javadoc)
	 * @see org.ow2.play.metadata.api.service.MetadataLoader#load(java.net.URL)
	 */
	@Override
	public List<Metadata> load(URL resource) throws MetadataException {
		// TODO Auto-generated method stub
		return null;
	}

}
