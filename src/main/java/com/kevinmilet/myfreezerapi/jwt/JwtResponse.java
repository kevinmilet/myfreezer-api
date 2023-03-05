/**
 * 
 */
package com.kevinmilet.myfreezerapi.jwt;

/**
 * @author kevin
 *
 */
public class JwtResponse {

    private String userName;
    private Boolean isAdmin;

    public JwtResponse(String userName, Boolean isAdmin) {
	this.userName = userName;
	this.isAdmin = isAdmin;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Boolean getIsAdmin() {
	return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

}
