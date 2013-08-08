
package com.parship.roperty.ui.persistence.entity;

import com.parship.roperty.ui.permission.Permissions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;


/**
 * TODO
 *
 * @author marc
 *         Since 16.07.2013
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private byte[] password;

	private byte[] salt;

	private String name;

	private String firstname;

	private String email;

	private String permission;

	private Boolean active;

	public User() {}

	public User(Integer id, String username, byte[] password, byte[] salt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFullName() {
		return this.getFirstname() + " " + this.getName();
	}

	public List<Permissions> getPermissionList() {

		LinkedList<Permissions> result = new LinkedList<Permissions>();
		String[] permissions = this.permission.split("\\s*,\\s*");//StringUtils.split(this.permission, ",");

		Permissions permTemp;
		for (String permission : permissions) {

			permTemp = Permissions.valueOf(permission);
			if (permTemp != null) {
				result.add(permTemp);
			}
		}
		return result;
	}

}
