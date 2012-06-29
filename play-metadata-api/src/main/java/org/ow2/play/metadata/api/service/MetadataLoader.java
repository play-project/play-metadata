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
package org.ow2.play.metadata.api.service;

import java.net.URL;
import java.util.List;

import org.ow2.play.metadata.api.Metadata;
import org.ow2.play.metadata.api.MetadataException;

/**
 * Loads metadata from resources
 * 
 * @author chamerling
 * 
 */
public interface MetadataLoader {

	/**
	 * Loads metadata from resources and returns a list of metadata objects. Up
	 * to the implementation to handle the resource access...
	 * 
	 * @param resource
	 * @return
	 * @throws MetadataException
	 */
	List<Metadata> load(URL resource) throws MetadataException;

}
