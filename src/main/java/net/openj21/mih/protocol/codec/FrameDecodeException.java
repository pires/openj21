/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 */
package net.openj21.mih.protocol.codec;

import java.io.IOException;

/**
 * This exception is thrown when a MIH protocol frame, or part of it, can not be
 * decoded.
 */
public class FrameDecodeException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -490052854454254011L;

	/**
	 * Constructs an {@code IOException} with {@code null} as its error detail
	 * message.
	 */
	public FrameDecodeException() {
	}

	/**
	 * Constructs an {@code IOException} with the specified detail message.
	 * 
	 * @param message
	 *            The detail message (which is saved for later retrieval by the
	 *            {@link #getMessage()} method)
	 */
	public FrameDecodeException(String message) {
		super(message);
	}
}
