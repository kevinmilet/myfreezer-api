package com.kevinmilet.myfreezerapi.common;

import java.util.UUID;

/**
 * @author kevin
 *
 */
public class Utils {

    /**
     * Genere les UUID
     * 
     * @return
     */
    public static String generateUUID() {
	return UUID.randomUUID().toString().replace("-", "");
    }

}
